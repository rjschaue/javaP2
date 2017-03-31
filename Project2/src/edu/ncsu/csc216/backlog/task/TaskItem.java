package edu.ncsu.csc216.backlog.task;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.task.xml.NoteItem;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * This class maintains all of the history of progression throw the FSM
 * @author Joey Schauer
 */
public class TaskItem {
	/** Gets the task id number */
	private int taskId;
	/** Gets the state of the task item */
	private TaskItemState state;
	/** Gets the title of the task item */
	private String title;
	/** Gets the type of the task item */
	private Type type;
	/** Gets the creator of the task item */
	private String creator;
	/** Gets the owner of the task item */
	private String owner;
	/** Gets whether the task item is verified or not */
	private boolean isVerified;
	/** Gets an array list of notes for the task item */
	private ArrayList<Note> notes;
	/** Gets a constant value for the backlog state */
	private final TaskItemState backlogState = new BacklogState();
	/** Gets a constant value for the owned state */
	private final TaskItemState ownedState = new OwnedState();
	/** Gets a constant value for the processing state */
	private final TaskItemState processingState = new ProcessingState();
	/** Gets a constant value for the verifying state */
	private final TaskItemState verifyingState = new VerifyingState();
	/** Gets a constant value for the done state */
	private final TaskItemState doneState = new DoneState();
	/** Gets a constant value for the rejected state */
	private final TaskItemState rejectedState = new RejectedState();
	/** Gets a constant value for the backlog name */
	public static final String BACKLOG_NAME = "Backlog";
	/** Gets a constant value for the owned name */
	public static final String OWNED_NAME = "Owned";
	/** Gets a constant value for the processing name */
	public static final String PROCESSING_NAME = "Processing";
	/** Gets a constant value for the verifying name */
	public static final String VERIFYING_NAME = "Verifying";
	/** Gets a constant value for the done name */
	public static final String DONE_NAME = "Done";
	/** Gets a constant value for the rejected name */
	public static final String REJECTED_NAME = "Rejected";
	/** Gets a constant value for the feature short name */
	public static final String T_FEATURE = "F";
	/** Gets a constant value for the bug short name */
	public static final String T_BUG = "B";
	/** Gets a constant value for the technical work short name */
	public static final String T_TECHNICAL_WORK = "TW";
	/** Gets a constant value for the knowledge acquisition short name */
	public static final String T_KNOWLEDGE_ACQUISITION = "KA";
	/** Gets a static value for counting the number of task items*/
	private static int counter = 1;
	
	/**
	 * One of the constructors for TaskItem accepting 4 parameters
	 * @param title the title of the task item
	 * @param type the type of task item
	 * @param creator the creator of the task item
	 * @param note the note for the task item
	 * @throws IllegalArgumentException if any of the parameters is null or empty
	 */
	public TaskItem(String title, Type type, String creator, String note) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (type == null) {
			throw new IllegalArgumentException();
		}
		if (creator == null || creator.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}
		taskId = counter;
		state = backlogState;
		this.title = title;
		this.type = type;
		this.creator = creator;
		owner = null;
		isVerified = false;
		Note newNote = new Note(creator, note);
		notes = new ArrayList<Note>();
		notes.add(newNote);		
		incrementCounter();
	}
	
	/**
	 * Another constructor for TaskItem that accepts one Task parameter
	 * @param task the task to be constructed as a TaskItem
	 */
	public TaskItem(Task task) {
		taskId = task.getId();
		setState(task.getState());
		title = task.getTitle();
		setType(task.getType());
		creator = task.getCreator();
		owner = task.getOwner();
		isVerified = task.isVerified();
		List<NoteItem> noteItemList = task.getNoteList().getNotes();
		notes = new ArrayList<Note>();
		for (int i = 0; i < noteItemList.size(); i++) {
			String noteAuthor = noteItemList.get(i).getNoteAuthor();
			String noteText = noteItemList.get(i).getNoteText();
			Note note = new Note(noteAuthor, noteText);
			notes.add(note);
		}
		incrementCounter();
	}
	
	/**
	 * This method increments the static counter
	 */
	public static void incrementCounter() {
		counter++;
	}
	
	/**
	 * Returns the task item id
	 * @return the task item id
	 */
	public int getTaskItemId() {
		return taskId;
	}
	
	/**
	 * Returns the state name
	 * @return the state name
	 */
	public String getStateName() {
		return state.getStateName();
	}
	
	/**
	 * Sets the state of the task item
	 * @param stateValue the state to be set as a string
	 * @throws IllegalArgumentException if the state value is null or not a valid option
	 */
	private void setState(String stateValue) {
		if (stateValue == null) {
			throw new IllegalArgumentException();
		}
		if (stateValue.equals(BACKLOG_NAME)) {
			state = backlogState;
		} else if (stateValue.equals(OWNED_NAME)) {
			state = ownedState;
		} else if (stateValue.equals(PROCESSING_NAME)) {
			state = processingState;
		} else if (stateValue.equals(VERIFYING_NAME)) {
			state = verifyingState;
		} else if (stateValue.equals(DONE_NAME)) {
			state = doneState;
		} else if (stateValue.equals(REJECTED_NAME)) {
			state = rejectedState;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Sets the type for the task item
	 * @param typeValue the type to be set as a string
	 * @throws IllegalArgumentException if the type value is null or not a valid option
	 */
	private void setType(String typeValue) {
		if (typeValue == null) {
			throw new IllegalArgumentException();
		}
		if (typeValue.equals(T_FEATURE)) {
			type = Type.FEATURE;
		} else if (typeValue.equals(T_BUG)) {
			type = Type.BUG;
		} else if (typeValue.equals(T_TECHNICAL_WORK)) {
			type = Type.TECHNICAL_WORK;
		} else if (typeValue.equals(T_KNOWLEDGE_ACQUISITION)) {
			type = Type.KNOWLEDGE_ACQUISITION;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Returns the type of the task item
	 * @return the type of the task item
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Returns the type as a short string
	 * @return the type as a short string
	 */
	public String getTypeString() {
		if (type.equals(Type.FEATURE)) {
			return T_FEATURE;
		} else if (type.equals(Type.BUG)) {
			return T_BUG;
		} else if (type.equals(Type.TECHNICAL_WORK)) {
			return T_TECHNICAL_WORK;
		} else if (type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			return T_KNOWLEDGE_ACQUISITION;
		}
		return null;
	}
	
	/**
	 * Returns the type as a full string
	 * @return the type as a full string
	 */
	public String getTypeFullString() {
		if (type.equals(Type.FEATURE)) {
			return "Feature";
		} else if (type.equals(Type.BUG)) {
			return "Bug";
		} else if (type.equals(Type.TECHNICAL_WORK)) {
			return "Technical Work";
		} else if (type.equals(Type.KNOWLEDGE_ACQUISITION)) {
			return "Knowledge Acquisition";
		}
		return null;
	}
	
	/**
	 * Returns the owner of the task item
	 * @return the owner of the task item
	 */
	public String getOwner() {
		return owner;
	}
	
	/**
	 * Returns the title of the task item
	 * @return the title of the task item
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns the creator of the task item
	 * @return the creator of the task item
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Returns the notes for the task item as an ArrayList
	 * @return the notes for the task item as an ArrayList
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	/**
	 * Updates the task item with a given command
	 * @param command the command for the task item
	 * @throws UnsupportedOperationException if the command is not a valid one
	 */
	public void update(Command command) {
		try {
			state.updateState(command);
		} catch (UnsupportedOperationException e) {
			throw new UnsupportedOperationException();
		}		
	}
	
	/**
	 * Returns the task item as an XML task
	 * @return the task item as an XML task
	 */
	public Task getXMLTask() {
		Task task = new Task();
		task.setId(taskId);
		task.setState(getStateName());
		task.setTitle(title);
		task.setType(getTypeString());
		task.setCreator(creator);
		task.setOwner(owner);
		task.setVerified(isVerified);
		NoteList noteList = new NoteList();
		for (int i = 0; i < notes.size(); i++) {
			String noteAuthor = notes.get(i).getNoteAuthor();
			String noteText = notes.get(i).getNoteText();
			NoteItem noteItem = new NoteItem();
			noteItem.setNoteAuthor(noteAuthor);
			noteItem.setNoteText(noteText);
			noteList.getNotes().add(noteItem);
		}
		task.setNoteList(noteList);
		return task;
	}
	
	/**
	 * Sets the static counter to a given value
	 * @param count the value to set the static counter to
	 * @throws IllegalArgumentException if the count is less than 1
	 */
	public static void setCounter(int count) {
		if (count < 1) {
			throw new IllegalArgumentException();
		}
		counter = count;
	}
	
	/**
	 * Returns the task item's notes as a 2D array
	 * @return the task item's notes as a 2D array
	 */
	public String[][] getNotesArray() {
		String[][] notesArray = new String[notes.size()][2];
		for (int i = 0; i < notes.size(); i++) {
			notesArray[i][0] = notes.get(i).getNoteAuthor();
			notesArray[i][1] = notes.get(i).getNoteText();
		}
		return notesArray;
	}
	
	/**
	 * An enum to represent the different types of task items
	 * @author Joey Schauer
	 */
	public static enum Type { FEATURE, BUG, TECHNICAL_WORK, KNOWLEDGE_ACQUISITION }
	
	/**
	 * Interface for states in the Task State Pattern.  All 
	 * concrete task states must implement the TaskState interface.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface TaskItemState {
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();
	
	}
	
	/**
	 * This class is used to handle task items in the backlog state
	 * @author Joey Schauer
	 */
	private class BacklogState implements TaskItemState {
		/**
		 * Null constructor for the backlog state
		 */
		private BacklogState() {
			
		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand().equals(CommandValue.CLAIM)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				owner = c.getNoteAuthor();
				setState(OWNED_NAME);
			} else if (c.getCommand().equals(CommandValue.REJECT)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				setState(REJECTED_NAME);
			} else {
				throw new UnsupportedOperationException();
			}			
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return BACKLOG_NAME;
		}
	}
	
	/**
	 * This class is used to handle task items in the owned state
	 * @author Joey Schauer
	 */
	private class OwnedState implements TaskItemState {
		/**
		 * Null constructor for the owned state
		 */
		private OwnedState() {
			
		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand().equals(CommandValue.PROCESS)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				setState(PROCESSING_NAME);
			} else if (c.getCommand().equals(CommandValue.REJECT)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				owner = null;
				setState(REJECTED_NAME);
			} else if (c.getCommand().equals(CommandValue.BACKLOG)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				owner = null;
				setState(BACKLOG_NAME);
			} else {
				throw new UnsupportedOperationException();
			}		
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return OWNED_NAME;
		}
	}
	
	/**
	 * This class is used to handle task items in the processing state
	 * @author Joey Schauer
	 */
	private class ProcessingState implements TaskItemState {
		/**
		 * Null constructor for the processing state
		 */
		private ProcessingState() {

		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand().equals(CommandValue.PROCESS)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				setState(PROCESSING_NAME);
			} else if (c.getCommand().equals(CommandValue.VERIFY)) {
				if (!getTypeString().equals(T_KNOWLEDGE_ACQUISITION) && getTypeString() != null) {
					Note note = new Note(c.getNoteAuthor(), c.getNoteText());
					notes.add(note);
					setState(VERIFYING_NAME);
				} else {
					throw new UnsupportedOperationException();
				}			
			} else if (c.getCommand().equals(CommandValue.COMPLETE)) {
				if (getTypeString().equals(T_KNOWLEDGE_ACQUISITION)) {
					Note note = new Note(c.getNoteAuthor(), c.getNoteText());
					notes.add(note);
					setState(DONE_NAME);
				} else {
					throw new UnsupportedOperationException();
				}	
			} else if (c.getCommand().equals(CommandValue.BACKLOG)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				owner = null;
				setState(BACKLOG_NAME);
			} else {
				throw new UnsupportedOperationException();
			}			
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}
	}
	
	/**
	 * This class is used to handle task items in the verifying state
	 * @author Joey Schauer
	 */
	private class VerifyingState implements TaskItemState {
		/**
		 * Null constructor for the verifying state
		 */
		private VerifyingState() {
			
		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (!getTypeString().equals(T_KNOWLEDGE_ACQUISITION) && getTypeString() != null) {
				if (c.getCommand().equals(CommandValue.PROCESS)) {
					Note note = new Note(c.getNoteAuthor(), c.getNoteText());
					notes.add(note);
					setState(PROCESSING_NAME);
				} else if (c.getCommand().equals(CommandValue.COMPLETE)) {
					Note note = new Note(c.getNoteAuthor(), c.getNoteText());
					notes.add(note);
					isVerified = true;
					setState(DONE_NAME);
				} else {
					throw new UnsupportedOperationException();
				}
			} else {
				throw new UnsupportedOperationException();
			}				
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}
	}
	
	/**
	 * This class is used to handle task items in the done state
	 * @author Joey Schauer
	 */
	private class DoneState implements TaskItemState {
		/**
		 * Null constructor for the done state
		 */
		private DoneState() {
			
		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand().equals(CommandValue.PROCESS)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				setState(PROCESSING_NAME);
			} else if (c.getCommand().equals(CommandValue.BACKLOG)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				owner = null;
				setState(BACKLOG_NAME);
			} else {
				throw new UnsupportedOperationException();
			}		
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return DONE_NAME;
		}
	}
	
	/**
	 * This class is used to handle task items in the rejected state
	 * @author Joey Schauer
	 */
	private class RejectedState implements TaskItemState {
		/**
		 * Null constructor for the rejected state
		 */
		private RejectedState() {
			
		}
		
		/**
		 * Update the {@link TaskItem} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TaskItem}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand().equals(CommandValue.BACKLOG)) {
				Note note = new Note(c.getNoteAuthor(), c.getNoteText());
				notes.add(note);
				setState(BACKLOG_NAME);
			} else {
				throw new UnsupportedOperationException();
			}	
		}

		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}
	}
}
