/*
 * Created by Osman Balci on 2021.7.20
 * Copyright © 2021 Osman Balci and Rob Guldi. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.User;
import edu.vt.EntityBeans.UserSurvey;
import edu.vt.FacadeBeans.UserSurveyFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.globals.Methods;
import edu.vt.pojo.Category;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("userSurveyController")
@SessionScoped
public class UserSurveyController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    @EJB
    private UserSurveyFacade userSurveyFacade;

    // 'items' is a List containing the object references of UserSurvey objects
    private List<UserSurvey> items = null;

    private List<UserSurvey> allItems = null;

    // 'selected' contains the object reference of the selected UserSurvey object
    private UserSurvey selected;

    // 'bevqItems' is a List containing the object references of Category objects
    private List<Category> bevqItems = null;

    private String answerToSecurityQuestion;

    /*
    ***************************************************************
    Return the List of Surveys Created by the Signed-In User
    ***************************************************************
     */
    public List<UserSurvey> getItems() {
        if (items == null) {
            /*
            user_id (database primary key) was put into the SessionMap in the
            initializeSessionMap() method in LoginManager upon user's sign in.
             */
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            int userPrimaryKey = (int) sessionMap.get("user_id");

            items = userSurveyFacade.findUserSurveysByUserPrimaryKey(userPrimaryKey);
        }
        return items;
    }
    public List<UserSurvey> getAllItems() {
        if (allItems == null) {
            allItems = userSurveyFacade.getAllSurveys();
        }
        return allItems;
    }


    public void setAllItems(List<UserSurvey> allItems) {this.allItems = allItems; }

    public void setItems(List<UserSurvey> items) {
        this.items = items;
    }

    public UserSurvey getSelected() {
        return selected;
    }

    public void setSelected(UserSurvey selected) {
        bevqItems = null;    // Invalidate list of BEVQ items to trigger re-query.
        this.selected = selected;
    }

    public List<Category> getBevqItems() {
        if (bevqItems == null) {

            bevqItems = new ArrayList<>();

            String survey = selected.getSurvey();
            JSONArray jsonArray = new JSONArray(survey);

            jsonArray.forEach(object -> {
                // Typecast the object as JSONObject
                JSONObject jsonObject = (JSONObject) object;

                Integer question_number = jsonObject.getInt("question_number");
                String question_title = jsonObject.getString("question_title");
                String question_answer = jsonObject.getString("question_answer");

                // Create a Category object using the attributes (Key-Value pairs) of the jsonObject
                Category category = new Category(question_number, question_title, question_answer);

                // Add newly created Category object to the ArrayList
                bevqItems.add(category);
            });
        }
        return bevqItems;
    }

    public void setBevqItems(List<Category> bevqItems) {
        this.bevqItems = bevqItems;
    }

    public String getAnswerToSecurityQuestion() {
        return answerToSecurityQuestion;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    /*
    ================
    Instance Methods
    ================

    *****************************************************
    Process the Submitted Answer to the Security Question
    *****************************************************
     */
    public void securityAnswerSubmit() {
        /*
        'user', the object reference of the signed-in user, was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        User signedInUser = (User) sessionMap.get("user");

        String actualSecurityAnswer = signedInUser.getSecurityAnswer();
        String enteredSecurityAnswer = getAnswerToSecurityQuestion();

        if (actualSecurityAnswer.equals(enteredSecurityAnswer)) {
            // Answer to the security question is correct. Delete the selected survey.
            deleteSurvey();

        } else {
            Methods.showMessage("Error",
                    "Answer to the Security Question is Incorrect!", "");
        }
    }

    /*
    *************************************
    Prepare to Create a New Survey
    *************************************
     */
    public void prepareCreate() {
        /*
        Instantiate a new UserSurvey object and store its object reference into instance
        variable 'selected'. The UserSurvey class is defined in UserSurvey.java
         */
        selected = new UserSurvey();
    }

    /*
    ******************************************
    Create a New Survey in the Database
    ******************************************
     */
    public void create() {
        Methods.preserveMessages();

        persist(PersistAction.CREATE,"Thank You! Your Survey was Successfully Saved in the Database!");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The CREATE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            allItems = null;
        }
    }

    /*
    ***************************************************
    Delete the Selected Survey from the Database
    ***************************************************
     */
    public void deleteSurvey() {
        Methods.preserveMessages();
        /*
        Show the message "The Survey was Successfully Deleted from the Database!"
        given in the Bundle.properties file under the UserSurveyDeleted keyword.
        
        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the 
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.DELETE,"The Survey was Successfully Deleted from the Database!\n");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            allItems = null;
        }
    }

    /*
     ****************************************************************************
     *   Perform CREATE, EDIT (UPDATE), and DELETE Operations in the Database   *
     ****************************************************************************
     */
    /**
     * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).
                    
                     UserSurveyFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    userSurveyFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.
                    
                     UserSurveyFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    userSurveyFacade.remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
            }
        }
    }

    /*
    ***************************************************
    Type Converter Methods for PrimeFaces Data Exporter 
    Function to Export Data into PDF, Excel, and CSV
    ***************************************************
    
    PrimeFaces p:dataExporter requires the exported values to be of String type.
    These methods are called in SurveyDetails.xhtml.
     */
    public String convertIntToString(Integer value) {
        return Integer.toString(value);
    }

    public String convertDoubleToString(Double value) {
        return Double.toString(value);
    }

}
