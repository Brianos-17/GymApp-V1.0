package controllers;

import models.Todo;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");
    List<Todo> todolist = Todo.findAll();
    render("dashboard.html", todolist);
  }

  public static void addTodo(String title)
  {
    Todo todo = new Todo(title);
    todo.save();
    Logger.info("Addint Todo" + title);
    redirect("/dashboard");
  }

  public static void deleteTodo(Long id)
  {
    Todo todo = Todo.findById(id);
    todo.delete();
    Logger.info("Deleting " + todo.title);
    redirect("/dashboard");
  }
}
