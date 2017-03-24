/**
 * 
 */
package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Maintains a list of task items
 * @author Joey Schauer
 */
public class TaskItemList {
	/** The array list of task items */
	public ArrayList<TaskItem> tasks;
	/** The inital counter value for the task item list */
	private static final int INITIAL_COUNTER_VALUE = 1;

	/**
	 * The constructor for TaskItemList
	 */
	public TaskItemList() {
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
		emptyList();
	}
	
	/**
	 * Sets tasks to an empty list
	 */
	private void emptyList() {
		tasks = new ArrayList<TaskItem>();
	}
	
	/**
	 * Adds an item to the task list with the given parameters
	 * @param title the title of the task item
	 * @param type the type of the task item
	 * @param creator the creator of the task item
	 * @param note the note for the task item
	 * @return the id of the added task item
	 */
	public int addTaskItem(String title, Type type, String creator, String note) {
		return 0;
	}
	
	/**
	 * Adds a list of XML tasks to the task item list
	 * @param tasks the array of tasks to be added
	 */
	public void addXMLTasks(List<Task> tasks) {
		//To be implemented
	}
	
	/**
	 * Returns a list of task items
	 * @return a list of task items
	 */
	public List<TaskItem> getTaskItems() {
		return null;
	}
	
	/**
	 * Returns a list of task items by owner
	 * @param owner the owner to get task items for
	 * @return a list of task items by owner
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner) {
		return null;
	}
	
	/**
	 * Returns a list of task items by creator
	 * @param creator the creator to get task items for
	 * @return a list of task items by creator
	 */
	public List<TaskItem> getTasksByCreator(String creator) {
		return null;
	}
	
	/**
	 * Returns a task item by it's id
	 * @param taskId the id of the task to be returned
	 * @return a task item by it's id
	 */
	public TaskItem getTaskItemById(int taskId) {
		return null;
	}
	
	/**
	 * Executes a given command to the task with the given id
	 * @param taskId the id of the task to be commanded
	 * @param command the command for the task with the given id
	 */
	public void executeCommand(int taskId, Command command) {
		//To be implemented
	}
	
	/**
	 * Deletes a task item with the given id
	 * @param taskId the id of the task item to be deleted
	 */
	public void deleteTaskItemById(int taskId) {
		//To be implemented
	}
}
