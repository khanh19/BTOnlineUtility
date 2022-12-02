/*
 * Created by Osman Balci and Connor Ostrander on 2022.12.1
 * Copyright © 2022 Osman Balci and Connor Ostrander. All rights reserved.
 */

package edu.vt.controllers;

import edu.vt.EntityBeans.Route;
import edu.vt.EntityBeans.UserSurvey;
import edu.vt.FacadeBeans.RouteFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Methods;

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

}
