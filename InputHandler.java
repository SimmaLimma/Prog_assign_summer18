import java.io.*;
import java.util.Scanner;
import java.util.Vector; 


/*
 * Class that stores read input, reads input and check validity.
 * Can also specify what type a command is.
 */

public class InputHandler {



	/*--- Variables ---*/

	/*
	 *Stores all commands read from either file or console.
	 */
	private Vector<String> commandList;


	/*
	 * Keeps track of if input should be read from file or console.
	 * True if reading input from file,
	 * false if reading from  console.
	 */
	boolean readFromFile;


	String inputFileName;

	Scanner inputScanner;

	File inputFile;





	/*--- Constructors ---*/

	/*
	 * Default constructor.
	 * Initializes reading from console.
	 */
	public InputHandler(){
		
		//Initializes empty vector for command list
		commandList = new Vector<String>();
		
		//User mode, read from console
		this.readFromFile = false;
		this.inputScanner = new Scanner(System.in);
		
	}


	/*
	 * Constructor.
	 * Initializes reading from file inputFileName.
	 */
	public InputHandler(String inputFileName){
		
		//Initializes empty vector for command list
		commandList = new Vector<String>();
		
		//File mode, read from file inputFileName
		this.readFromFile = true;
		this.inputFileName = inputFileName;

		inputFile = new File(inputFileName);

		try{
			inputScanner = new Scanner(inputFile);
		} 
		catch(FileNotFoundException e){
			System.out.println("Error: File not found or can not be read. \n");
		}


	}





	/*--- Methods ---*/

	/*
	 * Checks if a certain command is a valid input.
	 * Returns true if it is valid, false otherwise.
	 */
	private boolean validInput(String input){


		//Switch statement checks if input have the correct words corresponding to each type of command.
		//Operation command needs second word as "add", "subtract" or "multiply",
		//and first and third word as alphanumerical.
		//Print needs first word as 
		switch(StringHandler.wordCount(input)){

		case 1:

			//Extracting first word, which should be "quit"
			String quitStr = StringHandler.extractWordFromString(input, 1);
			boolean validQuitCommand = quitStr.equals("quit");
			
			//Returns true if valid input
			if(validQuitCommand && StringHandler.isStringAlphanumeric(input)){
				return true;
			}
			break;


		case 2: 

			//Extracting first word, which should be "print"
			String printStr = StringHandler.extractWordFromString(input, 1);
			boolean validPrintCommand = printStr.equals("print");

			//Returns true if valid input
			if(validPrintCommand && StringHandler.isStringAlphanumeric(input)){
				return true;
			}
			break;


		case 3:

			//Extracting second word, which is the operator
			String oper = StringHandler.extractWordFromString(input, 2);
			boolean validOperCommand = oper.equals("add") || oper.equals("subtract") || oper.equals("multiply");
			
			//Extract first word to check if first word is register (not only numeric), and not a value 
			String firstWord = StringHandler.extractWordFromString(input, 1);
			
			//Return true if second word valid and valid register names
			if(validOperCommand && StringHandler.isStringAlphanumeric(input) && StringHandler.isStringAlphabetic(firstWord)){
				return true;
			}
			break;


		}

		//Notifies user input invalid
		System.out.println("Invalid input: " + input);
		
		//If amount of words is not equal to 1,2 or 3,
		//or the right word requirement is not fulfilled,
		//input is invalid and return false
		return false;

	}

	
	/*
	 * Reads one line from input.
	 * If fileMode is true, reads from file inputFile. If false, reads from console.
	 * All inputs are made lower case.
	 * If file ends, a command "quit" is added to command list, so that program quits.
	 * Returns true if input valid.
	 * Returns false if invalid input.
	 */
	public boolean readLineFromInput(){

		//Read input from file
		if(readFromFile){ 
			if(inputScanner.hasNextLine()){

				//Read next line from file
				String tmp = inputScanner.nextLine();

				//Make lower case to make program case-insensitive
				tmp = tmp.toLowerCase();
				
				//If input invalid, return false and do not add input to commandList
				if(!validInput(tmp)){
					return false;
				}

				//If input valid, add to commandList
				commandList.add(tmp);

				//Input valid
				return true;

			}
			//If no line to read from file, interpret it as a quit command
			else{ 
				commandList.add("quit");
				return true;
			}


		} 
		else { //Read input from console

			//Waits for user to write a line and then reads it
			String tmp = inputScanner.nextLine();

			//Make lower case to make program case-insensitive
			tmp = tmp.toLowerCase();
			
			//If input invalid, return false and do not add input to commandList
			if(!validInput(tmp)){
				return false;
			}

			//If input valid, add to commandList
			commandList.add(tmp);
			return true;
		}
	}


	/*
	 * Method for checking what type of command the latest command was.
	 * Returns string with either "operation", "print", "quit" or "NoType" (if no commands available)
	 * Note that only valid commands are stored, and therefore no need checking validity
	 */
	public String getTypeOfLatestCommand(){

		//If command list empty, return "NoType"
		if(commandList.size() == 0){
			return "NoType";
		}
		
		//Calculate how many words latest command have
		int noOfWords = StringHandler.wordCount(commandList.lastElement()); 

		//Checking command type based on words in latest command.
		//Three words corresponds to operations.
		//Two words corresponds to prints.
		//One word corresponds to quit.
		if(noOfWords == 3){
			return "operation";

		}
		else if(noOfWords == 2){
			return "print";

		}
		//if(noOfWords == 1)
		else{ 
			return "quit";
		}

	}

	
	/*
	 * Getter for command list
	 */
	public Vector<String> getCommandList(){
		return this.commandList;
	}

	/*
	 * 
	 */
	public String getLatestCommand(){
		String result = commandList.lastElement();
		return result;
	}
	
	/*
	 * Empties the command list
	 */
	public void emptyCommandList(){
		this.commandList = new Vector<String>();
	}





	/*--- Methods for testing ---*/

	/*
	 * Prints out current commandList
	 */
	public void printCommandList(){

		System.out.println("Current stored command list:");

		int limit = commandList.size();
		for(int i=0; i < limit; i++){
			System.out.println(commandList.get(i));

		}

		System.out.print("\n");
	}
}




