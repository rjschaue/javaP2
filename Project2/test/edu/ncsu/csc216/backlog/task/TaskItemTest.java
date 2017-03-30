/**
 * 
 */
package edu.ncsu.csc216.backlog.task;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.backlog.task.TaskItem.Type;

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
		} catch (IllegalArgumentException e) {
			assertEquals(taskItem.getTaskItemId(), 1);
			assertEquals(taskItem.getStateName(), "Backlog");
			assertEquals(taskItem.getType(), Type.BUG);
			assertEquals(taskItem.getTitle(), "Title");
			assertEquals(taskItem.getCreator(), "Creator");
			assertNull(taskItem.getOwner());
		}
	}

}
