/*
 * Created by Osman Balci on 2021.7.20
 * Copyright © 2021 Osman Balci and Rob Guldi. All rights reserved.
 */
package edu.vt.EntityBeans;

import edu.vt.pojo.Category;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// The @Entity annotation designates this class as a JPA Entity class representing the UserSurvey table in the BevqDB database.
@Entity

// Name of the database table represented
@Table(name = "UserSurvey")

@NamedQueries({
    @NamedQuery(name = "UserSurvey.findAll", query = "SELECT u FROM UserSurvey u")
    , @NamedQuery(name = "UserSurvey.findById", query = "SELECT u FROM UserSurvey u WHERE u.id = :id")
    , @NamedQuery(name = "UserSurvey.findByDateEntered", query = "SELECT u FROM UserSurvey u WHERE u.dateEntered = :dateEntered")
    , @NamedQuery(name = "UserSurvey.findSurveysByUserPrimaryKey", query = "SELECT u FROM UserSurvey u WHERE u.userId.id = :primaryKey")
})
/* 
 The findSurveysByUserPrimaryKey query is added. The others are auto generated. 
 userId     = object reference of the User object (See below: private User userId;)
 userId.id  = User object's database Primary Key to be set
 primaryKey = User object's database Primary Key given
 */

public class UserSurvey implements Serializable {
    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the UserSurvey table in the BevqDB database.

    CREATE TABLE UserSurvey
    (
        id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
        date_entered DATE NOT NULL,
        fluid_Ounces_Consumed FLOAT(8,3) NOT NULL,
        kcal_Intake FLOAT(8,3) NOT NULL,
        survey MEDIUMTEXT NOT NULL,
        user_id INT UNSIGNED,
        FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
    );
    ========================================================
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_entered")
    @Temporal(TemporalType.DATE)
    private Date dateEntered;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "survey")
    private String survey;

    // user_id INT UNSIGNED
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    /*
    =========================================================================
    Class constructors for instantiating a UserSurvey entity object to
    represent a row in the UserSurvey table in the BevqDB database.
    =========================================================================
     */
    public UserSurvey() {
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the UserSurvey table in the BevqDB database.
    ======================================================
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    /*
    ================================
    Instance Methods Used Internally
    ================================
     */

    // Generate and return a hash code value for the object with database primary key id
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the UserSurvey object identified by 'object' is the
     same as the UserSurvey object identified by 'id'
     Parameter object = UserSurvey object identified by 'object'
     Returns True if the UserSurvey 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserSurvey)) {
            return false;
        }
        UserSurvey other = (UserSurvey) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    public List<Category> getQA() {

        ArrayList<Category> QA = new ArrayList<>();

        String survey = getSurvey();
        System.out.println(survey);
        JSONArray jsonArray = new JSONArray(survey);

        jsonArray.forEach(object -> {
            // Typecast the object as JSONObject
            JSONObject jsonObject = (JSONObject) object;

            Integer question_number = jsonObject.getInt("question_number");
            System.out.println("questionNumber: " + question_number);
            String question_title = jsonObject.getString("question_title");
            System.out.println("questionName: " + question_title);
            String question_answer = jsonObject.getString("question_answer");
            System.out.println("questionAnswer: " + question_answer);

            // Create a Category object using the attributes (Key-Value pairs) of the jsonObject
            Category category = new Category(question_number, question_title, question_answer);

            // Add newly created Category object to the ArrayList
            QA.add(category);
        });

        return QA;
    }


    // Return String representation of database primary key id
    @Override
    public String toString() {
        return id.toString();
    }

}
