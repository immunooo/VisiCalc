import java.text.ParsePosition;
import java.text.SimpleDateFormat;

public class Value {
	private String stringVal;
	private Value val;
	private boolean equation;
	private boolean str;
	private boolean date;
	private boolean doub;
	
	public Value() {
		this.val = null;
	}
	public Value(String value) {//Method finds value object the input will become
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		if(value != null) {
			if(value.charAt(0) == '(' && value.charAt(value.length() - 1) == ')') {//This is for equation
				equation = true;
			} else if(value.charAt(0) == '\"' && value.charAt(value.length()- 1) == '\"') {//this is for string
				str = true;
			}else if(format.parse(value, new ParsePosition(0)) != null){//this is for date
				date = true;
			} else {//This is for a double
				try {
					Double.parseDouble(value);
					doub = true;
				} catch(NumberFormatException e) {
					doub = false;
				}
			}
		}
		if(equation) {
			this.val = new EquationValue(value);//Creates equation object
		} else if(str){
			this.val = new StringValue(value);//Creates string value
		}else if(date) {
			this.val = new DateValue(value);//Creates Date object
		}else if(doub) {
			this.val = new DoubleValue(value);//Creates double value
		} else {
			this.val = null;
		}
	}
	public String toString() {//Method returns output
		if(val != null) {
			if(str) {
				stringVal = ((StringValue) val).getOutput();
			} else if(date) {
				stringVal = ((DateValue) val).getOutput();
			} else if (equation) {
				stringVal = ((EquationValue) val).getOutput();
			} else if(doub) {
				stringVal =((DoubleValue) val).getOutput();
			}
		}
		return stringVal;
	}
	public String rawValue() {//Method returns input
		if (str) {
			stringVal = ((StringValue) val).getInput();
		} else if (date) {
			stringVal = ((DateValue) val).getInput();
		} else if (equation) {
			stringVal = ((EquationValue) val).getInput();
		} else if (doub) {
			stringVal = ((DoubleValue) val).getInput();
		}
		return stringVal;
	}
}
