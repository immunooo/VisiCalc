public class DateValue extends Value {
	private String stringValue;
	public DateValue() {
		
	}
	public DateValue(String value) {
		this.stringValue = value;
	}
	public String getOutput() {//Returns output
		return stringValue;
	}
	public String getInput() {//Returns input
		return stringValue;
	}

}
