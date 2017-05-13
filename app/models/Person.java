package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Brian on 03/05/2017.
 */

@Entity
public abstract class Person extends Model {
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public String gender;

    //Constructor for class models.Person
    public Person(String email, String firstName, String lastName, String password, String gender) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setGender(gender);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    //Mutator Methods
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        if ((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("m"))) {
            this.gender = "M";
        } else if (gender.toLowerCase().equals("female") || (gender.toLowerCase().equals("f"))) {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }
}
