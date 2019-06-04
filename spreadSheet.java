import java.util.ArrayList;
import java.util.Collections;

public class spreadSheet {
	public static Cell[][] main = new Cell[10][10];//Spreadsheet object
	
	public void setCell(int row, int column, String cellValue) {//Creates a cell with a value
		main[row][column] = new Cell(cellValue);
	}
	public void clearCell(int row, int column) {//Creates a null cell object of row and column
		main[row][column] = new Cell();
	}
	
	public String cellRef(int row, int column) {//Returns input of given row and column
		return main[row][column].getCell();
	}
	
	public void print() {//Method prints out the spreadsheet
		System.out.print("  ");
		for(int i = 1; i <= 10; i++) {
			System.out.print(" " + i + "         ");
		}
		System.out.print("\n  ");
		for(int i = 1; i <= 111; i++) {
			System.out.print("-");
		}
		for(int f = 0 ; f <= 9; f++) {
			System.out.print("\n" + (char) (65 + f) + " |");
			for(int j = 0; j <= 9; j++) {
				String str = "";
				try {
					str = main[f][j].getProduct();//Gets output of location
					if(str.length() > 10) {//if the location is bigger than 10 it cuts it down
						str = str.substring(0,10);
					}
				}catch(NullPointerException e) {
					str = "";
				}
				if (str != null) {
					System.out.print(str);
					for(int k = 0; k < 10 - str.length(); k++) {
						System.out.print(" ");
					}
				} else {
					System.out.print("          ");
				}
				System.out.print("|");
				
			}
			System.out.print("\n  ");
			for(int l = 1; l <= 111; l++) {
				System.out.print("-");
			}
		}
	}
	public void sorta(int row1, int column1, int row2, int column2, boolean sorta) {//Sorts the cells based on the boolean
		ArrayList<Double> num = new ArrayList<Double>();
		ArrayList<String> location = new ArrayList<String>();
		
		for(int i = row1; i <= row2; i++) {//For method collects values of locations in two different array lists.
			for(int j = column1; j <= column2; j++) {
				if( main[i][j].getProduct() != null) {
					num.add(Double.parseDouble(main[i][j].getCell()));
					location.add(i + "" + j);
				}
			}
		
		}
		Collections.sort(num);//sorts values ascending
		if(!sorta) {//if false sorts descending
			Collections.reverse(num);
		}
		for(int i = 0; i < num.size(); i++) {//Reassigns values for locations
			String rowColumn = location.get(i);
			int row = Integer.parseInt(rowColumn.substring(0, 1));
			int column = Integer.parseInt(rowColumn.substring(1));
			this.setCell(row, column, Double.toString(num.get(i)));
		}
	}

}
