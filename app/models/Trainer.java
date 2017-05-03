package models;

/**
 * Created by Brian on 03/05/2017.
 */
public class Trainer extends Person {
    private String speciality;

    public Trainer (String email, String firstName, String lastName, String password, String gender, String speciality)
    {
        super(email, firstName, lastName, password, gender);
        setSpeciality(speciality);
    }

    //Mutator Method
    public String getSpeciality() {
        return speciality;
    }

    //Accessor Method
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

}
