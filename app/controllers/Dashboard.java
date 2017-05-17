package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {

  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessment = member.assessment;
    render("dashboard.html", member, assessment);
  }

  public static void trainerIndex() {
    Logger.info("Rendering Trainer Dashboard");
    Trainer trainer = Accounts.getLoggedInTrainer();
    List<Member> members = trainer.members;
    render("trainerDashboard.html", trainer, members);
  }

  public static void memberAssessment(Long trainerid, Long memberid) {
    Logger.info("Rendering Assessments");
    Trainer trainer = Trainer.findById(trainerid);
    List<Member> members = trainer.members;
    Member member = Member.findById(memberid);
    List<Assessment> assessment = member.assessment;
    render("assessment.html", trainer, members, member, assessment);
  }

  public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist,
                                   double hips, String comment) {
    Member member = Accounts.getLoggedInMember();
    String trend = member.assessmentTrend();
    Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, trend, comment);
    member.assessment.add(newAssessment);
    newAssessment.save();
    Logger.info("Adding new Assessment for " + member.getFirstName() + member.getLastName());
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long id, Long assessmentId) {
    Member member = Member.findById(id);
    Assessment assessment = Assessment.findById(assessmentId);
    member.assessment.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting Assessment");
    redirect("/dashboard");
  }
}
