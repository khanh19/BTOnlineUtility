/*
 * Created by Connor Ostrander on 2022.10.9
 * Copyright © 2022 Connor Ostrander. All rights reserved.
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

@Named("btAPIController")
@SessionScoped
public class BTApiController implements Serializable {

    // searchItems = List of object references of Brewery objects found in the API DB search
    private List<Stop> searchItems = null;
    private Stop selected;

    // Search type from 1 to 4
    private Integer searchType;
    private String nameQ;
    private String numberQ;


    /*
    The @EJB annotation directs the EJB Container Manager to inject (store) the object reference of the
    BreweryFacade bean into the instance variable 'breweryFacade' after it is instantiated at runtime.
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


    public String getNameQ() {
        return nameQ;
    }

    public void setNameQ(String nameQ) {
        this.nameQ = nameQ;
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
                    // Search Open Brewery DB API by Brewery Name
                    searchItems = stopFacade.numberQuery(numberQ);
                    break;
                case 2: // Search Type 2
                    // Search Open Brewery DB API by City Name
                    searchItems = stopFacade.nameQuery(nameQ);
                    break;
                case 3: // Search Type 3
                    // Search Open Brewery DB API by State Name
                    searchItems = stopFacade.nameQuery(nameQ);
                    break;
                default:
                    Methods.showMessage("Fatal Error", "Search Type is Out of Range!",
                            "");
            }
        }
        return searchItems;
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
