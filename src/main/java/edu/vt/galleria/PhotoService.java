/*
 * Created by Rob Guldi on 2022.10.14
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */

package edu.vt.galleria;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "photoService")
@ApplicationScoped
public class PhotoService {
    /*
    ============================
    Instance Variable (Property)
    ============================
     */
    private List<Photo> listOfPhotos;

    /*
    The PostConstruct annotation is used on a method that needs to be executed after
    dependency injection is done to perform any initialization. The initialization
    method init() is the first method invoked before this class is put into service.
     */
    @PostConstruct
    public void init() {
        listOfPhotos = new ArrayList<>();
        String[] titles = {"Wellness awaits you! Treating the mind, body, soul.", "Health, Wealth, and Happiness.", "Healthy Body, Mind, and Spirit.",  "Wellness is the act of practicing healthy habits on a daily basis to attain better physical and mental health outcomes.", "Exercise is king. Nutrition is queen. Put them together and you’ve got a kingdom. [Jack LaLanne]", "Healthy Living Secret: Turn off the screens and sleep!", "The number one secret to living long and healthier is to eat the right kind of food.", "Health is a state of body. Wellness is a state of being. [J. Stanford]", "Benefits of yoga include stress management, mental/emotional health, promoting healthy eating/activity habits, sleep, and balance.", "Living a healthy lifestyle can help prevent chronic diseases and long-term illnesses.", "Good health, peace of mind, and healthy relationships are the real wealth of life.", "Your health is your most priceless possession. Being Healthy allows you to enjoy all other Blessings!"};
        for(int i = 1; i <= titles.length; i++) {
            listOfPhotos.add(new Photo("/resources/images/photos/photo" + i + ".jpg", "/resources/images/photos/photo" + i + "s.jpg",
                    "Description for Photo 1", titles[i-1]));
        }
    }

    /*
    =============
    Getter Method
    =============
     */
    public List<Photo> getListOfPhotos() {
        return listOfPhotos;
    }
}
