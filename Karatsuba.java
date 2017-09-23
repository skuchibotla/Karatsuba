package karatsuba;

import java.math.BigDecimal;

public class Karatsuba {

	public double multipleRecurse(double x, double y) {
		// Gets larger number
		int lengthX = String.valueOf(x).length();
		int lengthY = String.valueOf(y).length();
		int n = Math.max(lengthX, lengthY);
		
		// If n is a single digit number,
		// multiply normally
		if(n < 10)
			return x * y;
		
		// Half of n
		double newN = (n / 2) + (n % 2);
		
		// Use these for finding product
		double tenN = (long)Math.pow(10, n);
		double tenNewN = (long)Math.pow(10, newN);
		
		// Subnumbers to find subequations
		double a = x / tenN;
		double b = x - (a * tenN);
		double c = y / tenN;
		double d = y - (c * tenN);
		
		// Subequations to find final product
		double ac = multipleRecurse(a, c);
		double bd = multipleRecurse(b, d);
		double abcd = multipleRecurse(a + b, c + d);
		
		return (tenN * ac) + (tenNewN * (abcd - ac - bd)) + bd;
	}
	
	public static void main(String[] args) {
		Karatsuba k = new Karatsuba();
		double result = k.multipleRecurse(2733, 72434);
		BigDecimal r = new BigDecimal("" + result);
		System.out.println(r);
		
	}
}
