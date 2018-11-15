import java.util.Vector; 


/*
 * Class that handles registers.
 * Works as a middle layer between main program and Registers.
 * Is able to handle InputHandler as direct input to update Registers.
 * Assumes validity checking of inputs are made by InputHandler.
 */
public class RegisterHandler {

	/*--- Variables ---*/

	private static Registers registers;



	/*--- Constructors ---*/

	/*
	 * Default constructor
	 */
	public RegisterHandler(){

		//Initializes a Registers object, which will give Registers with no registers
		registers = new Registers();

	}




	/*--- Methods ---*/


	/*
	 * Updates registers storage from commands.
	 * Ignores print- and quit-commands
	 */
	public void updateRegFromCommands(InputHandler input){

		//Get command list from input
		Vector<String> commandList = input.getCommandList();

		//Update register from every command
		for(int i=0; i < commandList.size(); i++){

			//Get the i:th command
			String currentCommand = commandList.elementAt(i);

			//Check how many words current command contains
			int amountOfWords = StringHandler.wordCount(currentCommand);

			//If current command is three words long, it means it is an operation command
			//i.e. not a print- or quit-command
			if(amountOfWords == 3){

				//Extract information from command
				String firstRegister = StringHandler.extractWordFromString(currentCommand,1);
				String operation = StringHandler.extractWordFromString(currentCommand,2);
				String secondRegister = StringHandler.extractWordFromString(currentCommand,3);
			


				//Check if first register already exists. If it does not, add it
				if(!registers.doRegisterExist(firstRegister) && !StringHandler.isStringNumeric(firstRegister)){

					registers.addRegister(firstRegister);
				}

				//Check if second register already exists. If it do not, add it
				if(!registers.doRegisterExist(secondRegister) && !StringHandler.isStringNumeric(secondRegister)){

					registers.addRegister(secondRegister);
				}


				//Find which operation should be done and do it
				if(operation.equals("add")){
					registers.addToRegister(firstRegister, secondRegister);
				}
				else if(operation.equals("subtract")){
					registers.subtractFromRegister(firstRegister, secondRegister);
				}
				else if(operation.equals("multiply")){
					registers.multiplyWithRegister(firstRegister, secondRegister);
				}


			}
		}

	}

	/*
	 * Evaluate value of register with name name.
	 * This is done recursivly, where to calculate every register
	 * that has been operated to register name,
	 * this method is used.
	 */
	public static int evaluateRegister(String name){

		//Extract evaluation string for register
		String evalString = registers.getEvaluationString(name);

		//Check how many words evalString consists of
		int wordCount = StringHandler.wordCount(evalString);

		//Variable to hold value when calculating
		int value = 0;

		//Iterate for every second word (operation and register name are bundled).
		for(int i=1; i < wordCount; i = i+2){
			
			//Extract words
			String tmpOper = StringHandler.extractWordFromString(evalString, i+1);
			String tmpReg = StringHandler.extractWordFromString(evalString, i+2);
			
			int tmpVal = 0;

			//If it is a numeric value, translate string to int and use wanted operation to variable value
			if(StringHandler.isStringNumeric(tmpReg)){
				
				tmpVal = Integer.parseInt(tmpReg);

				if(tmpOper.equals("+")){
					value = value + tmpVal;
				}
				else if(tmpOper.equals("-")){
					value = value - tmpVal;
				}
				else if(tmpOper.equals("*")){
					value = value * tmpVal;
				}
			}
			
			//If it is a register, calculate the registers value and use wanted operation to variable value
			else{
				
				if(tmpOper.equals("+")){
					value = value + RegisterHandler.evaluateRegister(tmpReg);
				}
				else if(tmpOper.equals("-")){
					value = value - RegisterHandler.evaluateRegister(tmpReg);
				}
				else if(tmpOper.equals("*")){
					value = value * RegisterHandler.evaluateRegister(tmpReg);
				}
			}
		}

		//Return value of register
		return value;


	}


}
