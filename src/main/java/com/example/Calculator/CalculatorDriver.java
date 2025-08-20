package com.example.Calculator;

import java.util.ArrayList;

import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.textfield.TextArea;


@SuppressWarnings("serial")
public class CalculatorDriver {
	
	private static String input = "0"; private static String lastInput; private static String lastOperator;
	private static String output = "";
	private static String equation ="";
	private static String result; 
    public static String lastResult;
	private static boolean isLastPressEqual = false;
    private static boolean isSyntaxError = false;
    private static int isFinalOp = 0;
	public static int numberOfInputs = 0; //numbers entered in operation
	private static boolean outputDelete = false; //when editing entry
	final static String validNum = "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?((\\))?)+"
			+ "((\\((-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
			+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+((\\))?)+"
			+ "((\\((-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
			+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?((\\))?)+"
			+ "((\\((-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
			+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(([\\)\\()])?)+)?)+"
			+ "|\\)";

    public static Input inputDisplay;
    public static Input outputDisplay;
    public static TextArea history;
    private static String historyText = "";

    public static void initializeDriver(Input input, Input output) {
        inputDisplay = input;
        outputDisplay = output;
    }

    public static void initializeHistoryField(TextArea historyfield) {
        history = historyfield;
    }

    private static void appendHistory() {
        historyText += "\n"+result+"\n\n";
		System.out.println("history:"+historyText);
        history.setValue(historyText);
    }
	
	//Operations
		private static double mult(double num1, double num2) {
			return num1 * num2;
		}
		private static double div(double num1, double num2) {
			return num1 / num2;
		}
		private static double add(double num1, double num2) {
			return num1 + num2;
		}
		private static double sub(double num1, double num2) {
			return num1 - num2;
		}
		private static double exponent(double num1, double num2) {
		return Math.pow(num1, num2);
	}
	
	//user input in the calculator
	public static void buttonPress(String button) {
		System.out.println("button pressed " + button);
	
		if (button.matches("[0-9\\.]") || button.equals("\u03C0")) {
			numberEntry(button);
		} 
		else if (button.matches("[-x÷/\\^\\+]")) {
			
			if (input.substring(input.length()-1).equals(".")) {//if last char is a dot remove it
				input = input.substring(0, input.length()-1);
				operatorEntry(button);
			}
			else operatorEntry(button);
			
			lastOperator = button;
		}
		else if (button.equals("=")) {
			if (isLastPressEqual) {
				System.out.println("isLastPressEqual:"+isLastPressEqual);
				String tempInput = lastInput;
				input = result;
				operatorEntry(lastOperator);
				System.out.println("last operator: "+lastOperator);
				input = tempInput;
				operatorEntry(button);
				equation = output;
				historyText += equation;
				getResult(output);
                if (!isSyntaxError) appendHistory();	
                isSyntaxError = false;	
            }
			else {
				operatorEntry(button);
				equation = output;
				historyText += equation;
				getResult(output);
				historyText += output;
                if (!isSyntaxError) appendHistory();
                isSyntaxError = false;
			}
		}
		else otherOperatorEntry(button);			
	}
	
	private static void numberEntry(String numEntered) {
		
		isLastPressEqual = false;
		
		if (outputDelete == true) {
			
			output += " "+numEntered;
			outputDisplay.setValue(output);
  
		}
		
		else switch (numEntered) {
			
			case "0": //initially zero, if enter zero, nothing happens
				if (input.contains("-")) {
					input = (input.substring(0,2).contains("0")) ? "-":input ;
					input += numEntered;
					break;
				} 
				else {
					if (input.equals("0")) break;
					else { 
						input += numEntered;
						inputDisplay.setValue(input);
						break;
					}
				}
					
			case ".": //only allow one dot
				if (!input.contains(".")) {
						input += numEntered;
						inputDisplay.setValue(input);
						break;
					}
					else break;
				
			default :
				if (input.contains("-")) {
					input = (input.substring(1,2).equals("0") && !input.contains(".")) ? "-":input ;
					input += numEntered;
				} 
				else {
					input = (input.equals("0")) ? "":input ;
					input += numEntered;	
				}
				
				inputDisplay.setValue(input);
                
			}
		
	}

	private static void operatorEntry(String operator) {
		
		isLastPressEqual = false;
		
		if (outputDelete == true) {
			input = " "+operator+" ";
			output += input;
			outputDisplay.setValue(output);
			input = "0";//reset input field
			
		} 
		else {
			numberOfInputs++;
			lastInput = input;//store last input number
			
			System.out.println("last intput: " + lastInput);
			if (input.equals("0")) input = "";
			input += (operator.matches("[/\\)\\(\\^]")) ? operator : " "+operator+" ";
			output += input;
			outputDisplay.setValue(output); 
			input = "0";//reset input field
			inputDisplay.setValue(input);
		}
		
		outputDelete = false;
	}
	
	private static void otherOperatorEntry(String operator) {
		
		isLastPressEqual = false;
		
		//for sign, parenthesis, C, DEL [...]
		System.out.println("otherOperatorEntry");
		switch (operator) {
		case "C" : //reset
			output = "";
			input = "0";
			numberOfInputs = 0;
			outputDisplay.setValue(output);
			inputDisplay.setValue(input);
			break;
			
		case "DEL": //delete
			//if last press was operator
				try {
					if (lastOperator.matches("[-x÷\\+\\^]") || outputDelete == true) {
						if (outputDelete==false) {
							output = output.substring(0, output.length()-2).trim();
							outputDisplay.setValue(output);
							System.out.println("deleted entry in output");
							outputDelete = true;
						} 
						else {
							output = output.substring(0, output.length()-1).trim();
							outputDisplay.setValue(output);
							System.out.println("deleted entry in output");
							outputDelete = true;
						}
					}
					break;
				}
				catch (NullPointerException npe) {
					System.out.println("excepption:nothing to delete");
				} 
				catch (IndexOutOfBoundsException ioobe) {
					System.out.println("excepption:cannot edit result");
				} break;
			
		case "sign":
			outputDelete = false;
            System.out.println("sign");
			if (!input.contains("-")) {
				input = "-"+input;
				inputDisplay.setValue(input);
				break;
			}
			else {
				input = input.substring(1);
				inputDisplay.setValue(input);
				break;
			}
			
		case "(" :
		case ")" :
            if (outputDelete == true) {
                if (input.equals("0")) {
                    output = output.trim()+operator;
                    outputDisplay.setValue(output);
                }
                else {
                    operatorEntry(operator);
                }
                outputDelete = false;
                break;
            }

			if (input.equals("0")) {
                output = output+operator;
                outputDisplay.setValue(output);
			}
			else {
				operatorEntry(operator);
			}
            break;
		}
	};
	
 	private static double getResult(String entry) {

		Equation eqObj = new Equation(entry);

		System.out.println("equation: "+eqObj); 
        System.out.println("getResult: "+eqObj); 
        
		//operatorEntry("=");
		//System.out.println(entry = output); 
		isLastPressEqual = true;
		/*if (output.matches("(\s)?[-x÷=\\+(\\^)](\s)?[-x÷=\\\\+(\\\\^)](\\s)?")) {
			System.out.println
		}*/
		
		//check syntax
			if (isSyntaxValid(eqObj.getString()) == false) { 
				System.out.println(eqObj); 
				System.out.println("error:syntax"); 
				outputDisplay.setValue("error:syntax");
				input = "0";
				output = "";
                isSyntaxError = true;
				return 0; 
				}
		
		while (eqObj.getString().contains("(")) eqObj.setString(removeParenthesis(eqObj.getString()));
			System.out.println("entryNoParenthesis?: "+eqObj);
			
		ArrayList<String> numbers_operators = new ArrayList<>();
		numbers_operators = eqObj.trim().splitWithDelimiters("[-x÷\\+]|(\\^)|/|=", numbers_operators);
		ArrayList<String> operators = new ArrayList<>();
		ArrayList<String> numbers = new ArrayList<>();
		
		for (int i=0; i<numbers_operators.size(); i++) {
			if (numbers_operators.get(i).matches("^(\s)?[-x÷\\+](\s)?$|(\\^)|/"))
				operators.add(numbers_operators.get(i).trim());
			else if (numbers_operators.get(i).matches("^(-)?[0-9]+(.[0-9]+)?(E[0-9]+)?$"))numbers.add(numbers_operators.get(i).trim());
		}
		
		System.out.println("numbers: " + numbers);
		System.out.println("operators: " + operators);
		
	
		output = "";//reset output
		System.out.println("searchOperation.return:"+searchOperation(numbers, operators));
		System.out.println("getResult.return:"+ Double.parseDouble(result));
		System.out.println("results: " + numbers);
		System.out.println("operators left: " + operators);
		
        System.out.println(isFinalOp);
		return Double.parseDouble(result);
		
	}

 	private static String removeParenthesis(String entry) {

		Equation eqObj = new Equation(entry);
 		System.out.println("removeParenthesisOf: "+eqObj);
 		
 		ArrayList<String> num_op = new ArrayList<>();
		num_op = eqObj.splitWithDelimiters("[\\(\\)=]", num_op);

	 		System.out.print("num_op_split: [");
	 		for (String element : num_op) {
	 			System.out.print(element+",");
	 			}
	 		System.out.println("]");
	 		
	 		int open_p = 0;
	 		int close_p = 0;
	 	ArrayList<String> num_opAL = new ArrayList<>();
		 	for (String element : num_op) {
		 		if (!element.equals("")) num_opAL.add(element);
		 		if (element.equals(")")) close_p++;
		 		if (element.equals("(")) open_p++;
		 	}
		 	
		 if (open_p != close_p) {
			 System.out.println(entry); 
				System.out.println("error:syntax"); 
				outputDisplay.setValue("error:syntax");
				input = "0";
				output = "";
				return "0";
		 }
		 	
		 	for (int i = 0; i<num_opAL.size()-1; i++) {
		 			String str = num_opAL.get(i);
		 			String strNext = num_opAL.get(i+1);
		 		if (str.matches("[0-9\\)]") || str.matches(validNum) || str.substring(str.trim().length()).matches("[0-9\\)]")) {
		 			System.out.println("checknext");
		 			if (strNext.equals("(") || strNext.substring(0,1).matches("[0-9]")) {
		 				System.out.println("add x");
		 				num_opAL.add(i+1, " x ");
		 			}
		 		}
		 	}
 		
	 		//find index of last open parenthesis
	 		int indexOfLast = num_opAL.lastIndexOf("(");
	 		
	 		//get result of operation in parenthesis
	 		num_opAL.set(indexOfLast+1,""+getResult(num_opAL.get(indexOfLast+1)));
	 		//remove parenthesis for which value was calculated
	 		num_opAL.remove(indexOfLast);
	 		num_opAL.remove(indexOfLast+1);
 		
	 		String resultNoParenthesis = "";
	 		System.out.print("num_opAL: [");
		 		for (String element : num_opAL) {
			 			System.out.print(element+",");
			 			resultNoParenthesis += element;
		 		}
		 	System.out.println("]");
		 	System.out.println(resultNoParenthesis);
 		
 		return resultNoParenthesis;	
 	}
 	
	private static double searchOperation(ArrayList<String> numbers , ArrayList<String> operators) {
		
		String mult = "x";
		String div = "÷";
		String add = "+";
		String sub = "-";
		String exp = "^";
		String frac = "/";
		
		int index = -1;
		double[] numbersToSend = new double [2];
		
		while (numbers.size() > 1) {
			//fractions are like div in parenthesis
			for (int i=0; i < operators.size(); i++) {
				
				if (operators.get(i).equals(frac)) {
					index = i;
					numbersToSend = getNumbers(index, numbers, operators);
					result = ""+CalculatorDriver.div(numbersToSend[0], numbersToSend[1]);
					numbers.add(index, result);
					continue;
				}	
			}
			
			//exponents 
			for (int i=0; i < operators.size(); i++) {
					index = -1;
						if (operators.get(i).equals(exp)) {
							index = i;
							numbersToSend = getNumbers(index, numbers, operators);
							result = ""+CalculatorDriver.exponent(numbersToSend[0], numbersToSend[1]);
							numbers.add(index, result);
							continue;
						}
				}
			
			//first look for multiplication
			if (index == -1) {
				for (int i=0; i < operators.size(); i++) {
					index = -1;
					if (operators.get(i).equalsIgnoreCase(mult)) {
						if(i != 0 && operators.get(i-1).equals(div)) break;//check if previous operator division 
						else {
							index = i;
							numbersToSend = getNumbers(index, numbers, operators);
							result = ""+CalculatorDriver.mult(numbersToSend[0], numbersToSend[1]);
							numbers.add(index, result);
							continue;
						} 
					}
				}
				
			}
			//second look for division
			if (index == -1) {
				for (int i=0; i < operators.size(); i++) {
					index = -1;
					if (operators.get(i).equals(div)) {
						if(i != 0 && operators.get(i-1).equals(mult)) break; //check if previous operator multiplication
						else {
							index = i;
							numbersToSend = getNumbers(index, numbers, operators);
							result = ""+CalculatorDriver.div(numbersToSend[0], numbersToSend[1]);
							numbers.add(index, result);
							continue;
						}
					}
				}
			}
			//third look for addition
			if (index == -1) {
				for (int i=0; i < operators.size(); i++) {
					index = -1;
					if (operators.get(i).equals(add)) {
						if(i != 0 && operators.get(i-1).equals(sub)) break; //check if previous operator substraction
						else {
							index = i;
							numbersToSend = getNumbers(index, numbers, operators);
							result = ""+CalculatorDriver.add(numbersToSend[0], numbersToSend[1]);
							numbers.add(index, result);
							continue;
						}
					}
				}
			}
			//fourth look for substraction
			if (index == -1) {
				for (int i=0; i < operators.size(); i++) {
					index = -1;
					if (operators.get(i).equals(sub)) {
						if(i != 0 && operators.get(i-1).equals(add)) break; //check if previous operator substraction
						else {
							index = i;
							numbersToSend = getNumbers(index, numbers, operators);
							result = ""+CalculatorDriver.sub(numbersToSend[0], numbersToSend[1]);
							numbers.add(index, result);
							continue;
						}
					}
				}
			}
		}
		if (index == -1) result = numbers.get(0);
		lastResult = result;
		inputDisplay.setValue(result);
		return Double.parseDouble(result);
	}
	
	private static double[] getNumbers(int index, ArrayList<String> numbers, ArrayList<String> operators ) {
		
		double[] numbersToReturn = new double[2];
		numbersToReturn[0] = Double.parseDouble(numbers.get(index));
		numbersToReturn[1] = Double.parseDouble(numbers.get(index+1));
		
		operators.remove(index);
		numbers.remove(index);
		numbers.remove(index);
		
		return numbersToReturn;
	}

	private static boolean isSyntaxValid(String operation) {
		//String validSyntaxRegex = "[0-9]+(((\s)?[xX\\-÷\\+\\^/](\s)?[0-9]+)?)+\s[=]\s";
		final String validSyntaxParenthesisRegex = 
		  "((\\()?)+(-)?([0-9]|(\u03C0))+(.[0-9]+)?(E(-)?[0-9]+)?((\\))?)+"
		+ "(((\\()+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
		+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+((\\))?)+"
		+ "(((\\()+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
		+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?((\\))?)+"
		+ "(((\\()+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?"
		+ "((\\()?)+(-)?[0-9]+(.[0-9]+)?(E(-)?[0-9]+)?)?)+(([\\)\\()])?)+)?)+"
		+ "(\s[=]\s)?";
		
		//this regex pattern allows for input with parenthesis
		//"((\\()?)+[0-9]+((\\))?)+((\\([0-9]+)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?((\\()?)+[0-9]+)?)+((\\))?)+((\\([0-9]+)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?((\\()?)+[0-9]+((\\))?)+((\\([0-9]+)?)+(((\s)?[xX\\-÷\\+\\^/](\s)?((\\()?)+[0-9]+)?)+(([\\)\\()])?)+)?)+\s[=]\s"
			if (operation.matches(validSyntaxParenthesisRegex)) return true;
			else return false;
	}
}

