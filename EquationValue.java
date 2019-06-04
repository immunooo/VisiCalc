import java.util.ArrayList;

public class EquationValue extends Value {
	private String stringValue;
	public EquationValue() {
	}
	public EquationValue(String value) {
		this.stringValue = value;
	}
	public double getEquationValue() {
		
		String equation = stringValue.substring(1, stringValue.length() -1);
		equation = equation.replace(" ",  "");
		String collector = "";
		//Above gets rid of spaces and ( )
		ArrayList<String> content = new ArrayList<String>();
		for(int i = 0; i < equation.length(); i++) {//for loop to collect data for content ArrayList
			if(equation.charAt(i) == '+'|| equation.charAt(i) == '-' || equation.charAt(i) == '*'|| equation.charAt(i) == '/' ) {//If state stops collector on operators, adds collector to Array List and resets the collector
				if(collector.toUpperCase().charAt(0) - 'A' <= 10 && collector.toUpperCase().charAt(0) - 'A' >= 0) {//This if statement determines if user is calling a cell reference in equation
					String lett = collector.substring(0,1);//Changes the input to coordinates. Example: B3 to the coordinates 1 2
					lett = lett.toUpperCase();
					char ch = lett.charAt(0);
					String num = collector.substring(collector.indexOf(ch) + 1);
					int row = ch - 'A';
					int column =  Integer.parseInt(num) - 1;
					try {
					collector = spreadSheet.main[row][column].getProduct();//Gets the value of the cell refrence and changes the collector to it
					} catch (NullPointerException e) {
						
					}
				}
				content.add(collector);//Adds input to content
				collector = "" + equation.charAt(i);//Gets operator
				content.add(collector);//Adds operator to content
				collector = "";//Resets collector
			} else {
				collector += equation.charAt(i);//Builds collector
			}
		}
		if(collector.toUpperCase().charAt(0) - 'A' <= 10 && collector.toUpperCase().charAt(0) - 'A' >= 0) {//This if statement determines if user is calling a cell reference in equation
			String lett = collector.substring(0,1);//Changes the input to coordinates. Example: B3 to the coordinates 1 2
			lett = lett.toUpperCase();
			char ch = lett.charAt(0);
			String num = collector.substring(collector.indexOf(ch) + 1);
			int row = ch - 'A';
			int column =  Integer.parseInt(num) - 1;
			try {
			collector = spreadSheet.main[row][column].getProduct();//Gets the value of the cell refrence and changes the collector to it
			} catch (NullPointerException e) {
				
			}
		}
		content.add(collector);
		double answer = Double.parseDouble(content.get(0));
		for(int i = 1; i < content.size(); i++) {//for loop parses all of the numbers and operators inside the content ArrayList
			if(content.get(i).equalsIgnoreCase("+")) {
				answer = answer + Double.parseDouble(content.get(i + 1));
			}
			if(content.get(i).equalsIgnoreCase("-")) {
				answer = answer - Double.parseDouble(content.get(i + 1));
			}
			if(content.get(i).equalsIgnoreCase("/")) {
				answer = answer / Double.parseDouble(content.get(i + 1));
			} 
			if(content.get(i).equalsIgnoreCase("*")) {
				answer = answer * Double.parseDouble(content.get(i + 1));
			} 
		}
		
		return answer;
	}
	public String getOutput() {//Returns the output to string
		return Double.toString(this.getEquationValue());
	}
	public String getInput() {//Returns input
		return stringValue;
	}

}
