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
	/** The initial counter value for the task item list */
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
		TaskItem taskItem = new TaskItem(title, type, creator, note);
		tasks.add(taskItem);
		return taskItem.getTaskItemId();
	}
	
	/**
	 * Adds a list of XML tasks to the task item list
	 * @param tasks the array of tasks to be added
	 */
	public void addXMLTasks(List<Task> tasks) {
		for (Task task: tasks) {
			TaskItem taskItem = new TaskItem(task);
			this.tasks.add(taskItem);
		}
	}
	
	/**
	 * Returns a list of task items
	 * @return a list of task items
	 */
	public List<TaskItem> getTaskItems() {
		return tasks;
	}
	
	/**
	 * Returns a list of task items by owner
	 * @param owner the owner to get task items for
	 * @return a list of task items by owner
	 */
	public List<TaskItem> getTaskItemsByOwner(String owner) {
		ArrayList<TaskItem> taskItemList = new ArrayList<TaskItem>();
		for (TaskItem taskItem: tasks) {
			if (taskItem.getOwner().equals(owner)) {
				taskItemList.add(taskItem);
			}
		}
		return taskItemList;
	}
	
	/**
	 * Returns a list of task items by creator
	 * @param creator the creator to get task items for
	 * @return a list of task items by creator
	 */
	public List<TaskItem> getTasksByCreator(String creator) {
		ArrayList<TaskItem> taskItemList = new ArrayList<TaskItem>();
		for (TaskItem taskItem: tasks) {
			if (taskItem.getCreator().equals(creator)) {
				taskItemList.add(taskItem);
			}
		}
		return taskItemList;
	}
	
	/**
	 * Returns a task item by it's id
	 * @param taskId the id of the task to be returned
	 * @return a task item by it's id
	 */
	public TaskItem getTaskItemById(int taskId) {
		for (TaskItem taskItem: tasks) {
			if (taskItem.getTaskItemId() == taskId) {
				return taskItem;
			}
		}
		return null;
	}
	
	/**
	 * Executes a given command to the task with the given id
	 * @param taskId the id of the task to be commanded
	 * @param command the command for the task with the given id
	 */
	public void executeCommand(int taskId, Command command) {
		TaskItem taskItem = getTaskItemById(taskId);
		if (taskItem != null) {
			taskItem.update(command);
		}		
	}
	
	/**
	 * Deletes a task item with the given id
	 * @param taskId the id of the task item to be deleted
	 */
	public void deleteTaskItemById(int taskId) {
		for (int i = 0; i < tasks.size(); i++) {
			TaskItem taskItem = tasks.get(i);
			if (taskItem.getTaskItemId() == taskId) {
				tasks.remove(i);
				break;
			}
		}
	}
}
