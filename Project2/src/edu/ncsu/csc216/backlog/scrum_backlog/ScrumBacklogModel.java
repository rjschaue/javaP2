package edu.ncsu.csc216.backlog.scrum_backlog;

import java.util.List;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.TaskIOException;
import edu.ncsu.csc216.task.xml.TaskReader;
import edu.ncsu.csc216.task.xml.TaskWriter;

/**
 * Maintains the current TaskItemList and handles activity around
 * loading, saving and creating new TaskItemLists
 * @author Joey Schauer
 */
public class ScrumBacklogModel {
	/** gets the task item list for the model */
	private TaskItemList taskItemList;
	/** this is a singleton instance for the model */
	private static ScrumBacklogModel singleton;
	
	/**
	 * This is the constructor for ScrumBacklogModel
	 */
	private ScrumBacklogModel() {
		taskItemList = new TaskItemList();
	}
	
	/**
	 * Returns the singleton instance of the model
	 * @return the singleton instance of the model
	 */
	public static ScrumBacklogModel getInstance() {
		if (singleton == null) {
			singleton = new ScrumBacklogModel();
		}
		return singleton;
	}
	
	/**
	 * Saves tasks in the item list to a file
	 * @param fileName the given file name to save the tasks to
	 * @throws IllegalArgumentException if a TaskIOException is caught
	 */
	public void saveTasksToFile(String fileName) {
		try {
			 TaskWriter taskWriter = new TaskWriter(fileName);
			 List<TaskItem> taskList = taskItemList.getTaskItems();
			 for (TaskItem taskItem: taskList) {
				 taskWriter.addItem(taskItem.getXMLTask());
			 }
			 taskWriter.marshal();
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Loads tasks from a given file name
	 * @param fileName the name of the file to load tasks from
	 * @throws IllegalArgumentException if a TaskIOException is caught
	 */
	public void loadTasksFromFile(String fileName) {
		try {
			 TaskReader taskReader = new TaskReader(fileName);
			 taskItemList.addXMLTasks(taskReader.getTasks());
		} catch (TaskIOException e) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new task item list
	 */
	public void createNewTaskItemList() {
		taskItemList = new TaskItemList();
	}
	
	/**
	 * Returns the task item list as an array
	 * @return the task item list as an array
	 */
	public Object[][] getTaskItemListAsArray() {
		List<TaskItem> taskList = taskItemList.getTaskItems();
		Object[][] taskItemListArray = new Object[taskList.size()][3];
		for (int i = 0; i < taskList.size(); i++) {
			TaskItem taskItem = taskList.get(i);
			taskItemListArray[i][0] = taskItem.getTaskItemId();
			taskItemListArray[i][1] = taskItem.getStateName();
			taskItemListArray[i][2] = taskItem.getTitle();
		}
		return taskItemListArray;
	}
	
	/**
	 * Returns the task item list by the given owner as an array
	 * @param owner the owner to get the array of task items for
	 * @return the task item list by the given owner as an array
	 * @throws IllegalArgumentException if the given owner is null
	 */
	public Object[][] getTaskItemListByOwnerAsArray(String owner) {
		if (owner == null) {
			throw new IllegalArgumentException();
		}
		List<TaskItem> taskList = taskItemList.getTaskItems();
		int ownerCount = 0;
		for (TaskItem taskItem: taskList) {
			if (taskItem.getOwner() != null && taskItem.getOwner().equals(owner)) {
				ownerCount++;
			}
		}
		Object[][] taskItemListArray = new Object[ownerCount][3];
		int i = 0;
		for (TaskItem taskItem: taskList) {
			if (taskItem.getOwner() != null && taskItem.getOwner().equals(owner)) {
				taskItemListArray[i][0] = taskItem.getTaskItemId();
				taskItemListArray[i][1] = taskItem.getStateName();
				taskItemListArray[i][2] = taskItem.getTitle();
				i++;
			}			
		}
		return taskItemListArray;
	}
	
	/**
	 * Returns the task item list by the given creator as an array
	 * @param creator the creator to get the array of task items for
	 * @return the task item list by the given creator as an array
	 * @throws IllegalArgumentException if the given creator is null
	 */
	public Object[][]  getTaskItemListByCreatorAsArray(String creator) {
		if (creator == null) {
			throw new IllegalArgumentException();
		}
		List<TaskItem> taskList = taskItemList.getTaskItems();
		int creatorCount = 0;
		for (TaskItem taskItem: taskList) {
			if (taskItem.getCreator().equals(creator)) {
				creatorCount++;
			}
		}
		Object[][] taskItemListArray = new Object[creatorCount][3];
		int i = 0;
		for (TaskItem taskItem: taskList) {
			if (taskItem.getCreator().equals(creator)) {
				taskItemListArray[i][0] = taskItem.getTaskItemId();
				taskItemListArray[i][1] = taskItem.getStateName();
				taskItemListArray[i][2] = taskItem.getTitle();
				i++;
			}			
		}
		return taskItemListArray;
	}
	
	/**
	 * Returns the task item with the given taskId
	 * @param taskId the task id of the item requested
	 * @return the task item with the given taskId
	 */
	public TaskItem getTaskItemById(int taskId) {		
		return taskItemList.getTaskItemById(taskId);
	}
	
	/**
	 * Executes the given command for the task at the given id
	 * @param taskId the task id for the task to be commanded
	 * @param command the command for the task at the given id
	 */
	public void executeCommand(int taskId, Command command) {
		taskItemList.executeCommand(taskId, command);
	}
	
	/**
	 * Deletes a task with the given task id
	 * @param taskId the task id of the item to be deleted
	 */
	public void deleteTaskItemById(int taskId) {
		taskItemList.deleteTaskItemById(taskId);
	}
	
	/**
	 * Adds a task item to the list with the given parameters
	 * @param title the title of the task item
	 * @param type the type of the task item
	 * @param creator the creator of the task item
	 * @param note the note for the task item
	 */
	public void addTaskItemToList(String title, Type type, String creator, String note) {
		taskItemList.addTaskItem(title, type, creator, note);
	}
}
