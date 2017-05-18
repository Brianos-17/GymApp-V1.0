package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

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

    public static void deleteAssessment(Long assessmentid) {
        Member member = Accounts.getLoggedInMember();
        Assessment deleteAssessment = Assessment.findById(assessmentid);
        member.assessment.remove(deleteAssessment);
        member.save();
        deleteAssessment.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

    public static void deleteMemberAssessment(Long memberid, Long assessmentid) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = trainer.members;
        Member member = Member.findById(memberid);
        List<Assessment> assessment = member.assessment;
        Assessment deleteAssessment = Assessment.findById(assessmentid);
        assessment.remove(deleteAssessment);
        member.save();
        trainer.save();
        deleteAssessment.delete();
        Logger.info("Deleting Assessment for " + member.getFirstName() + " " + member.getLastName());
        render("assessment.html", trainer, members, member, assessment);
    }

    public static void deleteMember(Long memberid) {
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = trainer.members;
        Member member = Member.findById(memberid);
        List<Assessment> assessment = member.assessment;
        trainer.members.remove(member);
        trainer.save();
        member.delete();
        Logger.info("Deleting Member" + member.getFirstName() + " " + member.getLastName());
        render("trainerDashboard.html", trainer, members, member, assessment);
    }

    public static void memberAssessment(Long trainerid, Long memberid) {
        Logger.info("Rendering Assessments");
        Trainer trainer = Trainer.findById(trainerid);
        List<Member> members = trainer.members;
        Member member = Member.findById(memberid);
        List<Assessment> assessment = member.assessment;
        render("assessment.html", trainer, members, member, assessment);
    }

    public static void updateComment(Long trainerid, Long memberid, Long assessmentid, String comment) {
        Trainer trainer = Trainer.findById(trainerid);
        List<Member> members = trainer.members;
        Member member = Member.findById(memberid);
        Assessment updateAssessment = Assessment.findById(assessmentid);
        List<Assessment> assessment = member.assessment;
        updateAssessment.setComment(comment);
        updateAssessment.save();
        member.save();
        trainer.save();
        Logger.info("Updating comment for " + member.getFirstName() + " " + member.getLastName());
        render("assessment.html", trainer, members, member, assessment);
    }

    public static void account () {
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessment = member.assessment;
        Logger.info("Rendering account information for " + member.getFirstName() + " " + member.getLastName());
        render("account.html", member, assessment);
    }


}
