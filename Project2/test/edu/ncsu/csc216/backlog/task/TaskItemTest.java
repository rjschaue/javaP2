/**
 * 
 */
package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.command.Command;
import edu.ncsu.csc216.backlog.command.Command.CommandValue;
import edu.ncsu.csc216.backlog.task.TaskItem.Type;
import edu.ncsu.csc216.task.xml.NoteList;
import edu.ncsu.csc216.task.xml.Task;

/**
 * Tests the TaskItem class
 * @author Joey Schauer
 */
public class TaskItemTest {

	/**
	 * Test TaskItem
	 */
	@Test
	public void testTaskItem() {
		TaskItem.setCounter(1);
		TaskItem taskItem = new TaskItem("Title", Type.BUG, "Creator", "Note");
		assertEquals(taskItem.getTaskItemId(), 1);
		assertEquals(taskItem.getStateName(), "Backlog");
		assertEquals(taskItem.getType(), Type.BUG);
		assertEquals(taskItem.getTitle(), "Title");
		assertEquals(taskItem.getCreator(), "Creator");
		assertNull(taskItem.getOwner());
		//Test null title
		try {
			taskItem = new TaskItem(null, Type.FEATURE, "Joey", "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test empty title string
		try {
			taskItem = new TaskItem("", Type.FEATURE, "Joey", "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test null type
		try {
			taskItem = new TaskItem("Task", null, "Joey", "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test null creator
		try {
			taskItem = new TaskItem("Task", Type.KNOWLEDGE_ACQUISITION, null, "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test empty creator string
		try {
			taskItem = new TaskItem("Task", Type.KNOWLEDGE_ACQUISITION, "", "Text");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test null note
		try {
			taskItem = new TaskItem("Task", Type.KNOWLEDGE_ACQUISITION, "Joey", null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test empty note string
		try {
			taskItem = new TaskItem("Task", Type.KNOWLEDGE_ACQUISITION, "Joey", "");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
		//Test TaskItem with Task parameter
		Task task = new Task();
		task.setId(1);
		task.setState("Backlog");
		task.setTitle("TaskTitle");
		task.setType("F");
		task.setCreator("TaskCreator");
		task.setOwner(null);
		task.setVerified(false);
		NoteList noteList = new NoteList();
		task.setNoteList(noteList);
		taskItem = new TaskItem(task);
		assertEquals(taskItem.getTaskItemId(), 1);
		assertEquals(taskItem.getStateName(), "Backlog");
		assertEquals(taskItem.getType(), Type.FEATURE);
		assertEquals(taskItem.getTitle(), "TaskTitle");
		assertEquals(taskItem.getCreator(), "TaskCreator");
		assertNull(taskItem.getOwner());
		
		//Test Type
		Type.valueOf("BUG");
		Type.values();
	}

	/**
	 * Test setState
	 */
	@Test
	public void testGetTypeString() {
		//Get Bug Strings
		TaskItem taskItem = new TaskItem("Title", Type.BUG, "Creator", "Note");	
		assertEquals(taskItem.getTypeString(), "B");
		assertEquals(taskItem.getTypeFullString(), "Bug");
		//Get Feature Strings
		taskItem = new TaskItem("Title2", Type.FEATURE, "Creator2", "Note2");
		assertEquals(taskItem.getTypeString(), "F");
		assertEquals(taskItem.getTypeFullString(), "Feature");
		//Get Technical Work Strings
		taskItem = new TaskItem("Title3", Type.TECHNICAL_WORK, "Creator3", "Note3");
		assertEquals(taskItem.getTypeString(), "TW");
		assertEquals(taskItem.getTypeFullString(), "Technical Work");
		//Get Knowledge Acquisition Strings
		taskItem = new TaskItem("Title4", Type.KNOWLEDGE_ACQUISITION, "Creator4", "Note4");
		assertEquals(taskItem.getTypeString(), "KA");
		assertEquals(taskItem.getTypeFullString(), "Knowledge Acquisition");
	}
	
	/**
	 * Test update
	 */
	@Test
	public void testUpdate() {
		//BUG TASK TESTS
		TaskItem.setCounter(1);
		TaskItem taskItem = new TaskItem("Title", Type.BUG, "Creator", "Note");
		assertEquals(taskItem.getStateName(), "Backlog");
		Command c = new Command(CommandValue.COMPLETE, "Author0", "Note0");
		//Test update from backlog to unsupported command
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Backlog");
		}
		
		//OWNED STATE TESTS
		//Test update from backlog to owned
		c = new Command(CommandValue.CLAIM, "Author", "Note");		
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Owned");
		//Test update from owned to unsupported command
		c = new Command(CommandValue.VERIFY, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Owned");
		}
		
		//PROCESSSING STATE TESTS
		//Test update from owned to processing
		c = new Command(CommandValue.PROCESS, "Author", "Note2");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Processing");
		//Test update from processing to processing
		c = new Command(CommandValue.PROCESS, "Author", "Note2.1");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Processing");
		//Test update from processing to unsupported command
		c = new Command(CommandValue.CLAIM, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Processing");
		}
		//Test update from processing to complete which is an unsupported command
		c = new Command(CommandValue.COMPLETE, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Processing");
		}
		
		//VERIFYING STATE TESTS
		//Test update from processing to verifying
		c = new Command(CommandValue.VERIFY, "Author", "Note3");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Verifying");
		//Test update from verifying to unsupported command
		c = new Command(CommandValue.CLAIM, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Verifying");
		}		
		
		//Test update from verifying to processing
		c = new Command(CommandValue.PROCESS, "Author2", "Note4");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Processing");
		
		//Bump back to verifying
		c = new Command(CommandValue.VERIFY, "Author", "Note5");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Verifying");
		
		//DONE STATE TESTS
		//Test update from verifying to done
		c = new Command(CommandValue.COMPLETE, "Author2", "Note6");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Done");
		//Test update from done to unsupported command
		c = new Command(CommandValue.CLAIM, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Done");
		}
				
		//Test update from done to backlog
		c = new Command(CommandValue.BACKLOG, "Author3", "Note7");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Backlog");
		
		//REJECTED STATE TESTS
		//Test update from backlog to rejected
		c = new Command(CommandValue.REJECT, "Author", "Note8");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Rejected");
		//Test update from rejected to unsupported command
		c = new Command(CommandValue.CLAIM, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Rejected");
		}
		
		//Test update from rejected to backlog
		c = new Command(CommandValue.BACKLOG, "Author", "Note9");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Backlog");
		
		//KNOWLEDGE ACQUISITION TASK TESTS
		TaskItem.setCounter(1);
		taskItem = new TaskItem("Title", Type.KNOWLEDGE_ACQUISITION, "Creator", "Note");
		assertEquals(taskItem.getStateName(), "Backlog");
		
		//MORE OWNED STATE TESTS
		//Claiming task
		c = new Command(CommandValue.CLAIM, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Owned");
		//Test update from owned to rejected
		c = new Command(CommandValue.REJECT, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Rejected");
		//Putting task in backlog
		c = new Command(CommandValue.BACKLOG, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Backlog");
		//Claiming task
		c = new Command(CommandValue.CLAIM, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Owned");
		//Test update from owned to backlog
		c = new Command(CommandValue.BACKLOG, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Backlog");
		//Claiming task
		c = new Command(CommandValue.CLAIM, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Owned");
		
		//MORE PROCESSING STATE TESTS
		//Processing task
		c = new Command(CommandValue.PROCESS, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Processing");
		//Test update from rejected to unsupported command
		c = new Command(CommandValue.VERIFY, "Author0", "Note0");
		try {
			taskItem.update(c);
			fail();
		} catch (UnsupportedOperationException e) {
			assertEquals(taskItem.getStateName(), "Processing");
		}
		//Test update from processing to done
		c = new Command(CommandValue.COMPLETE, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Done");
		
		//MORE DONE STATE TESTS	
		//Test update from done to processing
		c = new Command(CommandValue.PROCESS, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Processing");
		
		//EVEN MORE PROCESSING TESTS
		//Test update from processing to backlog
		c = new Command(CommandValue.BACKLOG, "Author", "Note");
		taskItem.update(c);
		assertEquals(taskItem.getStateName(), "Backlog");
	}
	
	/**
	 * Test getNotesArray
	 */
	@Test
	public void testGetNotesArray() {
		TaskItem.setCounter(1);
		TaskItem taskItem = new TaskItem("Title", Type.BUG, "Creator", "Note");
		try {
			TaskItem.setCounter(0);
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTitle(), "Title");
		}
		String[][] notesArray = new String[2][2];
		notesArray[0][0] = "Creator";
		notesArray[0][1] = "Note";
		assertEquals(taskItem.getNotesArray()[0][0], notesArray[0][0]);
		assertEquals(taskItem.getNotesArray()[0][1], notesArray[0][1]);
		Command c = new Command(CommandValue.CLAIM, "Author1", "Note1");
		taskItem.update(c);
		notesArray[1][0] = "Author1";
		notesArray[1][1] = "Note1";
		assertEquals(taskItem.getNotesArray()[1][0], notesArray[1][0]);
		assertEquals(taskItem.getNotesArray()[1][1], notesArray[1][1]);
	}
}
