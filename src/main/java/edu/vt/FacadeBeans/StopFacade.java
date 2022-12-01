/*
 * Created by Osman Balci and Connor Ostrander on 2022.10.9
 * Copyright © 2022 Osman Balci and Connor Ostrander. All rights reserved.
 */

package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Stop;
import edu.vt.globals.Methods;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall not be maintained.
@Stateless
public class StopFacade extends AbstractFacade<Stop>{
    /*
    ---------------------------------------------------------------------------------------------
    The EntityManager is an API that enables database CRUD (Create Read Update Delete) operations
    and complex database searches. An EntityManager instance is created to manage entities
    that are defined by a persistence unit. The @PersistenceContext annotation below associates
    the entityManager instance with the persistence unitName identified below.
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "BTOnlineUtilityPU")
    private EntityManager entityManager;

    // Obtain the object reference of the EntityManager instance in charge of
    // managing the entities in the persistence context identified above.
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /*
    This constructor method invokes its parent AbstractFacade's constructor method,
    which in turn initializes its entity class type T and entityClass instance variable.
     */
    public StopFacade() {
        super(Stop.class);
    }

    private void createBreweriesFromUrl(String url, List<Stop> stops) {
        try {

            url = url.replaceAll(" ", "%20");
            // Query API
            String jsonData = Methods.readUrlContent(url);

            // Parse JSON, create brewery objects, add to a list
            JSONArray jsonArray = new JSONArray(jsonData);

            int idx = 0;

            if (jsonArray.length() > idx) {

                while (jsonArray.length() > idx) {

                    JSONObject jsonObject = jsonArray.getJSONObject(idx);
                    /*
                    ============================
                    Brewery id
                    ============================
                    */
                    String number = jsonObject.optString("stopNumber", "");
                    if (number.equals("")) {
                        // Skip the brewery with no id provided
                        idx++;
                        continue;
                    }

                    /*
                    ============================
                    Brewery name
                    ============================
                    */
                    String name = jsonObject.optString("name", "");
                    if (name.equals("")) {
                        // Skip the brewery with no name provided
                        idx++;
                        continue;
                    }

                    /*
                    ============================
                    Brewery longitude
                    ============================
                    */
                    String longitude = jsonObject.optString("longitude", "");

                    /*
                    ============================
                    Brewery latitude
                    ============================
                    */
                    String latitude = jsonObject.optString("latitude", "");

                    String routes = jsonObject.optString("routes", "");

                    Stop currentStop = new Stop(number, name, routes, longitude, latitude);
                    stops.add(currentStop);
                    idx++;
                }
            }
        }
        catch (Exception e) {
            Methods.showMessage("Fatal Error", "Matching Brewery Fetch Failed!",
                    "Unable to get matching breweries with the Brewery DB API!");
        }
    }

    public List<Stop> nameQuery(String searchString) {
        List<Stop> stops = new ArrayList<>();
        //searchString = searchString.replace(" ", "%20");
        String url = "http://100.26.154.75:8080/BTStopsAPI/api/btStops/nameContains/" + searchString;
        createBreweriesFromUrl(url, stops);
        return stops;
    }

    public List<Stop> numberQuery(String searchString) {
        List<Stop> stops = new ArrayList<>();
        String url = "http://100.26.154.75:8080/BTStopsAPI/api/btStops/stopNumber/" + searchString;
        createBreweriesFromUrl(url, stops);
        return stops;
    }

    public List<Stop> routeQuery(String searchString) {
        List<Stop> stops = new ArrayList<>();
        String url = "http://100.26.154.75:8080/BTStopsAPI/api/btStops/routeContains/" + searchString;
        createBreweriesFromUrl(url, stops);
        return stops;
    }


}
