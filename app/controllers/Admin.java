package controllers;

import models.Assessment;
import models.Todo;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Admin extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Admin");
    List<Assessment> assessment = Assessment.findAll();
    render("admin.html", assessment);
  }
}
