/*
 * Created by Osman Balci and Connor Ostrander on 2022.12.1
 * Copyright © 2022 Osman Balci and Connor Ostrander. All rights reserved.
 */

package edu.vt.controllers;

import edu.vt.EntityBeans.Route;
import edu.vt.EntityBeans.UserSurvey;
import edu.vt.FacadeBeans.RouteFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("routeController")
@SessionScoped
public class RouteController implements Serializable {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private String routeName;

    private Route selected;

    private List<Route> listOfRoutes = null;

    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    UserFacade bean into the instance variable 'userFacade' after it is instantiated at runtime.
     */
    @EJB
    private RouteFacade routeFacade;

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public String getRouteName() {
        selected = routeFacade.findByRouteName(routeName);
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Route getSelected() {
        return selected;
    }

    public void setSelected(Route selected) {
        this.selected = selected;
    }

    public List<String> getListOfRouteNames() {
        List<String> names = new ArrayList<String>();
        List<Route> routes = routeFacade.findAllRoutes();
        for (int i = 0; i < routes.size(); i++) {
            names.add(routes.get(i).getRouteName());
        }
        return names;
    }

    public List<Route> getListOfRoutes() {
        if (listOfRoutes == null) {
            listOfRoutes = routeFacade.findAllRoutes();
        }
        return listOfRoutes;
    }

    /*
    ================
    Instance Methods
    ================
     */

    /*
     *************************************
     *   Cancel and Display List.xhtml   *
     *************************************
     */
    public String cancel() {
        // Unselect previously selected route object if any
        selected = null;
        return "/routes/List?faces-redirect=true";
    }

    /*
    ***************************************
    DELETE Selected Route from the Database
    ***************************************
     */
    public void destroy() {
        Methods.preserveMessages();

        persist(JsfUtil.PersistAction.DELETE,"Route was Successfully Deleted!");

        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null;           // Remove selection
            listOfRoutes = null;    // Invalidate listOfRoutes to trigger re-query.
        }
    }

    public void unselect() {
        selected = null;
    }

    public void prepareCreate() {
        /*
        Instantiate a new Route object and store its object reference into instance
        variable 'selected'. The Route class is defined in Route.java
         */
        selected = new Route();
    }

    /*
    ******************************************
    Create a New Route in the Database
    ******************************************
     */
    public void create() {
        Methods.preserveMessages();

        persist(JsfUtil.PersistAction.CREATE,"Thank You! Your Route was Successfully Saved in the Database!");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The CREATE operation is successfully performed.
            selected = null; // Remove selection
            listOfRoutes = null;
        }
    }

    public void update() {
        persist(JsfUtil.PersistAction.UPDATE,"Route was Successfully Updated!");
    }

    /*
    ***************************************************
    Delete the Selected Route from the Database
    ***************************************************
     */
    public void deleteSurvey() {
        Methods.preserveMessages();
        /*
        Show the message "The Route was Successfully Deleted from the Database!"
        given in the Bundle.properties file under the UserSurveyDeleted keyword.

        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(JsfUtil.PersistAction.DELETE,"The Survey was Successfully Deleted from the Database!\n");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null; // Remove selection
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
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     UserSurveyFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    routeFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.

                     UserSurveyFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    routeFacade.remove(selected);
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

    public String drawRoute() {
        ArrayList<String> points = getRoutePoints(routeName);

        if (points == null || points.isEmpty()) {
            return "https://www.google.com/maps/embed/v1/directions?key=AIzaSyCAcfIGYa9vzj7ABIemB8UGLgr187PI4jQ&origin=Current+Location&destination=37.229000,-80.423714";
        }

        String origin = points.remove(0) + "," + points.remove(0);
        String destination = points.remove(points.size()-2) + "," + points.remove(points.size()-1);

        String waypoints = "";

        for (int i = 0; i < points.size(); i+=2) {

            if (i >= 40) {break;} // Google Maps Embed API only supports 20 waypoints

            String lat = points.get(i);
            String lng = points.get(i+1);

            String placeIdUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&key=AIzaSyCAcfIGYa9vzj7ABIemB8UGLgr187PI4jQ";

            try {
                String jsonData = Methods.readUrlContent(placeIdUrl);

                JSONObject wholeJsonObject = new JSONObject(jsonData);

                String resultsData = wholeJsonObject.optString("results", "");

                JSONArray jsonArray = new JSONArray(resultsData);

                JSONObject jsonObject = jsonArray.getJSONObject(0);

                String placeId = jsonObject.optString("place_id","");

                if (i+2 >= points.size()) {
                    waypoints += "place_id:" + placeId;
                }
                else {
                    waypoints += "place_id:" + placeId + "|";
                }

            }
            catch (Exception e) {
                Methods.showMessage("Fatal Error", "Fetch Failed!",
                        "Unable to get route with BT API");
            }

        }

        String url = "https://www.google.com/maps/embed/v1/directions?key=AIzaSyCAcfIGYa9vzj7ABIemB8UGLgr187PI4jQ&origin="
                + origin + "&destination=" + destination + "&waypoints=" + waypoints;

        return url;
    }

    public ArrayList<String> getRoutePoints(String name) {
        try {
            String queryName = getQueryName(name);

            if (queryName == null) {return null;}

            String url = "https://ridebt.org/index.php?option=com_ajax&module=bt_map&format=json&Itemid=101&method=getPatternPoints&patternName=";
            url += queryName.replaceAll(" ", "%20");

            // Query API
            String jsonData = Methods.readUrlContent(url);

            JSONObject wholeJsonObject = new JSONObject(jsonData);

            String routeData = wholeJsonObject.optString("data", "");

            if (routeData.equals("")) {return null;}

            // Parse JSON, add to a list
            JSONArray jsonArray = new JSONArray(routeData);

            int idx = 0;
            ArrayList<String> latLng = new ArrayList<String>();

            while (jsonArray.length() > idx) {

                JSONObject jsonObject = jsonArray.getJSONObject(idx);

                String isBusStop = jsonObject.optString("isBusStop", "");

                if (isBusStop.equals("Y")) {
                    String lat = jsonObject.optString("latitude","");
                    String lng = jsonObject.optString("longitude","");

                    latLng.add(lat);
                    latLng.add(lng);
                }
                idx++;
            }

            return latLng;
        }
        catch (Exception e) {
            Methods.showMessage("Fatal Error", "Fetch Failed!",
                    "Unable to get route with BT API");
        }

        return null;
    }

    public String getQueryName(String name) {
        try {

            String jsonData = Methods.readUrlContent(Constants.ROUTES_URL);

            JSONObject wholeJsonObject = new JSONObject(jsonData);

            String routeData = wholeJsonObject.optString("data", "");

            if (routeData.equals("")) {return null;}

            JSONArray jsonArray = new JSONArray(routeData);

            String[] route = name.split("-");

            if (route.length <= 1) {return null;} // If route name isn't formatted as id-full name (e.g. TOM-Toms Creek)

            int idx = 0;
            String match = null;
            String roughMatch = null;

            while (jsonArray.length() > idx) {

                JSONObject jsonObject = jsonArray.getJSONObject(idx);

                String id = jsonObject.optString("routeId","");

                if (id.equals(route[0])) {
                    String currName = jsonObject.optString("name","");
                    if (!currName.equals("")) {
                        if (currName.split("-").length == 1) {
                            match = currName;
                            break;
                        }
                        else {
                            if (roughMatch == null) {
                                roughMatch = currName;
                            }
                        }
                    }
                }
                idx++;
            }

            if (match != null) {return match;}
            return roughMatch;
        }
        catch (Exception e) {
            Methods.showMessage("Fatal Error", "Fetch Failed!",
                    "Unable to get route with BT API!");
        }
        return null;
    }

}
