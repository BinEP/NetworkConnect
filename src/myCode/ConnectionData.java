package myCode;

public class ConnectionData {

	
	public String someText;
	private String[] talk = {"Knock Knock", "Who there", "I am", "I am who", "I am Brady"};
	
	public String processData(String text) {
		
		if (text == null) {
			return talk[0];
		}
		for (int i = 0; i < talk.length - 1; i++) {
			if (talk[i].equals(text)) {
				return talk[i+1];
			}
		}
		
		return talk[talk.length - 1];
	}
	
}
