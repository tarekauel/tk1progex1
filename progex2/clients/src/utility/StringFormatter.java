package utility;

public class StringFormatter {
	
	public static String formatCheckoutResult(String result){
		String code = String.valueOf(result.charAt(0));
		switch (code){
			case "0":	return "Successful!";
					 
			case "1":	String arg2 = result.substring(1);
						return String.format("Out of stock: %s", arg2);
			
			case "2": 	int count = Integer.parseInt(String.valueOf(result.charAt(1)));
						String arg0 = result.substring(2, 2+count);
						String arg1 = result.substring(2+count);
						return String.format("You cannot purchase more than %s of %s", arg0, arg1);
			
			default:	return "Error: Unkown result code";
		}
	}
}
