package test;

import calculation.*;

public class Main {

	
	public static void main(String[] args)
	{
		MyStack[] stacks = new MyStack[5];

		for (int i = 0; i < 5; i++) {
			stacks[i] = new MyStack();
		}
		
		//testing stacks[2]
		System.out.println("***************** Case 1 **********************");
		stacks[2].addItem("1");
		System.out.println("stacks[2] has " + stacks[2].getSize() + " item(s)");
		stacks[2].showItems();
		
		//testing s2
		System.out.println("*****************  Case 2 **********************");
		MyStack s2 = new MyStack("5 + 2 + 32 * 10");
		System.out.println("s2 has " + s2.getSize() + " item(s)");
		s2.showItems();
		ProcessStack.parseMyStack(s2);
		
		

		//testing s3
		System.out.println("****************** Case 3 *********************");
		MyStack s3 = new MyStack("5+2+32*10");
		System.out.println("s3 has " + s3.getSize() + " item(s)");
		s3.showItems();
		
		

		//testing s4
		System.out.println("******************* Case 4 ********************");
		MyStack s4 = new MyStack("d5 ~c+ 2$+a32*10b");
		System.out.println("s4 has " + s4.getSize() + " item(s)");
		s4.showItems();
		ProcessStack.parseMyStack(s4);
		

		//testing s5
		System.out.println("******************* Case 5 ********************");
		MyStack s5 = new MyStack("d5.3. c+ 22.5^+a31.2*10b");
		System.out.println("s5 has " + s5.getSize() + " item(s)");
		s5.showItems();
		ProcessStack.parseMyStack(s5);

		//testing s6
		System.out.println("******************* Case 6 ********************");
		MyStack s6 = new MyStack();
		s6.addItem("d5.3.6 c/0.215+ 22.5+a31.2*1%21b");
		System.out.println("s6 has " + s6.getSize() + " item(s)");
		s6.showItems();
		ProcessStack.parseMyStack(s6);
		

		//testing s7
		System.out.println("******************* Case 7 ********************");
		MyStack s7 = new MyStack();
		s7.addItem("d5.3.6 c/0.2 15+ 22. 5+a31.2 *10b +./8");
		System.out.println("s6 has " + s7.getSize() + " item(s)");
		s7.showItems();
		s7.addItem("502 + 123 -- *0.5ba.5.7-");
		s7.showItems();
	    
		MyStack s8 = new MyStack("1-5+32-2");
		s8.showItems();
		ProcessStack.parseMyStack(s8);
	}
}
