/**
 * 
 */
package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests the TaskItemList class
 * @author Joey Schauer
 */
public class TaskItemListTest {

	/**
	 * Test addTaskItem
	 */
	@Test
	public void testAddTaskItem() {
		TaskItemList taskList = new TaskItemList();
		assertNull(taskList.getTaskItemById(1));
		assertEquals(taskList.addTaskItem("Title1", Type.BUG, "Creator1", "Note1"), 1);
		assertEquals(taskList.addTaskItem("Title2", Type.BUG, "Creator2", "Note2"), 2);
		assertNotEquals(taskList.getTaskItemById(1), taskList.getTaskItemById(2));
	}
	
	/**
	 * Test addXMLTasks
	 */
	@Test
	public void testAddXMLTasks() {
		TaskItemList taskList = new TaskItemList();
		
		Task task = new Task();
		task.setId(1);
		task.setState("Backlog");
		task.setTitle("TaskTitle");
		task.setType("F");
		task.setCreator("TaskCreator");
		task.setOwner("Owner");
		task.setVerified(false);
		NoteList noteList = new NoteList();
		task.setNoteList(noteList);
		ArrayList<Task> xmlTasks = new ArrayList<Task>();
		xmlTasks.add(task);
		
		taskList.addXMLTasks(xmlTasks);
		TaskItem taskItem = taskList.getTaskItemById(1);
		assertEquals(taskItem.getTitle(), "TaskTitle");
	}
	
	/**
	 * Test getTaskItem, getTaskItemsByOwner and getTasksByCreator
	 */
	@Test
	public void testGetTaskItems() {
		TaskItemList taskList = new TaskItemList();
		assertEquals(taskList.addTaskItem("Title1", Type.BUG, "Creator1", "Note1"), 1);
		assertEquals(taskList.addTaskItem("Title2", Type.BUG, "Creator2", "Note2"), 2);
		
		Task task = new Task();
		task.setId(1);
		task.setState("Backlog");
		task.setTitle("TaskTitle");
		task.setType("F");
		task.setCreator("TaskCreator");
		task.setOwner("Owner");
		task.setVerified(false);
		NoteList noteList = new NoteList();
		task.setNoteList(noteList);
		ArrayList<Task> xmlTasks = new ArrayList<Task>();
		xmlTasks.add(task);
		taskList.addXMLTasks(xmlTasks);
		
		List<TaskItem> arrayList = taskList.getTaskItems();
		assertFalse(arrayList.isEmpty());
		List<TaskItem> ownerArrayList = taskList.getTaskItemsByOwner("Owner");
		assertFalse(ownerArrayList.isEmpty());
		List<TaskItem> creatorArrayList = taskList.getTasksByCreator("Creator1");
		assertFalse(creatorArrayList.isEmpty());
		
	}
	
	/**
	 * Test executeCommand
	 */
	@Test
	public void testExecuteCommand() {
		TaskItemList taskList = new TaskItemList();
		assertEquals(taskList.addTaskItem("Title1", Type.BUG, "Creator1", "Note1"), 1);
		assertEquals(taskList.addTaskItem("Title2", Type.BUG, "Creator2", "Note2"), 2);
		
		Command c = new Command(CommandValue.CLAIM, "Author", "Note");
		taskList.executeCommand(1, c);
		taskList.executeCommand(10, c);
	}
	
	/**
	 * Test deleteTaskItemById
	 */
	@Test
	public void testDeleteTaskItemById() {
		TaskItemList taskList = new TaskItemList();
		assertEquals(taskList.addTaskItem("Title1", Type.BUG, "Creator1", "Note1"), 1);
		assertEquals(taskList.addTaskItem("Title2", Type.BUG, "Creator2", "Note2"), 2);
		
		taskList.deleteTaskItemById(1);
		assertNull(taskList.getTaskItemById(1));
	}
}
