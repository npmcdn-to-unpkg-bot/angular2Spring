package net.chh.axon.event.handler;

import org.axonframework.eventhandling.annotation.EventHandler;

import net.chh.axon.event.ToDoItemCompletedEvent;
import net.chh.axon.event.ToDoItemCreatedEvent;

public class ToDoEventHandler {

  @EventHandler
  public void handle(ToDoItemCreatedEvent event) {
    System.out.println(
        "We've got something to do: " + event.getDescription() + " (" + event.getTodoId() + ")");
  }

  @EventHandler
  public void handle(ToDoItemCompletedEvent event) {
    System.out.println("We've completed a task: " + event.getTodoId());
  }
}
