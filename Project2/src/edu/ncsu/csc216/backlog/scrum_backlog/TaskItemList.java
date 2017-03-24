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
	public ArrayList<TaskItem> tasks;
	private static final int INITIAL_COUNTER_VALUE = 1;

	public TaskItemList() {
		TaskItem.setCounter(INITIAL_COUNTER_VALUE);
		emptyList();
	}
	
	private void emptyList() {
		tasks = new ArrayList<TaskItem>();
	}
	
	public int addTaskItem(String title, Type type, String creator, String note) {
		return 0;
	}
	
	public void addXMLTasks(List<Task> tasks) {
		
	}
	
	public List<TaskItem> getTaskItems() {
		return null;
	}
	
	public List<TaskItem> getTaskItemsByOwner() {
		return null;
	}
	
	public List<TaskItem> getTasksByCreator() {
		return null;
	}
	
	public TaskItem getTaskItemById(int taskId) {
		return null;
	}
	
	public void executeCommand(int taskId, Command command) {
		
	}
	
	public void deleteTaskItemById(int taskId) {
		
	}
}
