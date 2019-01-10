package calculation;

import java.util.*;



public class MyStack {

	/*Step 1: Define three data members for this class
	 * Data member 1: is called "total_stacks" which is used
	 * to record how many MyStack objects have been created;
	 *  
	 * Data member 2: is called "id" represents the id number
	 * the current MyStack object. Each created MyStack object
	 * should have one unique id number, which is assigned at
	 * the time when the object is created. You can use the total_stacks
	 * as a reference. For example, the first created MyStack object has ID = 0;
	 * The second created MyStack Object has ID = 1, and so forth
	 * 
	 * Data member 3: is called "stack", which is the Java supported Stack.
	 * This is the main variable to store the expression.
	 * 
	 * For these three data members, you should determine whether it is a 
	 * "static" or "non-static" according to its role as mentioned above
	 * 
	 * Try to use the "Generate Setters and Getters" tool in the "Source" menu to 
	 * create the three pairs of setters and getters automatically*/

	
	
	
	//Do the step 1 here
	
	private static int total_stacks ;
	private int id;
	private Stack<String> stack = new Stack<>();
	
	public int getTotal_stacks() {
		return total_stacks;
	}


	public void setTotal_stacks(int total_stacks) {
		this.total_stacks = total_stacks;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Stack<String> getStack() {
		return stack;
	}


	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}



	
	
	
	
	
	
	
	
	/*Step 2: Create two constructors of MyStack(). For both of the constructors, you
	 * need to make sure to assign the ID for the created object. Meanwhile, maintain
	 * the number of "total_stacks" globally, which means to increase it by one every time when a
	 * new object is created 
	 * 
	 * Constructor 1: this constructor has no input arguments. You need to update the
	 * related variables as mentioned above. Also print out a message, e.g. "A stack with 
	 * the id #5 is created" (do this at the end of the constructor) 
	 * 
	 * Constructor 2: this constructor has one input argument - "String exp". In addition to finishing the
	 * tasks as constructor 1 does, it also push the expression as string type into the 
	 * stack data member by simply calling addItem() member function. 
	 * */
	
	
	

	
	//Do the step 2 here

	public MyStack() {
		total_stacks ++;
		id = total_stacks;
		System.out.println("A stack with the id # " + this.id + " is created.");
		//System.out.println("total stacks = " + getTotal_stacks());
	}
	
	public MyStack(String exp) {
		total_stacks += 1;
		this.id ++;
		addItem(exp);
	}
	
	
	/*Step 3: complete the functions below*/
	
	/*remove the top item (String) from the stack data member*/
	public void removeItem()
	{
		//Implement here
		stack.pop();
		
	}

	
	/*Print out all the items of the stack by printing each one in a new line 
	 * For example, for an expression 5 * 18 + 21
	 * 			   [0] 5
	 * 			   [1] *
	 * 			   [2] 18
	 * 			   [3] +
	 * 			   [5] 21
	 * If you use the stack API directly, you probably can only access the items in the
	 * order from the top to the bottom. So to print them out in the order as the items
	 * are inserted, you need to use the Iterator class, which is returned from the stack.
	 * You need to look it up online on how to use Iterator class*/ 
	public void showItems()
	{
		//Implement here
		Iterator<String> itr = stack.iterator();
		while(itr.hasNext()) {
			String element = (String)itr.next();
			System.out.println("[" + stack.indexOf(element) + "]" + element);
		}
	}
	/* Return the top character of the stack, you can't remove the top item but just read it*/
	public String getTopItem()
	{
		return stack.peek();
	}
	
	//Return how many items are there in the stack
	public int getSize()
	{
		//Implement here here
		return stack.size();
	}
	
	/*Step 4: as described in the instruction. This is the most important function for this class
	 * The role is to process the input String, and store them into the stack as items*/
	public void addItem(String exp)
	{
		
		ArrayList<Character> toStack = new ArrayList<>();
		exp.chars().mapToObj( i-> (char)i )
					.filter( c -> Character.isDigit(c) || isSign(c) || c == '.')
						.forEach( c -> toStack.add(c) );	

		int isSign = 0;
		int wasSign = 0;
		StringBuffer builder = new StringBuffer();
		ArrayList<Character> sublist;
		if(!(containSigns(toStack)))
		{
			for(char ch : toStack) {
				builder.append(ch);
			}
			stack.push(builder.toString());	
			//stack.push(String.valueOf(toStack.get(i)));
			builder.setLength(0);
		}
		else {
			for(int i = 0; i < toStack.size(); i++) {
				//System.out.println(i+" : "+toStack.get(i));
				if (isSign(toStack.get(i))) {
					isSign = i;
					// convert array list to sublists.
					// assign them to a string buffer
					// convert from string buffer to string
					// add them to stack.
					
					if(wasSign <1) {
						sublist = new ArrayList<>(toStack.subList(wasSign , isSign));
						if(checkDecimalPts(sublist)) {
							// to call a new function that will fix the decimal point bug.
							processList(sublist);
						}
					}
					else {
						//System.out.println((wasSign+1)+","+(isSign));
						sublist = new ArrayList<>(toStack.subList(wasSign+1 , isSign));
						if(checkDecimalPts(sublist)) {
							// to call a new function that will fix the decimal point bug.
							processList(sublist);
						}
					}
//					for(char c:sublist)
//					{
//						System.out.println("Sublist: "+c);
//					}
					
					wasSign = isSign;
					for(char ch : sublist) {
						builder.append(String.valueOf(ch));
					}
					stack.push(builder.toString());	
					stack.push(String.valueOf(toStack.get(i)));
					builder.setLength(0);
				}
				
				//end
				if(i == toStack.size()-1 && isSign != i ) {
					sublist = new ArrayList<>(toStack.subList(wasSign+1, i+1));
					for(char ch : sublist) {
						builder.append(String.valueOf(ch));
					}
					stack.push(builder.toString());
				}
			}
		}
	}
	public boolean containSigns(ArrayList<Character> toStack) {
		for(char ch: toStack) {
			if(ch == '/'|| ch== '*' || ch == '-' || ch == '+')
			return true;
		}
		return false;
	}
	public boolean isSign(char elementOfList) {
		if(elementOfList == '/'|| elementOfList== '*' || elementOfList == '-' || elementOfList == '+')
			return true;
		
		return false;
	}
	public ArrayList<Character> hasDoubleDecimalPoints(ArrayList<Character> subList){
		
		return subList;
	}
	public boolean checkDecimalPts(ArrayList<Character> sublist) {
		int isPoint = 0;
		for(char ch: sublist) {
			if(ch == '.') {
				isPoint++;
			}
			if(isPoint >1) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<Character> processList(ArrayList<Character> subList){
		int isPoint = 0;
		//int wasPoint = 0;
		int points = 0;
		//List<Character> temp = List.of('+','0');
		for(int i = 0; i < subList.size(); i++) {
//			if (subList.get(i) == '.' && isSign(subList.get(i - 1)) && isSign(subList.get(i + 1))) {
//				subList.remove(i);
//			}
			if(subList.get(i) == '.') {
				isPoint = i;
				points++;
				if(points>1 && points < subList.size() && !(i == subList.size()-1 && isPoint == i )) {
					subList.add(isPoint, '0');
					subList.add(isPoint, '+');	
					break;
				}
				if(i == subList.size()-1 && isPoint == i ) {
					subList.remove(i);
					continue;
				}
			}	
		}

	return subList;
	}
	public Stack<String> checkStack(Stack<String> finalStack){
		Iterator<String> itr = finalStack.iterator();
		while(itr.hasNext()) {
			String element = itr.next();
		}
		return finalStack;
	}		
}
	
