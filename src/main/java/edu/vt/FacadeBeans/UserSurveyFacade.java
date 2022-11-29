/*
 * Created by Osman Balci on 2021.7.20
 * Copyright © 2021 Osman Balci and Rob Guldi. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.UserSurvey;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class UserSurveyFacade extends AbstractFacade<UserSurvey> {
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
    public UserSurveyFacade() {
        super(UserSurvey.class);
    }

    /*
     *********************
     *   Other Methods   *
     *********************
     */


    /**
     * Find all surveys that belong to a User whose database primary key is dbPrimaryKey
     *
     * @param dbPrimaryKey is the Primary Key of the user entity in the database
     * @return a list of object references of userSurveys that belong to the user whose database Primary Key = dbPrimaryKey
     */
    public List<UserSurvey> findUserSurveysByUserPrimaryKey(Integer dbPrimaryKey) {
        /*
        The following @NamedQuery is defined in UserSurvey.java entity class file:
        @NamedQuery(name = "UserSurvey.findSurveysByUserPrimaryKey", 
            query = "SELECT u FROM UserSurvey u WHERE u.userId.id = :primaryKey")
        
        The following statement obtaines the results from the named database query.
         */
        List<UserSurvey> questList = entityManager.createNamedQuery("UserSurvey.findSurveysByUserPrimaryKey")
                .setParameter("primaryKey", dbPrimaryKey)
                .getResultList();
        if(questList.size() == 0) {
        }
        return questList;
    }

    public List<UserSurvey> getAllSurveys() {
        List<UserSurvey> questList = entityManager.createNamedQuery("UserSurvey.findAll").getResultList();
        return questList;
    }

}
