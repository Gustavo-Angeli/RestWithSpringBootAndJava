package br.com.gusta.converter;

import br.com.gusta.exceptions.UnsupportedMathOperationException;

public class NumberConverter {

	public static Double convertToDouble(String strNumber) throws Exception{
		if (strNumber == null) {
			 return 0D;
		}
		String number = strNumber.replaceAll(",", ".");
		if (isNumeric(number)) {
			 return Double.parseDouble(number);
		} else if (!isNumeric(number)) {
			throw new UnsupportedMathOperationException("Por favor coloque um valor numérico");
		}
		return 0D;
	}

	private static boolean isNumeric(String strNumber) {
		if (strNumber == null) {
			 return false;
		}
		return strNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
