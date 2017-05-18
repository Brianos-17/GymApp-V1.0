package controllers;

import models.*;
import play.Logger;
import play.mvc.Controller;

import java.util.*;

public class Dashboard extends Controller {

    /**
     * Method which associates session with a specific member and renders the dashboard for them
     */
    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessment = member.assessment;
        render("dashboard.html", member, assessment);
    }

    /**
     * Method which associates session with a specific trainer and renders the trainer dashboard for them
     * Also gets all current members and adds them to the trainers arraylist of members
     */
    public static void trainerIndex() {
        Logger.info("Rendering Trainer Dashboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render("trainerDashboard.html", trainer, members);
    }

    /**
     * Method to add an assessment to the current members assessment arraylist
     * Renders the dashboard with updated information
     *
     * @param weight double
     * @param chest double
     * @param thigh double
     * @param upperArm double
     * @param waist double
     * @param hips double
     * @param comment String
     */
    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist,
                                     double hips, String comment) {
        Member member = Accounts.getLoggedInMember();
        String trend = "black";
        Assessment newAssessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, trend, comment);
        member.assessment.add(newAssessment);
        newAssessment.setTrend(member.assessmentTrend());
        newAssessment.save();
        Logger.info("Adding new Assessment for " + member.getFirstName() + member.getLastName());
        redirect("/dashboard");
    }

    /**
     * Method to remove and delete a specific assessment ffrom the current members assessment arraylist
     * Renders the dashboard with updated information
     * @param assessmentid Long
     */
    public static void deleteAssessment(Long assessmentid) {
        Member member = Accounts.getLoggedInMember();
        Assessment deleteAssessment = Assessment.findById(assessmentid);
        member.assessment.remove(deleteAssessment);
        member.save();
        deleteAssessment.delete();
        Logger.info("Deleting Assessment");
        redirect("/dashboard");
    }

    /**
     * Method which allows a trainer to delete a specific assessment for a member
     * Renders the assessment page for the trainer with the updated information
     *
     * @param memberid long
     * @param assessmentid long
     */
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

    /**
     * Method which allows a trainer to remove and delete a specific member
     * Renders the trainer dashboard with the updated information
     *
     * @param memberid long
     */
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

    /**
     * Method which allows a trainer to view all assessments for a specific member
     * Renders the assessment.html page
     *
     * @param trainerid long
     * @param memberid long
     */
    public static void memberAssessment(Long trainerid, Long memberid) {
        Logger.info("Rendering Assessments");
        Trainer trainer = Trainer.findById(trainerid);
        List<Member> members = trainer.members;
        Member member = Member.findById(memberid);
        List<Assessment> assessment = member.assessment;
        render("assessment.html", trainer, members, member, assessment);
    }

    /**
     * Method which allows a trainer to update or change the comment for a specific assessment for a member
     * Renders the assessment page with this updated information
     *
     * @param trainerid long
     * @param memberid long
     * @param assessmentid long
     * @param comment String
     */
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

    /**
     * Method which renders the accounts page to allow a member to view and update their profile
     */
    public static void account () {
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessment = member.assessment;
        Logger.info("Rendering account information for " + member.getFirstName() + " " + member.getLastName());
        render("account.html", member, assessment);
    }


}
