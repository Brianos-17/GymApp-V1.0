package controllers;

import models.Todo;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Todo> todolist = Todo.findAll();
    render("admin.html", todolist);
  }
}
