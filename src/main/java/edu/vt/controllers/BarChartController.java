/*
 * Created by Rob Guldi on 2022.11.29
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.UserSurvey;
import edu.vt.FacadeBeans.UserSurveyFacade;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/*
---------------------------------------------------------------------------
The @Named (javax.inject.Named) annotation indicates that the objects
instantiated from this class will be managed by the Contexts and Dependency
Injection (CDI) container. The name "barChartController" is used within
Expression Language (EL) expressions in JSF (XHTML) facelets pages to
access the properties and invoke methods of this class.
---------------------------------------------------------------------------
 */
@Named("barChartController")

/*
The @SessionScoped annotation preserves the values of the BarChartController
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/*
------------------------------------------------------------------------------
Marking the BarChartController class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized. 

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer, 
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
------------------------------------------------------------------------------
 */
public class BarChartController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================

    Instance variable to contain the object reference of the BarChartModel
    (org.primefaces.model.chart.BarChartModel) object to be instantiated.
     */
    private BarChartModel barModel1;
    private BarChartModel barModel2;
    private BarChartModel barModel3;
    private BarChartModel barModel4;
    private BarChartModel barModel5;
    private BarChartModel barModel6;
    private BarChartModel barModel7;
    private BarChartModel barModel8;
    private BarChartModel barModel9;
    private BarChartModel barModel10;

    private Integer numSurveys;

    public Integer getNumSurveys() {
        return numSurveys;
    }

    public void setNumSurveys(Integer numSurveys) {
        this.numSurveys = numSurveys;
    }

    // Maximum Y axis value of Total Area or Population
    Integer maxAreaOrPopulation;

    /*
    The @Inject annotation directs the CDI Container Manager to inject (store) the object reference of the
    CDI container-managed PickListController bean into the instance variable 'pickListController' after it is instantiated at runtime.
     */
    @Inject
    UserSurveyController userSurveyController;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    CountryFacade bean into the instance variable 'countryFacade' after it is instantiated at runtime.
     */
    @EJB
    private UserSurveyFacade userSurveyFacade;

    /*
    =============
    Getter Method
    =============
     */
    public BarChartModel getBarModel1() {
        createBarModel1();
        return barModel1;
    }

    public BarChartModel getBarModel2() {
        createBarModel2();
        return barModel2;
    }

    public BarChartModel getBarModel3() {
        createBarModel3();
        return barModel3;
    }

    public BarChartModel getBarModel4() {
        createBarModel4();
        return barModel4;
    }

    public BarChartModel getBarModel5() {
        createBarModel5();
        return barModel5;
    }

    public BarChartModel getBarModel6() {
        createBarModel6();
        return barModel6;
    }

    public BarChartModel getBarModel7() {
        createBarModel7();
        return barModel7;
    }

    public BarChartModel getBarModel8() {
        createBarModel8();
        return barModel8;
    }

    public BarChartModel getBarModel9() {
        createBarModel9();
        return barModel9;
    }

    public BarChartModel getBarModel10() {
        createBarModel10();
        return barModel10;
    }

    /*
    ================
    Instance Methods
    ================

    ********************
    Initialize Bar Model
    ********************
     */
    private BarChartModel initBarModel1() {

        BarChartModel model1 = new BarChartModel();

        ChartSeries question1 = new ChartSeries();
        question1.setLabel("1=Not healthy at all, 10=Extremely healthy");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question1Answers[] = new Integer[10];
        Arrays.fill(question1Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            question1Answers[Integer.parseInt(quest.getQA().get(0).getQuestion_answer())-1]++;
        }
        for(int i = 0; i < 10; i++) {
            question1.set(i+1, 100*question1Answers[i]/allSurveys.size());
        }

        model1.addSeries(question1);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model1.setExtender("barChartExtender1");

        return model1;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel1() {

        barModel1 = initBarModel1();

        barModel1.setLegendPosition("ne");

        Axis xAxis = barModel1.getAxis(AxisType.X);
        xAxis.setLabel("Question 1: How physically healthy are you?");

        Axis yAxis = barModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel2() {

        BarChartModel model2 = new BarChartModel();

        ChartSeries question2 = new ChartSeries();
        question2.setLabel("0=Not important at all, 10=Extremely important");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question2Answers[] = new Integer[11];
        Arrays.fill(question2Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            question2Answers[Integer.parseInt(quest.getQA().get(1).getQuestion_answer())]++;
        }
        for(int i = 0; i <= 10; i++) {
            question2.set(i, 100*question2Answers[i]/allSurveys.size());
        }

        model2.addSeries(question2);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model2.setExtender("barChartExtender2");

        return model2;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel2() {

        barModel2 = initBarModel2();

        barModel2.setLegendPosition("ne");

        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("Question 2: How important is exercise to you?");

        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel3() {

        BarChartModel model3 = new BarChartModel();

        ChartSeries question3 = new ChartSeries();
        question3.setLabel("Question 3");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question3Answers[] = new Integer[12];
        String question3Options[] = {"Aerobics", "Cycling", "Gym Workouts", "Gymnastics", "Hiking", "Pilates", "Racquetball", "Running", "Swimming", "Team Sport", "Walking", "Other"};
        Arrays.fill(question3Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            for(int i = 0; i < 12; i++) {
                if(quest.getQA().get(2).getQuestion_answer().equals(question3Options[i])) {
                    question3Answers[i]++;
                    break;
                }

            }

        }
        for(int i = 0; i < 12; i++) {
            question3.set(question3Options[i], 100*question3Answers[i]/allSurveys.size());
        }

        model3.addSeries(question3);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model3.setExtender("barChartExtender3");

        return model3;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel3() {

        barModel3 = initBarModel3();

        barModel3.setLegendPosition("ne");

        Axis xAxis = barModel3.getAxis(AxisType.X);
        xAxis.setLabel("Question 3: What do you most often do to exercise?");

        Axis yAxis = barModel3.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel4() {

        BarChartModel model4 = new BarChartModel();

        ChartSeries question4 = new ChartSeries();
        question4.setLabel("Question 4");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question4Answers[] = new Integer[8];
        Arrays.fill(question4Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            question4Answers[Integer.parseInt(quest.getQA().get(3).getQuestion_answer())]++;
        }
        for(int i = 0; i < 8; i++) {
            question4.set(i, 100*question4Answers[i]/allSurveys.size());
        }

        model4.addSeries(question4);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model4.setExtender("barChartExtender4");

        return model4;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel4() {

        barModel4 = initBarModel4();

        barModel4.setLegendPosition("ne");

        Axis xAxis = barModel4.getAxis(AxisType.X);
        xAxis.setLabel("Question 4: How many times do you exercise per week?");

        Axis yAxis = barModel4.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel5() {

        BarChartModel model5 = new BarChartModel();

        ChartSeries question5 = new ChartSeries();
        question5.setLabel("Question 5");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question5Answers[] = new Integer[7];
        String question5Options[] = {"Never", "1 time per week", "2-3 times per week", "4-6 times per week", "1 time per day", "2 times per day", "3+ times per day"};
        Arrays.fill(question5Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            for(int i = 0; i < 7; i++) {
                if(quest.getQA().get(4).getQuestion_answer().equals(question5Options[i])) {
                    question5Answers[i]++;
                    break;
                }

            }
        }
        for(int i = 0; i < 7; i++) {
            question5.set(question5Options[i], 100*question5Answers[i]/allSurveys.size());
        }

        model5.addSeries(question5);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model5.setExtender("barChartExtender5");

        return model5;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel5() {

        barModel5 = initBarModel5();

        barModel5.setLegendPosition("ne");

        Axis xAxis = barModel5.getAxis(AxisType.X);
        xAxis.setLabel("Question 5: How often do you drink regular soda or other sugared beverages?");

        Axis yAxis = barModel5.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel6() {

        BarChartModel model6 = new BarChartModel();

        ChartSeries question6 = new ChartSeries();
        question6.setLabel("Question 6");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question6Answers[] = new Integer[10];
        String question6Options[] = {"Never", "1 time per week", "2-3 times per week", "4-6 times per week", "1 time per day", "2 times per day", "3+ times per day"};
        Arrays.fill(question6Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            for(int i = 0; i < 7; i++) {
                if(quest.getQA().get(5).getQuestion_answer().equals(question6Options[i])) {
                    question6Answers[i]++;
                    break;
                }
            }
        }
        for(int i = 0; i < 7; i++) {
            question6.set(question6Options[i], 100*question6Answers[i]/allSurveys.size());
        }

        model6.addSeries(question6);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model6.setExtender("barChartExtender6");

        return model6;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel6() {

        barModel6 = initBarModel6();

        barModel6.setLegendPosition("ne");

        Axis xAxis = barModel6.getAxis(AxisType.X);
        xAxis.setLabel("Question 6: How often do you consume alcohol?");

        Axis yAxis = barModel6.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel7() {

        BarChartModel model7 = new BarChartModel();

        ChartSeries question7 = new ChartSeries();
        question7.setLabel("Question 7");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question7Answers[] = new Integer[10];
        String question7Options[] = {"Chips, crackers, nuts", "Fast foods (e.g. pizza, fries)", "Fruits, vegetables", "Ice cream, chocolate, cookies, candy", "Protein bed, fruit and nut bar, energy bar", "Other"};
        Arrays.fill(question7Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            for(int i = 0; i < 6; i++) {
                if(quest.getQA().get(6).getQuestion_answer().equals(question7Options[i])) {
                    question7Answers[i]++;
                    break;
                }
            }
        }
        for(int i = 0; i < 6; i++) {
            question7.set(question7Options[i], 100*question7Answers[i]/allSurveys.size());
        }

        model7.addSeries(question7);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model7.setExtender("barChartExtender7");

        return model7;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel7() {

        barModel7 = initBarModel7();

        barModel7.setLegendPosition("ne");

        Axis xAxis = barModel7.getAxis(AxisType.X);
        xAxis.setLabel("Question 7: What types of food do you usually snack on?");

        Axis yAxis = barModel7.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel8() {

        BarChartModel model8 = new BarChartModel();

        ChartSeries question8 = new ChartSeries();
        question8.setLabel("Question 8");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question8Answers[] = new Integer[10];
        Arrays.fill(question8Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            question8Answers[Integer.parseInt(quest.getQA().get(7).getQuestion_answer())-1]++;
        }
        for(int i = 0; i < 10; i++) {
            question8.set(i+1, 100*question8Answers[i]/allSurveys.size());
        }

        model8.addSeries(question8);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model8.setExtender("barChartExtender8");

        return model8;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel8() {

        barModel8 = initBarModel8();

        barModel8.setLegendPosition("ne");

        Axis xAxis = barModel8.getAxis(AxisType.X);
        xAxis.setLabel("Question 8: How many hours of sleep do you get each night on average?");

        Axis yAxis = barModel8.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel9() {

        BarChartModel model9 = new BarChartModel();

        ChartSeries question9 = new ChartSeries();
        question9.setLabel("Question 9");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question9Answers[] = new Integer[11];
        Arrays.fill(question9Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            question9Answers[Integer.parseInt(quest.getQA().get(8).getQuestion_answer())]++;
        }
        for(int i = 0; i <= 10; i++) {
            question9.set(i, 100*question9Answers[i]/allSurveys.size());
        }

        model9.addSeries(question9);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model9.setExtender("barChartExtender9");

        return model9;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel9() {

        barModel9 = initBarModel9();

        barModel9.setLegendPosition("ne");

        Axis xAxis = barModel9.getAxis(AxisType.X);
        xAxis.setLabel("Question 9: What is your stress level?");

        Axis yAxis = barModel9.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

    /*
    ********************
    Initialize Bar Model
    ********************
    */
    private BarChartModel initBarModel10() {

        BarChartModel model10 = new BarChartModel();

        ChartSeries question10 = new ChartSeries();
        question10.setLabel("Question 10");

        // Obtain the list of selected country names
        List<UserSurvey> allSurveys = userSurveyController.getAllItems();
        setNumSurveys(allSurveys.size());
        Integer question10Answers[] = new Integer[8];
        String question10Options[] = {"Exercise", "Family relationships", "Hobbies", "Prayer / spiritual activities", "Relaxation techniques", "Social relationships", "Time management", "Other"};
        Arrays.fill(question10Answers, 0);
        // Iteration in Java 8 using the forEach() method with lamda
        for(UserSurvey quest : allSurveys) {
            for(int i = 0; i < 8; i++) {
                if(quest.getQA().get(9).getQuestion_answer().equals(question10Options[i])) {
                    question10Answers[i]++;
                    break;
                }
            }
        }
        for(int i = 0; i < 8; i++) {
            question10.set(question10Options[i], 100*question10Answers[i]/allSurveys.size());
        }

        model10.addSeries(question10);

        // JavaScript function "barChartExtender" in SurveysReport.xhtml is used to style the chart
        model10.setExtender("barChartExtender10");

        return model10;
    }

    /*
    ****************
    Create Bar Chart
    ****************
     */
    private void createBarModel10() {

        barModel10 = initBarModel10();

        barModel10.setLegendPosition("ne");

        Axis xAxis = barModel10.getAxis(AxisType.X);
        xAxis.setLabel("Question 10: Which of the following is most effective in coping with your stress?");

        Axis yAxis = barModel10.getAxis(AxisType.Y);
        yAxis.setLabel("Percent of Responsdents");

        yAxis.setMin(0.000);
        yAxis.setMax(100.000);
    }

}
