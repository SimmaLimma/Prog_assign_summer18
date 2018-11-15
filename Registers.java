import java.util.Vector;

/*
 * Class that stores Register values and 
 * includes methods that works with registers directly.
 * Stores what operations have been done to a register in strings (see toEvaluate below).
 */
public class Registers {

	/*--- Variables ---*/
	
	//Names of register
	//The n:th element corresponds to the n:th element in toEvaluate
	private Vector<String> registerNames;
	
	//String that keeps track of what to evaluate when evaluation is called
	//Can store register names (alphanumeric), values (numeric) 
	//and operations (+, - or *).
	//Everything is separated by a space.
	//E.g., "result * year - 5 "
	//The n:th element corresponds to the n:th element in registerNames
	private Vector<String> toEvaluate;

	
	
	
	/*--- Constructors ---*/
	
	/*
	 * Default constructor
	 */
	public Registers(){
		
		//Creates Register object with empty list of registers
		registerNames = new Vector<String>();
		toEvaluate = new Vector<String>();
	}
	
	
	
	/*--- Methods ---*/
	

	
	/*
	 * Gets the amount of registers created
	 */
	public int getAmountOfRegisters(){
		return registerNames.size();
	}
	
	/*
	 * Get string that shows what operations are stored in the register with name registerName
	 */
	public String getEvaluationString(String registerName){
		
		//Find index of register registerName
		int index = registerNames.indexOf(registerName);
		
		//Return evaluation string of that register
		String result = toEvaluate.elementAt(index);
		return result;
		
	}
	
	/*
	 * Checks if a register already exists
	 * True if it exists, false otherwise
	 */
	public boolean doRegisterExist(String registerName){
		
		//Vector.indexOf() returns -1 if it can not find the object that equals it's argument
		if(registerNames.indexOf(registerName) == -1){
			return false;
		}
		
		//Vector.indexOf() found the register, and therefore returned something not equal to -1
		return true;
		
	}
	
	/*
	 * Adds register with init value of 0
	 */
	public void addRegister(String registerName){
	
		//Adds register with name registerName
		registerNames.add(registerName);
		
		//Init value of 0 for each newly created register
		toEvaluate.add("0 ");
		
	}
	
	

 	
	/*
	 * Adds operand (as register) to current value of register
	 */
 	public void addToRegister(String registerName, String operand){
		
		//Find index of register registerName
		int index = registerNames.indexOf(registerName);
 		
		//Add to evaluation string for that register
		String tmpString = toEvaluate.elementAt(index);
		tmpString = tmpString + "+ " + operand + " ";
		toEvaluate.set(index, tmpString);


	}
 	
 	
	

	/*
	 * Subtracts operand (as register) from current value of register
	 */
	public void subtractFromRegister(String registerName, String operand){
		
		//Find index of register registerName
		int index = registerNames.indexOf(registerName);
 		
		//Add to evaluation string for that register
		String tmpString = toEvaluate.elementAt(index);
		tmpString = tmpString + "- " + operand + " ";
		toEvaluate.set(index, tmpString);
	}
	
	

	
	/*
	 * Multiply operand (as register) with current value of register
	 */
	public void multiplyWithRegister(String registerName, String operand){
		
		//Find index of register registerName
		int index = registerNames.indexOf(registerName);
 		
		//Add to evaluation string for that register
		String tmpString = toEvaluate.elementAt(index);
		tmpString = tmpString + "* " + operand + " ";
		toEvaluate.set(index, tmpString);
	}

		
	
}
