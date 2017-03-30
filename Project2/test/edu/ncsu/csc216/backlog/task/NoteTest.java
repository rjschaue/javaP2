/**
 * 
 */
package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Note class
 * @author Joey Schauer
 */
public class NoteTest {

	/**
	 * Tests Note
	 */
	@Test
	public void testNote() {
		//Test valid constructor
		Note n = new Note("Author", "Text");
		assertEquals(n.getNoteAuthor(), "Author");
		assertEquals(n.getNoteText(), "Text");
		String[] s = {"Author", "Text"};
		assertEquals(n.getNoteArray()[0], s[0]);
		assertEquals(n.getNoteArray()[1], s[1]);
		//Test null author
		try {
			n = new Note(null, "Note");
		} catch(IllegalArgumentException e) {
			assertEquals(n.getNoteAuthor(), "Author");
			assertEquals(n.getNoteText(), "Text");
			assertEquals(n.getNoteArray()[0], s[0]);
			assertEquals(n.getNoteArray()[1], s[1]);
		}
		//Test empty author string
		try {
			n = new Note("", "Note");
		} catch(IllegalArgumentException e) {
			assertEquals(n.getNoteAuthor(), "Author");
			assertEquals(n.getNoteText(), "Text");
			assertEquals(n.getNoteArray()[0], s[0]);
			assertEquals(n.getNoteArray()[1], s[1]);
		}
		//Test null text
		try {
			n = new Note("Joey", null);
		} catch(IllegalArgumentException e) {
			assertEquals(n.getNoteAuthor(), "Author");
			assertEquals(n.getNoteText(), "Text");
			assertEquals(n.getNoteArray()[0], s[0]);
			assertEquals(n.getNoteArray()[1], s[1]);
		}
		//Test empty text string
		try {
			n = new Note("Joey", "");
		} catch(IllegalArgumentException e) {
			assertEquals(n.getNoteAuthor(), "Author");
			assertEquals(n.getNoteText(), "Text");
			assertEquals(n.getNoteArray()[0], s[0]);
			assertEquals(n.getNoteArray()[1], s[1]);
		}
	}

}
