package utils;

import models.Member;
import models.Assessment;
import play.db.jpa.Model;

/**
 * Created by Brian on 24/04/2017.
 */
public class Analytics extends Model {

    public static double calculateBMI(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight() * member.getHeight()));
    }

    public static String determineBMICategory(double bmiValue) {
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
