/*
 * Created by Shrenik Peddibhotla on 2022.10.9
 * Copyright © 2022 Shrenik Peddibhotla. All rights reserved.
 */

package edu.vt.controllers;


import edu.vt.EntityBeans.Stop;
import edu.vt.FacadeBeans.StopFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@Named("btAPIController")
@SessionScoped
public class BTApiController implements Serializable {

    // searchItems = List of object references of Stop objects found in the API DB search
    private List<Stop> searchItems = null;
    private Stop selected;

    // Search type from 1 to 4
    private Integer searchType;
    private String nameQ;
    private String numberQ;

    private String routeQ;

    public ArrayList<Stop> getFavoriteStops() {
        return favoriteStops;
    }

    public void setFavoriteStops(ArrayList<Stop> favoriteStops) {
        this.favoriteStops = favoriteStops;
    }

    // favoriteStops - list of a user's favorite Stops
    private ArrayList<Stop> favoriteStops = new ArrayList<Stop>() {
    };


    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    StopFacade bean into the instance variable 'stopFacade' after it is instantiated at runtime.
     */
    @EJB
    private StopFacade stopFacade;

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public Stop getSelected() {
        return selected;
    }

    public void setSelected(Stop selected) {
        this.selected = selected;
    }

    public String[] getStopNames() {return Constants.BUS_STOPS;}

    public String[] getRouteNames() {return Constants.ROUTES;}


    public String getNameQ() {
        return nameQ;
    }

    public void setNameQ(String nameQ) {
        this.nameQ = nameQ;
    }

    public String getRouteQ() {
        return routeQ;
    }

    public void setRouteQ(String routeQ) {
        this.routeQ = routeQ;
    }

    public String getNumberQ() {
        return numberQ;
    }

    public void setNumberQ(String numberQ) {
        this.numberQ = numberQ;
    }

    public void unselect() {
        selected = null;
    }

    public List<Stop> getStopSearchItems() {
        if (searchItems == null) {
            switch (searchType) {
                case 1: // Search Type 1
                    // Search Stops API by Stop Number
                    searchItems = stopFacade.numberQuery(numberQ);
                    break;
                case 2: // Search Type 2
                    // Search Stops API by Stop Name
                    searchItems = stopFacade.nameQuery(nameQ);
                    break;
                case 3: // Search Type 3
                    // Search Stops API by Stop Name via dropdown
                    searchItems = stopFacade.nameQuery(nameQ);
                    break;
                case 4: // Search Type 3
                    // Search Stops API by Routes

                    String[] route_bad = routeQ.split("-");

                    routeQ = route_bad[0];

                    routeQ = routeQ.trim();

                    searchItems = stopFacade.routeQuery(routeQ);
                    break;
                default:
                    Methods.showMessage("Fatal Error", "Search Type is Out of Range!",
                            "");
            }
        }
        return searchItems;
    }

    public void addToFavorites(Stop stop)
    {
        favoriteStops.add(stop);
    }

    /*
    ================
    Instance Methods
    ================
    */

    /*
     ******************************************
     *   Display the Search Results JSF Page  *
     ******************************************
     */
    public String search(Integer type) {
        // Set search type given as input parameter
        searchType = type;

        selected = null;

        // Invalidate list of search items to trigger re-query.
        searchItems = null;

        return "/BTApiSearch/SearchResults?faces-redirect=true";
    }
}
