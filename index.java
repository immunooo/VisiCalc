//Project 2: Visicalc
//Due : 2/15/2019
import java.util.Scanner;
public class index {
	
	public static spreadSheet main = new spreadSheet();
	
	public static void main(String []args) {
		introduction();
		Scanner console = new Scanner(System.in);
		System.out.print("Command: ");
		String input = console.nextLine();
		
		while(!input.equalsIgnoreCase("quit")) {//These if statements find out what command the user is trying to use
			if(input.equalsIgnoreCase("print")) {//Print
				main.print();
			} else if(input.equalsIgnoreCase("help")) {//Help
				introduction();
			} else if(input.equalsIgnoreCase("clear")) {//Clear
				clearAll();
			}else if(input.toLowerCase().contains("CLEAR".toLowerCase())) {//Clear <cell ref>
				clear(input);
			}else if(input.toLowerCase().contains("SORTA".toLowerCase()) || input.toLowerCase().contains("SORTD".toLowerCase())) {//sorta/sortd
				if(input.substring(0,5).equalsIgnoreCase("sorta") || input.substring(0,5).equalsIgnoreCase("sortd")) {
					sort(input);
				}
			}else {
				parseInput(input);//<cell ref> = <value> or <cell ref> :
			}
			System.out.print("\nCommand: ");
			input = console.nextLine();
		}
	}
	public static void parseInput(String str) {//parses input for setting cells and getting cell reference.
		if(str.contains("=")) {//Method Changes an input to coordinates. Example: B3 to the coordinates 1 2
			String lett = str.substring(0,1);
			lett = lett.toUpperCase();
			char ch = lett.charAt(0);
			String num = str.substring(str.indexOf(ch) + 1, str.indexOf(" "));
			int row = ch - 'A';
			String stuff = str.substring(str.indexOf("= ") + 1);
			stuff = stuff.substring(1, stuff.length());
			int column =  Integer.parseInt(num) - 1;
			main.setCell(row, column, stuff);
			String str1 = "";
			try {
				str1 = spreadSheet.main[row][column].getProduct();//This tests to see if the product doesn't come with error if it does it clears the cell and tells the user there was an error
			}catch(NumberFormatException e) {
				main.clearCell(row, column);
				System.out.print("Error assigning the value to the cell.");
			}
			if(str1 == null) {
				System.out.print("Error assigning the value to the cell.");
			}
		} else if(str.contains(":")){
			cellReference(str);		
		}
		
	}
	public static void cellReference(String str) {//Gets input of a cell
		try { // Method Changes an input to coordinates. Example: B3 to the coordinates 1 2
		String lett = str.substring(0,1);
		lett = lett.toUpperCase();
		char ch = lett.charAt(0);
		String num = str.substring(str.indexOf(ch) + 1, str.indexOf(":") - 1);
		int row = ch - 'A';
		int column =  Integer.parseInt(num) - 1;
		System.out.println(main.cellRef(row, column));
		} catch(NumberFormatException e) {
			System.out.println("error");
		}
	}
	public static void clear(String str) {//Method clears cell of given 
		try {//Method Changes an input to coordinates. Example: B3 to the coordinates 1 2
		str = str.replace(" ", "");
		str = str.toUpperCase();
		str = str.substring(str.indexOf("R") + 1);
		String lett = str.substring(0,1);
		lett = lett.toUpperCase();
		char ch = lett.charAt(0);
		String num = str.substring(str.indexOf(ch) + 1);
		int row = ch - 'A';
		int column =  Integer.parseInt(num) - 1;
		main.clearCell(row, column);//Clears cell
		System.out.println("Cleared: " + ch + num);
		} catch(NumberFormatException e) {
			System.out.println("error");
		}
	}
	public static void clearAll() {//Method clear every single cell
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				main.clearCell(i, j);
			}
		}
		System.out.print("Cleared every cell!");
	}
	public static void sort(String str) {//Method parses stuff for sorta
		try {//Method also gets coordinates for the range of stuff you want to sort
		boolean sorta = false;
		if(str.substring(0,5).equalsIgnoreCase("sorta")) {
			sorta = true;
		}
		str = str.substring(5);
		str = str.replace(" ", "");
		str = str.toUpperCase();
		String one = "ean";
		String two = "ean";
		try {
		one = str.substring(0, str.indexOf('-'));
		two = str.substring(str.indexOf('-') + 1);
		} catch(StringIndexOutOfBoundsException e) {
			
		}
		String lett = one.substring(0,1);//This chunk gets fist coordinates
		char ch = lett.charAt(0);
		String num1 = one.substring(one.indexOf(ch) + 1);
		int row1 = ch - 'A';
		int column1 =  Integer.parseInt(num1) - 1;
		
		lett = two.substring(0,1);//This chunk of code gets second coordinates
		ch = lett.charAt(0);
		String num2 = two.substring(two.indexOf(ch) + 1);
		int row2 = ch - 'A';
		int column2 =  Integer.parseInt(num2) - 1;
		main.sorta(row1, column1, row2, column2, sorta);
		} catch (NumberFormatException e) {
			System.out.println("Invalid range.");
		}
	}
	public static void introduction() { //prints user guide
		System.out.println("THIS PROGRAM ALLOWS FOR YOU TO SEE AND ADJUST A SPREADSHEET.\r\n"
				+ "THE FOLLOWING IS A LIST OF COMMANDS THAT THIS PROGRAM CAN HANDLE: \r\n"
				+ "	--> quit- This exits the spreadsheet program.\r\n" + 
				"	--> help- Prints out all available commands.\r\n" + 
				"	--> print- This prints out the current spreadsheet in its entirety.\r\n" + 
				"	--> <cell reference> = <value>- This is used to place data into a cell. Here are some examples: A1 = 6.983 , B2 = “Hello there people!” , C5 = 07/14/1955 \r\n" + 
				"	--> <cell reference> :- This is used to print the contents of a cell. This must print out exactly what was input\r\n" + 
				"				to the cell, which is not necessarily what gets printed in the cell when the spreadsheet is printed.\r\n" + 
				"	--> clear <cell reference>- This is used to clear out a cell and “make it empty”.\r\n" + 
				"	--> clear - This is used to clear the entire spreadsheet and make all cells empty.\r\n" + 
				"	--> sorta <cell range>- This is used to sort a block of cells in ascending order. All cells in the range must be of\r\n" + 
				"				the same type in order to be sorted. A cell range is specified as 2 cell references separated by a minus\r\n" + 
				"	--> sortd <cell range>- Same as sorta except the cells are sorted in descending order.\r\n"
				+ "PLEASE TYPE 'help' TO SEE THESE COMMANDS AGAIN.\r");
		System.out.println();
	}
}
