/*
 * Created by Osman Balci on 2021.7.19
 * edited by Shrenik Peddibhotla
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.User;
import edu.vt.globals.Methods;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named("btAPIController")
public class BTAPIController implements Serializable {



    /*
        ===============================
        Instance Variables (Properties)
        ===============================
         */
    //-----------------------------------------------------------
    // Change the URL to use your own VTCocktailsAPI-yourLastName
    //-----------------------------------------------------------
    private final String CocktailsAPIbaseURL = "https://www.thecocktaildb.com/api/json/v1/1/";
    private List<Cocktail> listOfVTCocktails;
    private List<String> listOfVTCocktailNames;

    // List of object references of Cocktail objects found in the search
    private List<Cocktail> cocktailsFound;

    private Cocktail selected;

    private String jsonCategoryData;

    private Integer searchType;

    // Search Query Variables (Q = Query)
    private String nameQ;
    private String numberQ;
    private String ingredientQ;

    private Integer id;

    private String name;

    private String number;

    private String glass;

    private String recipe;

    private String photo_url;

    private String ingredients;

    private String apiUrl;

    /*
    =====================
    Initialization Method
    =====================

    The PostConstruct annotation is used on a method that needs to be executed
    after dependency injection is done to perform any initialization.
    This method is invoked before the class is put into service.

    This init() method creates vtCocktails, vtCocktailNames, vtCocktailAbbreviations,
    and vtCocktailCategories by using the VTCocktailsAPI RESTful web services.
     */


    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public List<Cocktail> getListOfVTCocktails() {
        return listOfVTCocktails;
    }

    public void setListOfVTCocktails(List<Cocktail> listOfVTCocktails) {
        this.listOfVTCocktails = listOfVTCocktails;
    }

    //public List<Cocktail> getCocktailsFound() {
     //   return cocktailsFound;
   // }

  //  public void setCocktailsFound(List<Cocktail> cocktailsFound) {
   //     this.cocktailsFound = cocktailsFound;
   // }



    public List<String> getListOfVTCocktailNames() {
        return listOfVTCocktailNames;
    }

    public void setListOfVTCocktailNames(List<String> listOfVTCocktailNames) {
        this.listOfVTCocktailNames = listOfVTCocktailNames;
    }

    public Cocktail getSelected() {
        return selected;
    }

    public void setSelected(Cocktail selected) {
        this.selected = selected;
    }



    public String getJsonCategoryData() {
        return jsonCategoryData;
    }

    public void setJsonCategoryData(String jsonCategoryData) {
        this.jsonCategoryData = jsonCategoryData;
    }


    public String getNameQ() {
        return nameQ;
    }

    public void setNameQ(String nameQ) {
        this.nameQ = nameQ;
    }


    public String getCategoryQ() {
        return numberQ;
    }

    public void setCategoryQ(String numberQ) {
        this.numberQ = numberQ;
    }

    public String getIngredientQ() {
        return ingredientQ;
    }

    public void setIngredientQ(String ingredientQ) {
        this.ingredientQ = ingredientQ;
    }




    /*
    ================
    Instance Methods
    ================
     */

    /*
     *****************************************
     *   Unselect Selected Cocktail Object   *
     *****************************************
     */
    public void unselect() {
        selected = null;
    }


    /*
     ********************************************
     *   Display the SearchResults.xhtml Page   *
     ********************************************
     */
    public String search(Integer type) throws Exception {
        // Set search type given as input parameter
        searchType = type;

        // Unselect previously selected company if any before showing the search results
        selected = null;

        // Invalidate list of VT cocktails found to trigger re-query.
        cocktailsFound = null;





        return "/cocktailApiSearch/SearchResults?faces-redirect=true";
    }



    /*
     ****************************************************************************************************
     *   Return the list of object references of all those cocktails that satisfy the search criteria   *
     ****************************************************************************************************
     */
    public List<Cocktail> getCocktailsFound() throws Exception {
        /*
        =============================================================================================
        You must construct and return the search results List "searchItems" ONLY IF the List is null.
        Any List provided to p:dataTable to display must be returned ONLY IF the List is null
        ===> in order for the column-sort to work. <===
        =============================================================================================
         */
        apiUrl = "";
        switch (searchType) {



            case 1: // Search Type 1: Find VT cocktails whose names contain nameQ
                apiUrl = CocktailsAPIbaseURL + "search.php?s=" + nameQ;

                break;
            case 2: // Search Type 2: Find VT cocktails whose descriptions contain descriptionQ
                apiUrl = CocktailsAPIbaseURL + "filter.php?c=" + numberQ;
                break;
            case 3: // Search Type 3: Find VT cocktails whose abbreviations abbreviationQ
                apiUrl = CocktailsAPIbaseURL + "filter.php?i=" + ingredientQ;
                break;
            default:
                Methods.showMessage("Fatal Error", "Search Type is Out of Range!",
                        "");
        }

        if (cocktailsFound == null) {

            try {


                cocktailsFound = new ArrayList<>();

                String jsonData = Methods.readUrlContent(apiUrl);

                Methods.readUrlContent(apiUrl);

                JSONObject resultsJsonObject = new JSONObject(jsonData);

                // Convert the JSON data into a JSON array
                JSONArray jsonArrayOfCocktails = resultsJsonObject.getJSONArray("drinks");


                switch (searchType) {
                    case 1: // Search Type 1: Find VT cocktails whose names contain nameQ

                        int index = 0;

                        if (jsonArrayOfCocktails.length() > index) {

                            while (jsonArrayOfCocktails.length() > index) {

                                JSONObject cocktailJsonObject = jsonArrayOfCocktails.getJSONObject(index);


                                id = cocktailJsonObject.optInt("idDrink", 0);
                                name = cocktailJsonObject.optString("strDrink", "");
                                number = cocktailJsonObject.optString("strCategory", "");
                                glass = cocktailJsonObject.optString("strGlass", "");
                                recipe = cocktailJsonObject.optString("strInstructions", "");
                                photo_url = cocktailJsonObject.optString("strDrinkThumb", "");
                                ingredients = "";
                                for (int i = 1; i < 16; i++) {
                                    String ingred = cocktailJsonObject.optString("strIngredient" + i, "");
                                    if (!ingred.equals("")) {
                                        ingredients = ingredients + ingred;
                                    }
                                    String measure = cocktailJsonObject.optString("strMeasure" + i, "");
                                    if (!measure.equals("")) {
                                        ingredients = ingredients + " (" + measure + "), ";
                                    }
                                }
                                // Drop the last two characters ", "
                                String lastTwo = ingredients.substring(ingredients.length() - 2);
                                if (lastTwo.equals(", ")) {
                                    ingredients = ingredients.substring(0, ingredients.length() - 2);
                                }

                                // Remove all occurrences of space before right parenthesis
                                ingredients = ingredients.replaceAll(" \\)", ")");

                                // Create a new cocktail object dressed up with the values obtained from the JSON object
                                Cocktail ctail = new Cocktail(id, name, number, glass, recipe, photo_url, ingredients);

                                // Add the newly created cocktail object to the list of VT cocktails found
                                cocktailsFound.add(ctail);
                                index++;
                            }
                            break;
                        }
                        break;

                    case 2:
                    case 3:
                        int index2 = 0;
                        int s = jsonArrayOfCocktails.length();
                        if (jsonArrayOfCocktails.length() > index2) {
                            while (jsonArrayOfCocktails.length() > index2) {
                                JSONObject cocktailJsonObject = jsonArrayOfCocktails.getJSONObject(index2);

                                Integer id = cocktailJsonObject.optInt("idDrink", 0);

                                String newApiUrl = "https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + id;

                                jsonData = Methods.readUrlContent(newApiUrl);

                                resultsJsonObject = new JSONObject(jsonData);

                                JSONArray jsonArrayOfCocktails2 = resultsJsonObject.getJSONArray("drinks");

                                JSONObject cocktailJsonObject2 = jsonArrayOfCocktails2.getJSONObject(0);

                                name = cocktailJsonObject2.optString("strDrink", "");
                                number = cocktailJsonObject2.optString("strCategory", "");
                                glass = cocktailJsonObject2.optString("strGlass", "");
                                recipe = cocktailJsonObject2.optString("strInstructions", "");
                                photo_url = cocktailJsonObject2.optString("strDrinkThumb", "");
                                ingredients = "";
                                for (int i = 1; i < 16; i++) {
                                    String ingred = cocktailJsonObject2.optString("strIngredient" + i, "");
                                    if (!ingred.equals("")) {
                                        ingredients = ingredients + ingred;
                                    }
                                    String measure = cocktailJsonObject2.optString("strMeasure" + i, "");
                                    if (!measure.equals("")) {
                                        ingredients = ingredients + " (" + measure + "), ";
                                    }
                                }
                                // Drop the last two characters ", "
                                String lastTwo = ingredients.substring(ingredients.length() - 2);
                                if (lastTwo.equals(", ")) {
                                    ingredients = ingredients.substring(0, ingredients.length() - 2);
                                }

                                // Remove all occurrences of space before right parenthesis
                                ingredients = ingredients.replaceAll(" \\)", ")");

                                // Create a new cocktail object dressed up with the values obtained from the JSON object
                                Cocktail ctail = new Cocktail(id, name, number, glass, recipe, photo_url, ingredients);

                                // Add the newly created cocktail object to the list of VT cocktails found
                                cocktailsFound.add(ctail);
                                index2++;
                            }
                            break;
                        }
                        break;


                    default:
                        Methods.showMessage("Fatal Error", "Search Type is Out of Range!",
                                "");

                }
            } catch (IOException ex) {
                Methods.showMessage("Fatal Error", "Unrecognized Search Query!",
                        "The Cocktail API provides no data for the search query entered!");
                clear();

            }
        }
        return cocktailsFound;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return number;
    }

    public void setCategory(String number) {
        this.number = number;
    }


    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void clear() {
        name = null;
        number = null;
        glass = null;
        recipe = null;
        photo_url = null;
        ingredients = null;
    }
}
