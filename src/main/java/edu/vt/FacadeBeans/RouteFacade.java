/*
 * Created by Osman Balci and Connor Ostrander on 2022.12.1
 * Copyright © 2022 Osman Balci and Connor Ostrander. All rights reserved.
 */

package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Route;
import edu.vt.EntityBeans.User;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class RouteFacade extends AbstractFacade<Route>{
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
    public RouteFacade() {
        super(Route.class);
    }

    /*
     ************************
     *   Instance Methods   *
     ************************
     */

    // Returns the object reference of the Route object whose primary key is id
    public Route getRoute(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return entityManager.find(Route.class, id);
    }

    // Deletes the Route entity object whose primary key is id
    public void deleteRoute(int id) {

        // The find method is inherited from the parent AbstractFacade class
        Route route = entityManager.find(Route.class, id);

        // The remove method is inherited from the parent AbstractFacade class
        entityManager.remove(route);
    }

    public List<Route> findAllRoutes() {
        return (List<Route>) (entityManager.createNamedQuery("Route.findAll").getResultList());
    }

    public Route findByRouteName(String routeName) {
        if (entityManager.createQuery("SELECT r FROM Route r WHERE r.routeName = :routeName")
                .setParameter("routeName", routeName)
                .getResultList().isEmpty()) {
            return null;
        } else {
            return (Route) (entityManager.createQuery("SELECT r FROM Route r WHERE r.routeName = :routeName")
                    .setParameter("routeName", routeName)
                    .getSingleResult());
        }
    }


}
