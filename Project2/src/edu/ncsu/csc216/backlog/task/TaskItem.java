package edu.ncsu.csc216.backlog.task;

/**
 * This class maintains all of the history of progression throw the FSM
 * @author Joey Schauer
 */
public class TaskItem {
	private String title;
	private Type type;
	private String creator;
	private Note note;
	private static int counter = 1;
	
	public static enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
}
