/*
 * Created by Rob Guldi on 2022.10.14
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */
package edu.vt.globals;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Constants {
    /*
    ==================================================
    |   Use of Class Variables as Global Constants   |
    ==================================================
    All of the variables in this class are Class Variables (typed with the "static" keyword)
    with Constant values, which can be accessed in any class in the project by specifying
    Constants.CONSTANTNAME, i.e., ClassName.ClassVariableNameInCaps

    Constants are specified in capital letters.
    */

    /*
    ***********************************************************************************
    You are required to register at https://developers.thevideodb.org/3/getting-started
    and obtain your own API key. TMDb limits 4 accesses per second. Therefore, every
    student must use his/her own API key so that the limit is not exceeded.
    ***********************************************************************************
     */
    public static final String[] STATE_NAMES = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
            "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
            "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    public static final String[] COCKTAIL_INGREDIENTS = {"Absolut Citron", "Ale", "Amaretto", "Angelica root",
            "Apple brandy", "Apple cider", "Apple juice", "Applejack","Apricot brandy", "Berries", "Bitters",
            "Blackberry brandy", "Blended whiskey", "Bourbon", "Brandy", "Cantaloupe", "Carbonated water",
            "Champagne", "Cherry brandy", "Chocolate", "Chocolate liqueur", "Chocolate syrup", "Cider",
            "Cocoa powder", "Coffee", "Coffee brandy", "Coffee liqueur", "Cognac", "Cranberries",
            "Cranberry juice", "Creme de Cacao", "Creme de Cassis", "Dark rum", "demerara Sugar",
            "Dry Vermouth", "Dubonnet Rouge", "Egg", "Egg yolk", "Espresso", "Everclear", "Firewater",
            "Galliano", "Gin", "Ginger", "Grape juice", "Grapefruit juice", "Grapes", "Grenadine",
            "Heavy cream", "Irish cream", "Irish whiskey", "Johnnie Walker", "Kahlua", "Kiwi", "Lager",
            "Lemon", "Lemon juice", "Lemon vodka", "Lemonade", "Light rum", "Lime", "Lime juice", "Mango",
            "Midori melon liqueur", "Milk", "Orange", "Orange bitters", "Ouzo", "Peach nectar", "Peach Vodka",
            "Peppermint schnapps",  "Pineapple juice", "Pisco", "Port", "Red wine", "Ricard", "Rum", "Sambuca",
            "Scotch", "Sherry", "Sloe gin", "Southern Comfort", "Spiced rum", "Sprite", "Strawberries",
            "Strawberry schnapps", "Sugar", "Sugar syrup", "Sweet Vermouth", "Tea", "Tequila", "Tomato juice",
            "Triple sec", "Vodka", "Water", "Watermelon", "Whiskey", "Yoghurt"};

    public static final String[] SECURITY_QUESTIONS = {
            "In what city or town did your mother and father meet?",
            "In what city or town were you born?",
            "What did you want to be when you grew up?",
            "What do you remember most from your childhood?",
            "What is the name of the boy or girl that you first kissed?",
            "What is the name of the first school you attended?",
            "What is the name of your favorite childhood friend?",
            "What is the name of your first pet?",
            "What is your mother's maiden name?",
            "What was your favorite place to visit as a child?"
    };

    public static final String[] STATES = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT",
            "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA",
            "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
            "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT",
            "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    public static final Integer THUMBNAIL_SIZE = 200;

    public static final String FILES_ABSOLUTE_PATH  = "/opt/wildfly/DocRoot/CloudStorage/FileStorage/";
    public static final String PHOTOS_ABSOLUTE_PATH = "/Users/rober/DocRoot/SurveyUserPhotoStorage/";


    public static final String FILES_URI  = "http://100.26.154.75:8080/files/";
    public static final String PHOTOS_URI = "http://localhost:8080/userphotos/";


    public static final Map<String, String> INGREDIENTS;
    static{
        INGREDIENTS = new LinkedHashMap<String, String>();
        for(int i = 0; i < Constants.COCKTAIL_INGREDIENTS.length; i++) {
            INGREDIENTS.put(Constants.COCKTAIL_INGREDIENTS[i], Constants.COCKTAIL_INGREDIENTS[i]);
        }
    }


}
