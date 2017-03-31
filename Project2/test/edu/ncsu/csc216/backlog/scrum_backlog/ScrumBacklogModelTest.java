/**
 * 
 */
package edu.ncsu.csc216.backlog.scrum_backlog;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;

/**
 * Tests the ScrumBacklogModel class
 * @author Joey Schauer
 */
public class ScrumBacklogModelTest {

	/**
	 * Test saveTasksToFile
	 */
	@Test
	public void testSaveTasksToFile() {
		//Construct new model
		ScrumBacklogModel scrumBacklogModel = ScrumBacklogModel.getInstance();
		scrumBacklogModel.createNewTaskItemList();
		//Add a item and check the model
		scrumBacklogModel.addTaskItemToList("Title1", Type.BUG, "Creator1", "Note1");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");
		//Remove the item and check the model
		scrumBacklogModel.deleteTaskItemById(1);
		assertNull(scrumBacklogModel.getTaskItemById(1));
		//Add to the list and save to a file
		TaskItem.setCounter(1);
		scrumBacklogModel.addTaskItemToList("Title1", Type.BUG, "Creator1", "Note1");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");
		scrumBacklogModel.saveTasksToFile("test-files/Save_Task_Test.txt");
		//clear the list and load from the created file
		scrumBacklogModel.createNewTaskItemList();
		scrumBacklogModel.loadTasksFromFile("test-files/Save_Task_Test.txt");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");		
	}
	
	/**
	 * Test saveTasksToFile
	 */
	@Test
	public void testLoadTasksFromFile() {
		//Construct new model
		ScrumBacklogModel scrumBacklogModel = ScrumBacklogModel.getInstance();
		scrumBacklogModel.createNewTaskItemList();
		//Add to the list and save to a file
		TaskItem.setCounter(1);
		scrumBacklogModel.addTaskItemToList("Title1", Type.BUG, "Creator1", "Note1");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");
		scrumBacklogModel.saveTasksToFile("test-files/Save_Task_Test.txt");
		//clear the list and load from the created file
		scrumBacklogModel.createNewTaskItemList();
		scrumBacklogModel.loadTasksFromFile("test-files/Save_Task_Test.txt");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");
	}

	/**
	 * Test getTaskItemListAsArray, getTaskItemListByOwnerAsArray
	 * and getTaskItemListByCreatorAsArray
	 */
	@Test
	public void testgetTaskItemListAsArray() {
		//Construct new model
		ScrumBacklogModel scrumBacklogModel = ScrumBacklogModel.getInstance();
		scrumBacklogModel.createNewTaskItemList();
		//Add to the list and save to a file
		TaskItem.setCounter(1);
		scrumBacklogModel.addTaskItemToList("Title1", Type.BUG, "Creator1", "Note1");
		assertEquals(scrumBacklogModel.getTaskItemById(1).getTitle(), "Title1");
		Object[][] listArray = new Object[1][3];
		listArray[0][0] = 1;
		listArray[0][1] = "Backlog";
		listArray[0][2] = "Title1";
		//Test getTaskItemListAsArray
		assertEquals(listArray[0][0], scrumBacklogModel.getTaskItemListAsArray()[0][0]);
		assertEquals(listArray[0][1], scrumBacklogModel.getTaskItemListAsArray()[0][1]);
		assertEquals(listArray[0][2], scrumBacklogModel.getTaskItemListAsArray()[0][2]);
		//Test getTaskItemListByCreatorAsArray
		Object[][] scrumArray = scrumBacklogModel.getTaskItemListByCreatorAsArray("Creator1");
		assertEquals(listArray[0][0], scrumArray[0][0]);
		assertEquals(listArray[0][1], scrumArray[0][1]);
		assertEquals(listArray[0][2], scrumArray[0][2]);
		//Test getTaskItemListByOwnerAsArray
		Command c = new Command(CommandValue.CLAIM, "Owner1", "Note2");
		scrumBacklogModel.executeCommand(1, c);
		scrumArray = scrumBacklogModel.getTaskItemListByOwnerAsArray("Owner1");		
		listArray[0][1] = "Owned";
		assertEquals(listArray[0][0], scrumArray[0][0]);
		assertEquals(listArray[0][1], scrumArray[0][1]);
		assertEquals(listArray[0][2], scrumArray[0][2]);
	}
}
