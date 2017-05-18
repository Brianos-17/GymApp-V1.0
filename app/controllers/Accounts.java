package controllers;

import models.Trainer;
import play.Logger;
import play.mvc.Controller;
import models.Member;

import java.util.List;

/**
 * Created by Brian on 03/05/2017.
 */
public class Accounts extends Controller {

    public static void signup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static void register(String email, String firstName, String lastName, String password, String gender, double height, double startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(email, firstName, lastName, password, gender, height, startingWeight);
        member.save();
        redirect("/dashboard");
    }

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

    public static void updateProfileFirstName(String firstName) {
        Member member = getLoggedInMember();
        member.setFirstName(firstName);
        member.save();
        render("account.html", member);
    }

    public static void updateProfileLastName(String lastName) {
        Member member = getLoggedInMember();
        member.setLastName(lastName);
        member.save();
        render("account.html", member);
    }

    public static void updateProfileEmail(String email) {
        Member member = getLoggedInMember();
        member.setEmail(email);
        member.save();
        render("account.html", member);
    }

    public static void updateProfileGender (String gender) {
        Member member = getLoggedInMember();
        member.setGender(gender);
        member.save();
        render("account.html", member);
    }

    public static void updateProfilePassword (String password) {
        Member member = getLoggedInMember();
        member.setPassword(password);
        member.save();
        render("account.html", member);
    }

    public static void updateProfileHeight (double height) {
        Member member = getLoggedInMember();
        member.setHeight(height);
        member.save();
        render("account.html", member);
    }

    public static void updateProfileStartingWeight (double startingWeight) {
        Member member = getLoggedInMember();
        member.setStartingWeight(startingWeight);
        member.save();
        render("account.html", member);
    }
}
