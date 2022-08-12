package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import models.Tag;
import utilities.MyQueue;
import utilities.MyStack;

/**
 * Driver class
 * @author Nic Kelly
 */
public class AppDriver {
	
	/**
	 * Main method where application starts and ends.
	 * @param args args[0] should contain the file name.
	 * @throws IOException if the file couldn't be opened/found.
	 */
	public static void main(String[] args) throws IOException {
		MyStack<Tag> stack = new MyStack<>();
		MyQueue<Tag> queue = new MyQueue<>();

		String fileName = args[0];
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String line;

		/*
		 * Read through each line and then loop through all the characters on that line while finding tags and constructing them
		 * Tags are then processed to a stack and queue.
		 */
		while ((line = in.readLine()) != null) {
			line = line.trim();

			String tag = "";
			String type = null;
			Boolean addTag = false;

			char[] characters = line.toCharArray();

			for (int i = 0; i < characters.length; i++) {

				char current = characters[i];

				if (current == '<') {
					addTag = true;
					char next = (characters[i + 1]);

					if (next == '/') {
						type = "End";
					} else if (next == '?') {
						type = "Prologue";
					} else if (characters[line.indexOf('>') - 1] == '/') {
						type = "SelfClosing";
					} else {
						type = "Start";
					}
				}

				if (current == ' ' || current == '>') {
					addTag = false;
					tag += '>';
					i = line.lastIndexOf('>') + 1;
				}

				if (addTag) {
					tag += current;
				}
			}

			if (tag != "") {

				Tag tagFound = new Tag(tag, line, type);

				if (type.equals("Start")) {
					queue.push(tagFound);
				} else if (type.equals("End")) {
					stack.push(tagFound);
				}
			}
		}

		in.close();

		Boolean noErrors = true;

		/*
		 * This tests the order of the stack and queue. When there is an error the file will not parse properly.
		 * The lines where errors were are then reported back to the console.
		 */
		while (!queue.isEmpty()) {
			while (!stack.isEmpty()) {

				if (((Tag) queue.peek()).getTagName().equals(((Tag) stack.peek()).getTagName())) {
					queue.pop();
					stack.pop();
				} else {
					noErrors = false;
					System.out.println("Tags don't match");
					System.out.println(((Tag) queue.pop()).getLine());
					System.out.println(((Tag) stack.pop()).getLine());
					System.out.println("");
				}
			}

			if (!queue.isEmpty()) {
				System.out.println("\n\nError on line \n" + ((Tag) queue.pop()).getLine());
			}
		}

		if (noErrors) {
			System.out.println("Parsed " + fileName + " no errors were found.");
		}
	}
}