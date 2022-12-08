/*
 * Created by Khanh Pham on 2022.8.30
 * Copyright © 2022 Khanh Pham. All rights reserved.
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

        listOfPhotos.add(new Photo("/resources/images/photos/photo1.jpg", "/resources/images/photos/photo1s.jpg",
                "Description for Photo 1", "Use this app to calculate monthly payment and total interest given interest rate and loan duration"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo2.jpg", "/resources/images/photos/photo2s.jpg",
                "Description for Photo 2", "Understand your credit score before you apply for a car loan"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo3.jpg", "/resources/images/photos/photo3s.jpg",
                "Description for Photo 3", "Keep the loan term as short as you can afford to reduce interest payments"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo4.jpg", "/resources/images/photos/photo4s.jpg",
                "Description for Photo 4", "Put as much money down as you can so that you don't owe more money than the car is worth"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo5.jpg", "/resources/images/photos/photo5s.jpg",
                "Description for Photo 5", "Pay for sales tax, registration fees, document fees, and any extras with cash"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo6.jpg", "/resources/images/photos/photo6s.jpg",
                "Description for Photo 6", "Shop for a loan before you go to the dealership so you can negotiate lowest interest rate"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo7.jpg", "/resources/images/photos/photo7s.jpg",
                "Description for Photo 7", "Work on your timing since interest rates on car loans fluctuate due to economic conditions"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo8.jpg", "/resources/images/photos/photo8s.jpg",
                "Description for Photo 8", "If you are young, get a co-signer with good credit history to reduce interest rate"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo9.jpg", "/resources/images/photos/photo9s.jpg",
                "Description for Photo 9", "Borrow only from reputable banks to avoid outrageous expectations in the fine print"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo10.jpg", "/resources/images/photos/photo10s.jpg",
                "Description for Photo 10", "Learn about pros and cons of leasing versus buying a car and its effect on your car loan"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo11.jpg", "/resources/images/photos/photo11s.jpg",
                "Description for Photo 11", "Learn when it is a bad idea to refinance your auto loan"));
        listOfPhotos.add(new Photo("/resources/images/photos/photo12.jpg", "/resources/images/photos/photo12s.jpg",
                "Description for Photo 12", "Investigate if you should include the cost of car maintenance plan in your loan"));
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
