public class DoubleValue extends Value{
	private String stringValue;
	public DoubleValue() {
		
	}
	public DoubleValue(String value) {
		this.stringValue = value;
	}
	public double getdouble() {//Method gets returns a double type for value
		double doub = Double.parseDouble(stringValue);
		return doub;
	}
	public String getOutput() {//Method returns output
		return Double.toString(this.getdouble());
	}
	public String getInput() {//Returns input
		return stringValue;
	}
}
