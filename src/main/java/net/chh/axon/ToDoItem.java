package net.chh.axon;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import net.chh.axon.command.CreateToDoItemCommand;
import net.chh.axon.command.MarkCompletedCommand;
import net.chh.axon.event.ToDoItemCompletedEvent;
import net.chh.axon.event.ToDoItemCreatedEvent;

public class ToDoItem extends AbstractAnnotatedAggregateRoot {

  @AggregateIdentifier
  private String id;

  public ToDoItem() {
  }

  @CommandHandler
  public ToDoItem(CreateToDoItemCommand command) {
    apply(new ToDoItemCreatedEvent(command.getTodoId(), command.getDescription()));
  }

  @EventHandler
  public void on(ToDoItemCreatedEvent event) {
    this.id = event.getTodoId();
  }

  @CommandHandler
  public void markCompleted(MarkCompletedCommand command) {
    apply(new ToDoItemCompletedEvent(id));
  }
}
