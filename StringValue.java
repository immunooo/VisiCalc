//This works as intended
public class StringValue extends Value{
	private String stringValue;
	
	public StringValue() {
	}
	
	public StringValue(String value) {
		this.stringValue = value;
	}
	
	public String getOutput() {//Returns string without quotations
		String val = stringValue.substring(1, stringValue.length()-1);
		return val;
	}
	public String getInput() {//Returns input
		return stringValue;
	}

}
