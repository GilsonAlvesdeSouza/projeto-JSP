package test;

import java.math.BigDecimal;

public class TestBigDecimal {
	public static void main(String[] args) {
		BigDecimal big1 = new BigDecimal("0.1");
		BigDecimal big2 = new BigDecimal("0.2");
		BigDecimal bigResult = big1.add(big2);
		System.out.println(bigResult.toString());

	}

}
