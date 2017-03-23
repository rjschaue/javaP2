package edu.ncsu.csc216.backlog.command;

/**
 * This class encapsulates the user's actions
 * @author Joey Schauer
 */
public class Command {
	/** This gets the command value for the user's action */
	private CommandValue c;
	/** This gets the author of the task note */
	private String noteAuthor;
	/** This gets the text for the task note */
	private String noteText;
	
	/**
	 * This is the constructor for Command
	 * @param c the command value for the action
	 * @param noteAuthor the author of the task note
	 * @param noteText the text for the task note
	 * @throws IllegalArgumentException if any of the parameters are null or empty
	 */
	public Command(CommandValue c, String noteAuthor, String noteText) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		if (noteAuthor == null || noteAuthor.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (noteText == null || noteText.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.c = c;
		this.noteAuthor = noteAuthor;
		this.noteText = noteText;
	}
	
	/**
	 * Returns the command value
	 * @return the command value
	 */
	public CommandValue getCommand() {
		return c;
	}
	
	/**
	 * Returns the note text
	 * @return the note text
	 */
	public String getNoteText() {
		return noteText;
	}
	
	/**
	 * Returns the note author
	 * @return the note author
	 */
	public String getNoteAuthor() {
		return noteAuthor;
	}
	
	/**
	 * This is an enum used to represent the different command values a user can choose
	 * @author Joey Schauer
	 */
	public static enum CommandValue { BACKLOG, CLAIM, PROCESS, VERIFY, COMPLETE, REJECT }
}
