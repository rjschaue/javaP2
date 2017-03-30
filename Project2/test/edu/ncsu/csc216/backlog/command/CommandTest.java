/**
 * 
 */
package edu.ncsu.csc216.backlog.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command.CommandValue;

/**
 * Tests the Command class
 * @author Joey Schauer
 */
public class CommandTest {

	/**
	 * Tests Command method
	 */
	@Test
	public void testCommand() {
		//Test valid constructor
		Command c = new Command(CommandValue.BACKLOG, "Author", "Text");
		assertEquals(c.getCommand(), CommandValue.BACKLOG);
		assertEquals(c.getNoteAuthor(), "Author");
		assertEquals(c.getNoteText(), "Text");
		//Try constructor with null command value
		try {
			c = new Command(null, "Joey", "Note");
		} catch (IllegalArgumentException e) {
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
			assertEquals(c.getNoteAuthor(), "Author");
			assertEquals(c.getNoteText(), "Text");
		}
		//Try constructor with null note author
		try {
			c = new Command(CommandValue.CLAIM, null, "Note");
		} catch (IllegalArgumentException e) {
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
			assertEquals(c.getNoteAuthor(), "Author");
			assertEquals(c.getNoteText(), "Text");
		}
		//Try constructor with empty string for note author
		try {
			c = new Command(CommandValue.PROCESS, "", "Note");
		} catch (IllegalArgumentException e) {
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
			assertEquals(c.getNoteAuthor(), "Author");
			assertEquals(c.getNoteText(), "Text");
		}
		//Try constructor with null for note text
		try {
			c = new Command(CommandValue.VERIFY, "Joey", null);
		} catch (IllegalArgumentException e) {
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
			assertEquals(c.getNoteAuthor(), "Author");
			assertEquals(c.getNoteText(), "Text");
		}
		//Try constructor with empty string for note text
		try {
			c = new Command(CommandValue.COMPLETE, "Joey", "");
		} catch (IllegalArgumentException e) {
			assertEquals(c.getCommand(), CommandValue.BACKLOG);
			assertEquals(c.getNoteAuthor(), "Author");
			assertEquals(c.getNoteText(), "Text");
		}
	}

}
