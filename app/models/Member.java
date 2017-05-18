package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


import java.util.*;

/**
 * Created by Brian on 03/05/2017.
 *
 * Creates an instance of class Member, to log in and add assessments.
 * Member is a concrete implementation of class Person
 * This class also holds various calculations pertaining to the members health and current state
 */
@Entity
public class Member extends Person {
    private double height;
    private double startingWeight;

    /**
     * Each member stores an arraylist of their assessments
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessment = new ArrayList<Assessment>();

    /**
     * Constructor for class Member, creates an instance of this class
     * @param email String inherited from super class
     * @param firstName String inherited from super class
     * @param lastName String inherited from super class
     * @param password String inherited from super class
     * @param gender String inherited from super class
     * @param height double representing members height measured in meters
     * @param startingWeight double representing members measured in kgs
     */
    public Member(String email, String firstName, String lastName, String password, String gender, double height, double startingWeight) {
        super(email, firstName, lastName, password, gender);
        setHeight(height);
        setStartingWeight(startingWeight);
    }

    //Accessor Methods
    /**
     * Method to return members height
     * @return double representing this members height in meters
     */
    public double getHeight() {
        return height;
    }

    /**
     * Method to return members starting weight
     * @return double representing this members starting weight in kgs
     */
    public double getStartingWeight() {
        return startingWeight;
    }

    //Mutator Methods
    /**
     * Method to set members height
     * @param height double to represent this members height. Cannont be lower than 1 or higher than 3
     */
    public void setHeight(double height) {
        if ((height >= 1) && (height <= 3)) {
            this.height = height;
        }
    }

    /**
     * Method to set members starting weight
     * @param startingWeight double to represent this members starting weight. Must be between 35 and 250
     */
    public void setStartingWeight(double startingWeight) {
        if ((startingWeight >= 35) && (startingWeight <= 250)) {
            this.startingWeight = startingWeight;
        }
    }

    /**
     * Method to search for this member based on their email
     * @param email String email used to search for member
     * @return The first member found who's email matches the passed in string
     */
    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    /**
     * Method to check password for authentication purposes
     * @param password String password to be entered during log in process
     * @return true if the passed in password matches this members password
     */
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Method to return this members last assessment from their arraylist
     * @return The instance of class Assessment that was stored last in the members assessment arraylist
     */
    public Assessment latestAssessment() {
        return assessment.get(assessment.size() - 1);
    }

    /**
     * Method to determine the members BMI based on the devine method using their last assessment.
     * If there is no last assessment it will use the members starting weight instead
     * @return double representing this members BMI
     */
    public double calculateBMI() {
        if (assessment.size() > 0) {
            Assessment assessment = latestAssessment();
            return toTwoDecimalPlaces(assessment.getWeight() / (getHeight() * getHeight()));
        }
        return toTwoDecimalPlaces(startingWeight / (height * height));
    }

    /**
     * Method to determine the colour of the trend icon based on whether a member moves further or closer to an ideal BMI
     * Three different levels based off of no assessments, one assessment and more than one assessment
     * @return String of colour to be used in dashboard.html
     */
    public String assessmentTrend() {
        int idealBMI = 22;
        if (assessment.size() == 1) {
            double previousBMI = (startingWeight / (getHeight() * getHeight()));
            if(Math.abs(calculateBMI() - idealBMI) < Math.abs(previousBMI - idealBMI)) {
                return "green";
            }
            else
                return "red";
        } else if (assessment.size() > 1) {
            Assessment previousAssessment = assessment.get(assessment.size() - 2);
            double previousBMI = (previousAssessment.getWeight() / (getHeight() * getHeight()));
            if (Math.abs(calculateBMI() - idealBMI) < Math.abs(previousBMI - idealBMI)) {
                return "green";
            }
            else
                return "red";
        } else
            return "black";
    }

    /**
     * Method to determine the BMI category of the member based on the calculateBMI method
     * @param bmiValue double received from the calculateBMI() method
     * @return String representing one of 7 different categories of BMI
     */
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

    /**
     * Method to determine whether or not a member is of an ideal weight for their height
     * Checks for height, weight, gender and any previous assessments added for this member
     * @return String of colour to be used in dashboard.html for the ideal weight indicator
     */
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

    /**
     * Method to convert a members height from meters into inches
     * @return double representing this members height in inches
     */
    public double convertHeightMetresToInches() {
        return toTwoDecimalPlaces(getHeight() * 39.37);
    }
    /**
     * Method to convert a members weight from kgs into pounds
     * @return double representing this members weight in pounds
     */
    public double convertWeightKgToPounds() {
        return toTwoDecimalPlaces(getStartingWeight() * 2.2);
    }

    /**
     * Method used in many other methods of this class, truncates doubles to 2 decimal places
     * @param num double of more than 2 decimal places
     * @return double truncated to 2 decimal places
     */
    private static double toTwoDecimalPlaces(double num) {
        return (int) (num * 100) / 100.0;
    }
}
