package gaussmult;

import java.math.BigInteger;

public class Karatsuba {
	
	public BigInteger multiplyRecurse(BigInteger x, BigInteger y) {
		
		// Gets whichever number is longer
		int n = Math.max(x.toString().length(), y.toString().length());
		
		// If n is a single-digit number
		if(n == 1) {
			return x.multiply(y);
		}
		
		// Divide n by 2 to use for halving x and y
		n = (n / 2) + (n % 2);
		
		// Sub-numbers of x and y
		BigInteger a = x.divide(BigInteger.TEN.pow(n));
		BigInteger b = x.subtract(a.multiply(BigInteger.TEN.pow(n)));
		BigInteger c = y.divide(BigInteger.TEN.pow(n));
		BigInteger d = y.subtract(c.multiply(BigInteger.TEN.pow(n)));
		
		// Sub-equations
		BigInteger ac = multiplyRecurse(a, c);
		BigInteger bd = multiplyRecurse(b, d);
		BigInteger abcd = multiplyRecurse(a.add(b), c.add(d));
		
		// Multiplying sub-equations with 10^2n and 10^n, respectively
		BigInteger finalAC = (BigInteger.TEN.pow(2*n)).multiply(ac);
		BigInteger finalABCD = (BigInteger.TEN.pow(n)).multiply(abcd.subtract(ac).subtract(bd));
		
		// Final product
		return finalAC.add(finalABCD).add(bd);
	}
	
	public static void main(String[] args) {
		Karatsuba k = new Karatsuba();
		BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
		BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
		BigInteger result = k.multiplyRecurse(x, y);
		System.out.println(result);
	}
}
