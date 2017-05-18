package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by Brian on 03/05/2017.
 *
 * Person is an abstract class which is made concrete by creating an instance of either Member or Trainer
 */

@Entity
public abstract class Person extends Model {
    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public String gender;

    /**
     * Constructor for Abstract class Person
     *
     * @param email String representing persons email
     * @param firstName String representing persons first name
     * @param lastName String representing persons last name
     * @param password String representing persons password
     * @param gender String representing persons gender, will be set to "M", "F" or "Unspecified"
     */
    public Person(String email, String firstName, String lastName, String password, String gender) {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setGender(gender);
    }

    //Accessor Methods
    /**
     * Method to return Persons email
     * @return the persons email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to return persons first name
     * @return persons first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to return persons last name
     * @return persons last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to return persons password
     * @return persons password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to return persons gender
     * @return persons gender as "M", "F" or "Unspecified"
     */
    public String getGender() {
        return gender;
    }

    //Mutator Methods

    /**
     * Method to set persons email
     * @param email String to be set as this persons email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to set persons first name
     * @param firstName String to be set as this persons first name
     */
    public void setFirstName(String firstName) {
            this.firstName = firstName;
    }

    /**
     * Method to set persons last name
     * @param lastName String to be set as this persons last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method to set persons password
     * @param password String to be set as this persons password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to set persons gender
     * @param gender String which is case independent and will be shortened to "M", "F" or "Unspecified"
     */
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
