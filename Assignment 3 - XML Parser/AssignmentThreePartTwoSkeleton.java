import java.util.*;
import java.io.*;

public class AssignmentThreePartTwo {
	public static void main(String[] args) throws IOException {
		Stack<String> stack = new Stack<String>();
		Queue<String> errorQ = new LinkedList<String>();
		
		String fileName = "test.xml";
		
		// Open the file
		File file = new File(fileName);
		Scanner inFile = new Scanner(file);
		
		// Read from the file
		while (inFile.hasNext()) {
			String line = inFile.nextLine();
			
			// If Prolog_Tag
			if (isPrologTag(line)) {
				// Ignore
			}
			
			// If Self_Closing_Tag
			if (isSelfClosingTag(line)) {
				// Ignore 
				
			}
			
			// If Start_Tag
			if (isStartTag(line)) {
				//Pushon stack
				
			}
			
			
			//If End_Tag
				// If matches top of stack, pop stack and all is well
				// Else if matches head of errorQ, dequeue and ignore
				// Else if stack is empty, add to errorQ
				// Else
					//Search stack for matching Start_Tag
				
					//If stack has match
						// Pop each E from stack into errorQ until match, report as error
					// Else
						// Add E to extrasQ
		}
		
		// If stack is not empty, pop each E into errorQ
		// If either queue is empty (but not both), report each E in both queues as error
		// If both queues are not empty, peek both queues
			// If they don’t match, dequeue from errorQ and report as error
			// Else dequeue from both
		// Repeat until both queues are empty
		
		// Add to the stack
		stack.push("tag");
		
		// Pop off the stack.
		String tag = stack.pop();
		
		// Add to queue
		errorQ.add("tag");
		
		// Dequeue
		String tag = errorQ.poll();
	}
	
	private static boolean isPrologTag(String line) {
		// Check if it's a prolog tag (i.e. <?xml version="1.0" encoding="UTF-8"?>)
	}
	
	private static boolean isSelfClosingTag(String line) {
		// Check if it's self closing (i.e. <tag />)
	}
	
	private static boolean isStartTag(String line) {
		// Check if it's a starting tag (i.e. <tag>)
	}
}