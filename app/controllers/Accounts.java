package controllers;

import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import models.Member;

import java.util.List;

/**
 * Created by Brian on 03/05/2017.
 *
 * Class which holds session data for both members and trainers
 */
public class Accounts extends Controller {

    /**
     * Method which renders the signup page
     */
    public static void signup() {
        render("signup.html");
    }

    /**
     * Method which renders the login page
     */
    public static void login() {
        render("login.html");
    }

    /**
     * Method which will log a user out and clear their session data
     */
    public static void logout() {
        session.clear();
        redirect("/");
    }

    /**
     * Method to register a new member to the database and redirects the user to the dashboard so they can login
     * @param email String
     * @param firstName String
     * @param lastName String
     * @param password String
     * @param gender String
     * @param height double
     * @param startingWeight double
     */
    public static void register(String email, String firstName, String lastName, String password, String gender, double height, double startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(email, firstName, lastName, password, gender, height, startingWeight);
        member.save();
        redirect("/dashboard");
    }

    /**
     * Method which matches the users iput email with their password. If user is a member redirects to members page, if
     * user is a trainer redirects to trainers page, if user fails authentication reloads current page
     * @param email String
     * @param password String
     */
    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);

        Member member = Member.findByEmail(email);
        Trainer trainer = Trainer.findByEmail(email);
        if (member != null) {
            if ((member.checkPassword(password))) {
                Logger.info("Authentication successful");
                session.put("logged_in_MemberId", member.id);
                redirect("/dashboard");
            }
        } else if (trainer != null) {
            if (trainer.checkPassword(password)) {
                Logger.info("Authentication successful");
                session.put("logged_in_TrainerId", trainer.id);
                redirect("/trainerDashboard");
            }
        } else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    /**
     * Method which will get the logged in member so that all updates will be associated with that members id
     * @return Member
     */
    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_MemberId")) {
            String memberId = session.get("logged_in_MemberId");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    /**
     * Method which will get the logged in trainer so that all updates will be associated with that trainers id
     * @return Trainer
     */
    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_TrainerId")) {
            String trainerId = session.get("logged_in_TrainerId");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }

    /**
     * Method to allow a member update their first name
     * @param firstName String
     */
    public static void updateProfileFirstName(String firstName) {
        Member member = getLoggedInMember();
        member.setFirstName(firstName);
        member.save();
        render("account.html", member);
    }

    /**
     * Method to allow a member update their last name
     * @param lastName String
     */
    public static void updateProfileLastName(String lastName) {
        Member member = getLoggedInMember();
        member.setLastName(lastName);
        member.save();
        render("account.html", member);
    }

    /**
     * Method to allow a member update their email
     * @param email String
     */
    public static void updateProfileEmail(String email) {
        Member member = getLoggedInMember();
        member.setEmail(email);
        member.save();
        render("account.html", member);
    }

    /**
     * Method to allow a member update their gender
     * @param gender String
     */
    public static void updateProfileGender (String gender) {
        Member member = getLoggedInMember();
        member.setGender(gender);
        member.save();
        render("account.html", member);
    }

    /**
     * Method to allow a member update their password, must pass an authentication check on the assessment.html page
     * @param password String
     */
    public static void updateProfilePassword (String password) {
        Member member = getLoggedInMember();
        member.setPassword(password);
        member.save();
        render("account.html", member);
    }

    /**
     * Method to allow a member update their height
     * @param height double
     */
    public static void updateProfileHeight (double height) {
        Member member = getLoggedInMember();
        member.setHeight(height);
        member.save();
        render("account.html", member);
    }

    /**
     * MMethod to allow a member update their starting weight
     * @param startingWeight double
     */
    public static void updateProfileStartingWeight (double startingWeight) {
        Member member = getLoggedInMember();
        member.setStartingWeight(startingWeight);
        member.save();
        render("account.html", member);
    }
}
