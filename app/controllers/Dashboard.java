package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {

  public static void index()
  {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessment = member.assessment;
    render("dashboard.html", member, assessment);
  }

  public static void trainerIndex() {
    Logger.info("Rendering Trainer Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> allmembers = trainer.allmembers;
    render("trainerDashboard.html", trainer, allmembers);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist,
                                   double hips, String comment)
  {
    Member member = Accounts.getLoggedInMember();
    Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
    member.assessment.add(newAssessment);
    newAssessment.save();
    Logger.info("Adding new Assessment for " + member.getFirstName() + member.getLastName());
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id)
  {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(id);
    member.assessment.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }
}
