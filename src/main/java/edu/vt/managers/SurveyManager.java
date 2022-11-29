/*
 * Created by Osman Balci on 2021.7.20
 * Copyright © 2021 Osman Balci and Rob Guldi. All rights reserved.
 */
package edu.vt.managers;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import edu.vt.EntityBeans.User;
import edu.vt.controllers.UserSurveyController;
import edu.vt.globals.Methods;
import edu.vt.pojo.Category;
import org.primefaces.shaded.json.JSONArray;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named(value = "surveyManager")
@SessionScoped

public class SurveyManager implements Serializable {
    /*
    ==================
    Instance Variables
    ==================
     */
    @Inject
    private UserSurveyController userSurveyController;

    // 'items' is a List containing the object references of Category objects
    private List<Category> items = null;

    public String[] getQuestion3Options() {
        return question3Options;
    }

    public void setQuestion3Options(String[] question3Options) {
        this.question3Options = question3Options;
    }

    public String[] getQuestion56Options() {
        return question56Options;
    }

    public void setQuestion56Options(String[] question56Options) {
        this.question56Options = question56Options;
    }

    public String[] getQuestion7Options() {
        return question7Options;
    }

    public void setQuestion7Options(String[] question7Options) {
        this.question7Options = question7Options;
    }

    public String[] getQuestion10Options() {
        return question10Options;
    }

    public void setQuestion10Options(String[] question10Options) {
        this.question10Options = question10Options;
    }

    private String[] question3Options = {"Aerobics","Cycling","Gym Workouts","Gymnastics","Hiking","Pilates","Racquetball","Running","Swimming","Team Sport","Walking","Other"};

    private String[] question56Options = {"Never","1 time per week","2-3 times per week","4-6 times per week","1 time per day","2 times per day","3+ times per day"};

    private String[] question7Options = {"Chips, crackers, nuts","Fast foods (e.g. pizza, fries)","Fruits, vegetables","Ice cream, chocolate, cookies, candy","Protein bar, fruit and nut bar, energy bar","Other"};

    private String[] question10Options = {"Exercise","Family relationships","Hobbies","Prayer / spiritual activities","Relaxation techniques","Social relationships","Time management","Other"};

    private Integer question1Answer;

    private Integer question2Answer;

    private String question3Answer;

    private Integer question4Answer;

    private String question5Answer;

    private String question6Answer;

    private String question7Answer;

    private Integer question8Answer;

    private Integer question9Answer;

    private String question10Answer;

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public Integer getQuestion1Answer() {
        return question1Answer;
    }

    public void setQuestion1Answer(Integer question1Answer) {
        this.question1Answer = question1Answer;
    }

    public Integer getQuestion2Answer() {
        return question2Answer;
    }

    public void setQuestion2Answer(Integer question2Answer) {
        this.question2Answer = question2Answer;
    }

    public String getQuestion3Answer() {
        return question3Answer;
    }

    public void setQuestion3Answer(String question3Answer) {
        this.question3Answer = question3Answer;
    }

    public Integer getQuestion4Answer() {
        return question4Answer;
    }

    public void setQuestion4Answer(Integer question4Answer) {
        this.question4Answer = question4Answer;
    }

    public String getQuestion5Answer() {
        return question5Answer;
    }

    public void setQuestion5Answer(String question5Answer) {
        this.question5Answer = question5Answer;
    }

    public String getQuestion6Answer() {
        return question6Answer;
    }

    public void setQuestion6Answer(String question6Answer) {
        this.question6Answer = question6Answer;
    }

    public String getQuestion7Answer() {
        return question7Answer;
    }

    public void setQuestion7Answer(String question7Answer) {
        this.question7Answer = question7Answer;
    }

    public Integer getQuestion8Answer() {
        return question8Answer;
    }

    public void setQuestion8Answer(Integer question8Answer) {
        this.question8Answer = question8Answer;
    }

    public Integer getQuestion9Answer() {
        return question9Answer;
    }

    public void setQuestion9Answer(Integer question9Answer) {
        this.question9Answer = question9Answer;
    }

    public String getQuestion10Answer() {
        return question10Answer;
    }

    public void setQuestion10Answer(String question10Answer) {
        this.question10Answer = question10Answer;
    }


    /*
    ================
    Instance Methods
    ================

    ***********************************
    Process the Submitted Survey
    ***********************************
     */
    public String processSurvey() {

        // Instantiate a new 'items' List to contain the object references of the Category objects
        items = new ArrayList<>();

        String answers[] = {String.valueOf(question1Answer), String.valueOf(question2Answer), question3Answer, String.valueOf(question4Answer), question5Answer,
                            question6Answer, question7Answer, String.valueOf(question8Answer), String.valueOf(question9Answer), question10Answer};
        
        Integer question_number;
        String question_title;
        String question_answer;
        Category category;
        for(Integer i = 0; i < 10; i++) {
            items.add(new Category(i+1, titleOfQuestion(i+1), answers[i]));
        }

        /*
        The UserSurvey Table in BevqDB database has the following attributes to set:
            Integer id (The DB Primary Key id value is generated and set at the time of UserSurvey object creation)
            Date    dateEntered
            float   fluidOuncesConsumed
            float   kcalIntake
            String  survey (Stored as MEDIUMTEXT in BevqDB containing the JSON Array of all BEVQ 15 categories)
            User    userId
         */
        //--------------------------------------
        // Create a new UserSurvey object
        //--------------------------------------
        userSurveyController.prepareCreate();

        //-----------------------
        // Set id attribute value 
        //-----------------------
        /*
        The database Primary Key id value is generated and set at the time of UserSurvey object creation.
         */
        //--------------------------------
        // Set dateEntered attribute value 
        //--------------------------------
        LocalDate localDate = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);

        // Set Date in the format of YYYY-MM-DD
        userSurveyController.getSelected().setDateEntered(currentDate);

        //----------------------------------
        // Set survey attribute value 
        //----------------------------------
        /*
        ----------------------------------------------------------------------------------------------
        Convert the List of Category objects Integer of an array of JSON objects as depicted below:
        [
            {
                "howOftenTitle":"3+ times per day",
                "howMuchTitle":"8 fl oz (1 cup)",
                "caloricDensityInKcalPerFluidOunce":0,
                "fluidOuncePerDay":8,
                "question_number":1,
                "optionsOther":"Club Soda, Tonic Water: 2-3 times per week each time 8 fl oz (1 cup)",
                "averageDailyCaloricIntakeInKcal":0,
                "frequencyPerDay":3,
                "question_title":"Water or Unsweetened Sparkling Water",
                "averageDailyConsumptionInFluidOunce":26.856
            },
            :
            :
        ]
        This conversion calls each Getter method in the Category class to define the KEY:VALUE pair of
        a JSON object for each Category object attribute as shown above. If you include other 
        Getter methods in the Category class, their values will also be included in the JSON file.
        Note that the JSON object {...} lists the Category object attributes in no particular order.
        ----------------------------------------------------------------------------------------------
         */
        JSONArray jasonArray = new JSONArray(items);

        // Convert the JSON array Integero a String
        String survey = jasonArray.toString();
        System.out.println("Survey JSON: " + survey);
        // Set the survey attribute value
        userSurveyController.getSelected().setSurvey(survey);

        //---------------------------
        // Set userId attribute value 
        //---------------------------
        // Obtain the object reference of the signed-in user
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        User signedInUser = (User) sessionMap.get("user");

        userSurveyController.getSelected().setUserId(signedInUser);

        //----------------------------------------------------------
        // Store the newly created UserSurvey in the database
        //----------------------------------------------------------
        userSurveyController.create();

        //-----------------------------------------------------------
        // Clear the survey content without displaying message
        //-----------------------------------------------------------
        clearSurvey(false);

        return "/index?faces-redirect=true";
    }

    /*
    *********************************************
    Return the Title of the Question specified. *
    *********************************************
     */
    private String titleOfQuestion(Integer q) {

        switch(q) {
            case 1:
                return "How physically healthy are you?";
            case 2:
                return "How important is exercise to you?";
            case 3:
                return "What do you most often do to exercise?";
            case 4:
                return "How many times do you exercise per week?";
            case 5:
                return "How often do you drink regular soda or other sugared beverages?";
            case 6:
                return "How often do you consume alcohol?";
            case 7:
                return "What types of food do you usually snack on?";
            case 8:
                return "How many hours of sleep do you get each night on average?";
            case 9:
                return "What is your stress level?";
            case 10:
                return "Which of the following is most effective in coping with your stress?";
            default:
                throw new IndexOutOfBoundsException(q);
        }
    }

    /*
    ***********************************************************
    Return the Title of How Often Given the User Selected Value
    ***********************************************************
     */
    private String howOftenTitle(String itemValue) {

        String howOftenTitle = "";

        switch (itemValue) {
            case "0":
                howOftenTitle = "Never or < 1 time per week";
                break;
            case "0.143":
                howOftenTitle = "1 time per week";
                break;
            case "0.357":
                howOftenTitle = "2-3 times per week";
                break;
            case "0.714":
                howOftenTitle = "4-6 times per week";
                break;
            case "1":
                howOftenTitle = "1 time per day";
                break;
            case "2":
                howOftenTitle = "2 times per day";
                break;
            case "3":
                howOftenTitle = "3+ times per day";
                break;
            default:
                System.out.println("howOftenTitle itemValue is out of range!");
                break;
        }

        return howOftenTitle;
    }

    /*
    **********************************************************
    Return the Title of How Much Given the User Selected Value
    **********************************************************
     */
    private String howMuchTitle(String itemValue) {

        String howMuchTitle = "";

        switch (itemValue) {
            case "0":
                howMuchTitle = "I do not drink it";
                break;
            case "4":
                howMuchTitle = "&lt; 6 fl oz (&frac34; cup)";
                break;
            case "8":
                howMuchTitle = "8 fl oz (1 cup)";
                break;
            case "12":
                howMuchTitle = "12 fl oz (1&frac12; cups)";
                break;
            case "16":
                howMuchTitle = "16 fl oz (2 cups)";
                break;
            case "20":
                howMuchTitle = "20 fl oz (2&frac12; cups)";
                break;
            case "24":
                howMuchTitle = "24 fl oz (3 cups)";
                break;
            case "28":
                howMuchTitle = "28 fl oz (3&frac12; cups)";
                break;
            case "32":
                howMuchTitle = "32 fl oz (4 cups)";
                break;
            default:
                System.out.println("howMuchTitle itemValue is out of range!");
                break;
        }

        return howMuchTitle;
    }

    /*
    ************************************************
    Clear All of the Selections in the Survey
    ************************************************
     */
    public void clearSurvey(Boolean displayMessage) {

        items = null;

        question1Answer = null;
        question2Answer = null;
        question3Answer = null;
        question4Answer = null;
        question5Answer = null;
        question6Answer = null;
        question7Answer = null;
        question8Answer = null;
        question9Answer = null;
        question10Answer = null;

        if (displayMessage) {
            Methods.showMessage("Information", "Cleared!",
                    "All Selections are Reset!");
        }
    }

    /*
    *******************************************
    Pre-process the PDF File to be in Landscape 
    Orientation on Letter Size Paper
    *******************************************
     */
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
    }

    /*
    *****************************************
    Warn the User for 5 Minutes of Inactivity
    *****************************************
     */
    public void onIdle() {
        Methods.showMessage("Warning", "No User Action for the Last 5 Minutes!",
                "Do You Need More Time?");
    }

    /*
    ***************************************************
    Welcome Back the User After 5 Minutes of Inactivity
    ***************************************************
     */
    public void onActive() {
        Methods.showMessage("Warning", "Welcome Back!",
                "Please Continue Filling Out Your Questionanire!");
    }

}
