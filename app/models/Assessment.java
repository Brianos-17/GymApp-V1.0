package models;

import play.db.jpa.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;

/**
 * Created by Brian on 03/05/2017.
 *
 * CAssessment class used to store details which members and trainers wish to keep track of
 */
@Entity
public class Assessment extends Model {
    private String date;
    private double weight;
    private double chest;
    private double thigh;
    private double upperArm;
    private double waist;
    private double hips;
    private String trend;
    private String comment;

    /**
     * Constructor for class Assessment, creates an instance of this class
     * @param weight double representing the weight for a member for this assessment
     * @param chest double representing the chest measurement for a member for this assessment
     * @param thigh double representing the thigh measurement for a member for this assessment
     * @param upperArm double representing the upper arm measurement for a member for this assessment
     * @param waist double representing the waist measurement for a member for this assessment
     * @param hips double representing the hip measurement for a member for this assessment
     * @param trend String of colour to be used in dashboard.html for the assessment trend
     * @param comment String comment which gets added by a trainer
     */
    public Assessment(double weight, double chest, double thigh, double upperArm, double waist, double hips,
                      String trend, String comment) {
        setDate(date);
        setWeight(weight);
        setChest(chest);
        setThigh(thigh);
        setUpperArm(upperArm);
        setWaist(waist);
        setHips(hips);
        setTrend(trend);
        setComment(comment);
    }

    //Mutator Methods
    /**
     * Method to add a date to an assessment automatically without need to pass it as a parameter though the constructor
     * @param date String representing the current date, passed through a simple date format
     */
    public void setDate(String date) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = dateFormat.format(currentDate);
        this.date = date;
    }

    /**
     * MEthod to add a weight to an assessment
     * @param weight double representing the weight for this assessment
     */
    public void setWeight(double weight) {
            this.weight = weight;
    }

    /**
     * Method to add chest measurements to an assessment
     * @param chest double representing the chest measurement for this assessment
     */
    public void setChest(double chest) {
        this.chest = chest;
    }

    /**
     * Method to add thigh measurement to an assessment
     * @param thigh double representing the thigh measurement for this assessment
     */
    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    /**
     * Method to add upper arm measurement to an assessment
     * @param upperArm double representing the upper arm for this assessment
     */
    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    /**
     * Method to add a waist measurement to an assessment
     * @param waist double representing the waist measurement for this assessment
     */
    public void setWaist(double waist) {
        this.waist = waist;
    }

    /**
     * Method to add hips measurement to an assessment
     * @param hips double representing the hips measurement for this assessment
     */
    public void setHips(double hips) {
        this.hips = hips;
    }

    /**
     * Method to add trend to an assessment
     * @param trend String representing the colour the trend icon will be changed to
     */
    public void setTrend(String trend) {
        this.trend = trend;
    }

    /**
     * Method to add a comment to an assessment
     * @param comment String representing the comment the trainer adds for this assessment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    //Accessor Methods
    /**
     * Method to return the Date for this assessment
     * @return String representing the Date in a simple date format
     */
    public String getDate() {
        return date;
    }

    /**
     * Method to return the weight for this assessment
     * @return double representing the weight for this assessment
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Method to return the chest measurement for this assessment
     * @return double representing the chest for this assessment
     */
    public double getChest() {
        return chest;
    }

    /**
     * Method to return the thigh measurement for this assessment
     * @return double representing the thigh for this assessment
     */
    public double getThigh() {
        return thigh;
    }

    /**
     * Method to return the upper arm measurement for this assessment
     * @return double representing the upper arm for this assessment
     */
    public double getUpperArm() {
        return upperArm;
    }

    /**
     * Method to return the waist measurement for this assessment
     * @return double representing the waist for this assessment
     */
    public double getWaist() {
        return waist;
    }

    /**
     * Method to return the hip measurement for this assessment
     * @return double representing the hips for this assessment
     */
    public double getHips() {
        return hips;
    }

    /**
     * Method to return the trend for this assessment
     * @return String representing the colour the trend icon will be changed to
     */
    public String getTrend() {
        return trend;
    }

    /**
     * Method to return the comment for this assessment
     * @return String representing the comment the trainer adds for this assessment
     */
    public String getComment() {
        return comment;
    }

}
