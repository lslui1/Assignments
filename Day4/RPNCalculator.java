package ssa;

import java.util.*;

public class RPNCalculator {

	// Pushes type to stack
	static void showpush(Stack st, int a) {
	     st.push(new Integer(a));
	     //System.out.println("push(" + a + ")");
	     //System.out.println("stack: " + st);
	}

	// Pops the operator from the stack and returns it
	static char popOperator(Stack st) {
		 //System.out.print("pop operator-> ");
	     Integer a = (Integer) st.pop();
	     //System.out.println(a);
	     char op = isOperator(a);
	     //System.out.println("stack: " + st);
	     return op;
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
	
	// Pops from the stack returning an int type
	static int showpop(Stack st) {
	     //System.out.print("pop -> ");
	     Integer a = (Integer) st.pop();
	     //System.out.println(a);
	     int NumericA = Character.getNumericValue(a);
	     //System.out.println("stack: " + st);
	     return NumericA;
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
		int opResult;
		char operator;

		char[] problem = new char[] {'6','2','/','1','-','2','*','4','+','3','/'};		
		
		Stack<Integer> stk = new Stack<Integer>();
		//System.out.println("stack: " + stk);
		
		// Push the problem to the stack
		for (int probIndex=0 ; probIndex < problem.length; probIndex++) {
			showpush(stk, problem[(problem.length - 1) - probIndex]);
		}

		// Pop from the stack and perform calculations
		while (!(stk.empty())) {				
			arg1 = showpop(stk);
			arg2 = showpop(stk);
			operator = popOperator(stk);
			System.out.printf("Calculating %d,%d,%c\n", arg1, arg2, operator);			
			opResult = performOp(arg1, arg2, operator);
			System.out.printf("The result is %d.\n", opResult);
			
			// Check for empty stack. If not empty, push the result back in and continue loop
			if (stk.empty()) {
				System.out.println("The stack is empty.");
			}
			else {
				char b = Integer.toString(opResult).charAt(0);
				System.out.printf("Stack is not empty. Pushing %c back in.\n", b);
				showpush(stk, b);
			}
		}
	}
	
}
