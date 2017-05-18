package models;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 03/05/2017.
 */
@Entity
public class Trainer extends Person {
    private String speciality;

    @OneToMany(cascade = CascadeType.ALL)
    public static List<Member> members = new ArrayList<Member>();

    public Trainer (String email, String firstName, String lastName, String password, String gender, String speciality) {
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

    public static Trainer findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
