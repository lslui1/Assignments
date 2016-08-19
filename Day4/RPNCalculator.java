package ssa;

import java.util.*;

public class RPNCalculator {

	// Pushes integer to stack
	static void pushInt(Stack st, int a) {
		st.push(Character.getNumericValue(a));
	    //System.out.println("push(" + a + ")");
	    //System.out.println("stack: " + st);
	}
	
	// Pushes operator type to stack
	static void pushOp(Stack st, int a) {
	    st.push(new Integer(a));
	    //System.out.println("push(" + a + ")");
	    //System.out.println("stack: " + st);
	}

	// Pops the operator from the stack
	static char popOperator(Stack st) {
		 //System.out.print("pop operator-> ");
	     Integer a = (Integer) st.pop();
	     //System.out.println(a);
	     char op = isOperator(a);
	     //System.out.println("stack: " + st);
	     return op;
	}
	
	// Pops number from the stack
	static int popInt(Stack st) {
	     //System.out.print("pop int -> ");
	     int a = (int)st.pop();
	     //System.out.println(a);
	     //System.out.println("stack: " + st);
	     return a;
	}
	
	// Checks and returns operator type as a char
	static char isOperator(Integer op) {
		char opType = ' '; 
		switch (op) {
        case 43:  {
        	opType = '+';
        	break;
        }
        case 45:  {
        	opType = '-';
        	break;
        }
        case 42:  {
        	opType = '*';
        	break;
        }
        case 47:  {
        	opType = '/';
        	break;
        }
        }
		return opType;
	}
	

	//Perform calculation
	static int performOp(int arg1, int arg2, char opType) {
		int result = 0;
		
		//System.out.printf("arg1 is %d. arg2 is %d. Op is %c.\n", arg1, arg2, opType);	
		switch (opType) {
        case '+':  {
        	result = arg1 + arg2;
        	//System.out.println("Performing addition");
        	break;
        }
        case '-':  {
        	result = arg1 - arg2;
        	//System.out.println("Performing subtraction");
        	break;
        }
        case '*':  {
        	result = arg1 * arg2;
        	//System.out.println("Performing multiplication");
        	break;
        }
        case '/':  {
        	result = arg1 / arg2;
        	//System.out.println("Performing division");
        	break;
        }
        }
		return result;
	}
	
	public static void main(String[] args) {
		
		int arg1;
		int arg2;
		int opResult = 0;
		char operator;

		//char[] problem = new char[] {'6','2','/','1','-','2','*','4','+','3','/'};		
		char[] problem = new char[] {'1','2','+','3','+','2','*','4','/'};	
		
		Stack<Integer> stk = new Stack<Integer>();
		//System.out.println("stack: " + stk);
		
		// Push the problem to the stack
		for (int probIndex=0 ; probIndex < problem.length; probIndex++) {
			if (Character.getNumericValue(problem[(problem.length - 1) - probIndex]) == -1) {
				pushOp(stk, problem[(problem.length - 1) - probIndex]);
			}
			else {
				pushInt(stk, problem[(problem.length - 1) - probIndex]);
			}
		}

		// Pop from the stack and perform calculations
		while (!(stk.empty())) {				
			arg1 = popInt(stk);
			arg2 = popInt(stk);
			operator = popOperator(stk);
			System.out.printf("Calculating %d,%d,%c. ", arg1, arg2, operator);			
			opResult = performOp(arg1, arg2, operator);
			System.out.printf("The answer is %d.\n", opResult);
			
			// Check for empty stack. If not empty, push the result back in and continue loop
			if (!(stk.empty())) {
				System.out.printf("Stack is not empty. Pushing %d back in.\n", opResult);
				stk.push(opResult);
			}
		}
		
		stk.push(opResult);
		System.out.println("The final answer is " + opResult);
		System.out.println("stack: " + stk);
	}
	
}
