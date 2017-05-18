package controllers;

import play.Logger;
import play.mvc.*;

public class About extends Controller {

  /**
   * Method which will render the about page
   */
  public static void index() {
    Logger.info("Rendering about");
    render("about.html");
  }
}
