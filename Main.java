
import java.util.Scanner;
import java.util.Vector; 
import java.io.*;

/*
 * Created by:		
 * 		Anonymous
 * 
 * Created at:		
 * 		2018-02-01
 * 
 * Last update at:	
 * 		2018-02-06
 * 
 * 
 */



public class Main{


	public static void main(String[] args){

		//Ends program if too many arguments
		if(args.length > 1){
			System.out.println("Error: Too many arguments.");
			System.exit(0);
		}


		//If args.empty, then user-Mode.class Otherwise	
		boolean readFromFileMode = (args.length == 1); 

		//Init for class handling user input.
		InputHandler userInput;
		
		//Defining if object handling inputs should read from file or from console.
		//This is calculated by checking if user has entered any file name as argument
		if(readFromFileMode){
			
			//Reads from file
			userInput = new InputHandler(args[0]);
		} 
		//if no argument entered
		else { 
			
			//Reads from console
			userInput = new InputHandler();
		}

		
		//Init for object handling registers
		RegisterHandler registerHandler = new RegisterHandler();

		//Indicates if user wants to exit program or if whole file scanned
		boolean exitProgram = false;

		//Welcoming text which indicates program ready for input, if user-mode
		if(!readFromFileMode){
			System.out.println("Please enter commands:");
		}

		//Main loop
		while(!exitProgram){

			//Read line
			userInput.readLineFromInput();
		
			//Get command type of latest inputed command
			String commandType = userInput.getTypeOfLatestCommand();
			
			//If user requested print: Evaluate registers and then print
			if(commandType == "print"){
				
				//Stores latest command, which is a print-command
				String tmpCommand = userInput.getLatestCommand();
				
				//Extracts what register should be printed
				String registerToPrint = StringHandler.extractWordFromString(tmpCommand, 2);
				
				//Updates registers in registerHandler according to what user had inputed
				registerHandler.updateRegFromCommands(userInput);
				
				//Empty the command list, becuase registers are now updated to those commands
				userInput.emptyCommandList();
				
				//Evaluates register that has been demanded to print
				int result = registerHandler.evaluateRegister(registerToPrint);
				
				//Prints result
				System.out.println(result);
				
			}
			
			
			//If user requested quit or if file ends, then quit main loop
			else if(commandType == "quit"){
				exitProgram = true;
			}
			
		}
	}
}
