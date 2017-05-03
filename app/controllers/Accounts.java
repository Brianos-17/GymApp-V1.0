package controllers;

import play.Logger;
import play.mvc.Controller;
import models.Member;

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

    public static void register(String name, String email, String password, String gender, double height, double startingWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(name, email, password, gender, height, startingWeight);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);

        Member member = Member.findByEmail(email);
        if((member != null) && (member.checkPassword(password))){
            Logger.info("Authentication successful");
            session.put("logged_in_MemberId", member.id);
            redirect("/dashboard");
        }
        else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if(session.contains("logged_in_MemberId")) {
            String memberId = session.get("logged_in_MemberId");
            member = Member.findById(Long.parseLong(memberId));
        }
        else {
            login();
        }
        return member;
    }
}
