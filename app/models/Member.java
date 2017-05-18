package models;

import controllers.Accounts;

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

    public double calculateBMI() {
        if (assessment.size() > 0) {
            Assessment assessment = latestAssessment();
            return toTwoDecimalPlaces(assessment.getWeight() / (getHeight() * getHeight()));
        }
        return toTwoDecimalPlaces(startingWeight / (height * height));
    }

    public String assessmentTrend() {
        int idealBMI = 22;
        if (assessment.size() == 1) {
            double previousBMI = startingWeight / (getHeight() * getHeight());
            if (((calculateBMI() < previousBMI) && (calculateBMI() >= idealBMI))
                    || (calculateBMI() > previousBMI) && (calculateBMI() <= idealBMI)) {
                return "green";
            } else
                return "red";
        } else if (assessment.size() > 1) {
            Assessment previousAssessment = assessment.get(assessment.size() - 2);
            double previousBMI = previousAssessment.getWeight() / (getHeight() * getHeight());
            if (((calculateBMI() < previousBMI) && (calculateBMI() >= idealBMI))
                    || (calculateBMI() > previousBMI) && (calculateBMI() <= idealBMI)) {
                return "green";
            } else
                return "red";
        } else
            return "black";
    }

    public String determineBMICategory(double bmiValue) {
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

    public String isIdealBodyWeight() {
        if (assessment.size() > 0) {
            Assessment assessment = latestAssessment();
            if (getGender().equals("M")) {
                if (convertHeightMetresToInches() > 60) {
                    if ((((convertHeightMetresToInches() - 60) * 2.3) + 50) <= ((assessment.getWeight() + 2))
                            && ((((convertHeightMetresToInches() - 60) * 2.3) + 50) >= ((assessment.getWeight()) - 2))) {
                        return "green";
                    } else {
                        return "red";
                    }
                } else {
                    if ((50) <= ((assessment.getWeight() + 2)) && ((50) >= ((assessment.getWeight() - 2)))) {
                        return "green";
                    } else {
                        return "red";
                    }
                }
            } else {
                if (convertHeightMetresToInches() > 60) {
                    if ((((convertHeightMetresToInches() - 60) * 2.3) + 45.5) <= ((assessment.getWeight() + 2))
                            && ((((convertHeightMetresToInches() - 60) * 2.3) + 45.5) >= ((assessment.getWeight() - 2)))) {
                        return "green";
                    } else {
                        return "red";
                    }
                } else {
                    if ((45.5) <= ((assessment.getWeight() + 2)) && ((45.5) >= ((assessment.getWeight() - 2)))) {
                        return "green";
                    } else {
                        return "red";
                    }
                }
            }
        } else if (getGender().equals("M")) {
            if (convertHeightMetresToInches() > 60) {
                if ((((convertHeightMetresToInches() - 60) * 2.3) + 50) <= ((getStartingWeight() + 2))
                        && ((((convertHeightMetresToInches() - 60) * 2.3) + 50) >= ((getStartingWeight() - 2)))) {
                    return "green";
                } else {
                    return "red";
                }
            } else {
                if ((50) <= ((getStartingWeight() + 2)) && ((50) >= ((getStartingWeight() - 2)))) {
                    return "green";
                } else {
                    return "red";
                }
            }
        } else {
            if (convertHeightMetresToInches() > 60) {
                if ((((convertHeightMetresToInches() - 60) * 2.3) + 45.5) <= ((getStartingWeight() + 2))
                        && ((((convertHeightMetresToInches() - 60) * 2.3) + 45.5) >= ((getStartingWeight() - 2)))) {
                    return "green";
                } else {
                    return "red";
                }
            } else {
                if ((45.5) <= ((getStartingWeight() + 2)) && ((45.5) >= ((getStartingWeight() - 2)))) {
                    return "green";
                } else {
                    return "red";
                }
            }
        }
    }

    public double convertHeightMetresToInches() {
        return toTwoDecimalPlaces(getHeight() * 39.37);
    }

    public double convertWeightKgToPounds() {
        return toTwoDecimalPlaces(getStartingWeight() * 2.2);
    }

    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }

}
