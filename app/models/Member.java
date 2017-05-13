package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import java.util.*;

/**
 * Created by Brian on 03/05/2017.
 */
@Entity
public class Member extends Person {
    private double height;
    private double startingWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessment = new ArrayList<Assessment>();

    public Member(String email, String firstName, String lastName, String password, String gender, double height, double startingWeight) {
        super(email, firstName, lastName, password, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
    }

    //Accessor Methods
    public double getHeight() {
        return height;
    }

    public double getStartingWeight() {
        return startingWeight;
    }

    //Mutator Methods
    public void setHeight(double height) {
        if ((height >= 1) && (height <= 3)) {
            this.height = height;
        }
    }

    public void setStartingWeight(double startingWeight) {
        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.startingWeight = startingWeight;
        }
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public Assessment latestAssessment() {
            return assessment.get(assessment.size() - 1);
    }

    public  double calculateBMI() {
        if (assessment.size() > 0) {
            Assessment assessment = latestAssessment();
            return toTwoDecimalPlaces(assessment.getWeight() / (getHeight() * getHeight()));
        }
        return 0.0;
    }

    public String determineBMICategory(double bmiValue) {
        if (assessment.size() > 0) {
            if (bmiValue < 15) {
                return "VERY SEVERELY UNDERWEIGHT";
            } else if ((bmiValue >= 15) && (bmiValue < 16)) {
                return "SEVERELY UNDERWEIGHT";
            } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
                return "UNDERWEIGHT";
            } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
                return "NORMAL";
            } else if ((bmiValue >= 25) && (bmiValue < 30)) {
                return "OVERWEIGHT";
            } else if ((bmiValue >= 30) && (bmiValue < 35)) {
                return "MODERATELY OBESE";
            } else if ((bmiValue >= 35) && (bmiValue < 40)) {
                return "SEVERELY OBESE";
            } else {
                return "VERY SEVERELY OBESE";
            }
        }
        return "NO VALID DATA";
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        if (member.getGender().equals("M")) {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 50) >= ((assessment.getWeight()) - 2))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((50) <= ((assessment.getWeight() + 2)) && ((50) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (convertHeightMetresToInches(member) > 60) {
                if ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) <= ((assessment.getWeight() + 2))
                        && ((((convertHeightMetresToInches(member) - 60) * 2.3) + 45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((45.5) <= ((assessment.getWeight() + 2)) && ((45.5) >= ((assessment.getWeight() - 2)))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public static double convertHeightMetresToInches(Member member) {
        return toTwoDecimalPlaces(member.getHeight() * 39.37);
    }

    public static double convertWeightKgToPounds(Assessment assessment){
        return toTwoDecimalPlaces((assessment.getWeight()) *2.2 );
    }

    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }

}
