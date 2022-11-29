/*
 * Created by Osman Balci on 2021.7.20
 * Copyright © 2021 Osman Balci and Rob Guldi. All rights reserved.
 */
package edu.vt.pojo;

// This class provides a Plain Old Java Object (POJO) representing a BEVQ category
public class Category {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    Integer question_number;
    String question_title;
    
    String question_answer;

    /*
    ==================
    Constructor Method
    ==================
     */
    public Category(Integer question_number, String question_title, String question_answer) {
        this.question_number = question_number;
        this.question_title = question_title;
        this.question_answer = question_answer;
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public Integer getQuestion_number() {
        return question_number;
    }

    public void setQuestion_number(Integer question_number) {
        this.question_number = question_number;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }
}
