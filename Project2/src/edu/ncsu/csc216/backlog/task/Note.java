package edu.ncsu.csc216.backlog.task;

/**
 * This class encapsulates the author and text of a user's note
 * @author Joey Schauer
 */
public class Note {
	/** This gets the author of the note */
	private String noteAuthor;
	/** This gets the text for the note */
	private String noteText;
	
	/**
	 * This is the constructor for Note
	 * @param noteAuthor the author of the note
	 * @param noteText the text for the note
	 * @throws IllegalArgumentException if either parameter is null or empty
	 */
	public Note(String noteAuthor, String noteText) {
		if (noteAuthor == null || noteAuthor.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (noteText == null || noteText.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.noteAuthor = noteAuthor;
		this.noteText = noteText;
	}
	
	/**
	 * Returns the author of the note
	 * @return the author of the note
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * Reutrns the text for the note
	 * @return the text for the note
	 */
	public String getNoteText() {
		return noteText;
	}
	
	/**
	 * Returns the note author and text as an array
	 * @return the note author and text as an array
	 */
	public String[] getNoteArray() {
		String[] noteArray = new String[2];
		noteArray[0] = noteAuthor;
		noteArray[1] = noteText;
		return noteArray;
	}
}
