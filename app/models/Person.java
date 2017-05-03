package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Brian on 03/05/2017.
 */

@Entity
public abstract class Person extends Model {
    private String email;
    private String name;
    private String password;
    private String address;
    private String gender;

    //Constructor for class models.Person
    public Person(String email, String name, String password, String address, String gender) {
        setEmail(email);
        setName(name);
        setPassword(password);
        setAddress(address);
        setGender(gender);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    //Mutator Methods
    private void setEmail(String email) {
        this.email = email;
    }

    private void setName(String name) {
        if (name.length() > 30) {
            this.name = name.substring(0, 30);
        } else {
            this.name = name;
        }
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setGender(String gender) {
        if ((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("m"))) {
            this.gender = "M";
        } else if (gender.toLowerCase().equals("female") || (gender.toLowerCase().equals("f"))) {
            this.gender = "F";
        } else {
            this.gender = "Unspecified";
        }
    }
}
