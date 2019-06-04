public class Cell {
	private String stringValue;
	private Value value;
	public Cell() {//Cell is null
		this.value = null;
	}
	public Cell(String cellValue) {//Makes a value object for the cell
		this.value = new Value(cellValue);
	}
	
	public String getCell() {//Gets raw Value
		try {
		stringValue = value.rawValue();
		return stringValue;
		} catch(NullPointerException e) {
			return "<Null>";
		}
	}
	public String getProduct() {//Gets the finished value for print command
		stringValue = value.toString();
		return stringValue;
	}

}
