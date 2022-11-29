/*
 * Created by Rob Guldi on 2022.11.11
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */

package edu.vt.controllers;

import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.UserFacade;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("allUserController")
@SessionScoped
public class AllUserController implements Serializable {
    @EJB
    private UserFacade userFacade;

    private List<User> allUsers = null;

    private User selected;

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public AllUserController() {

    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public List<User> getAllUsers() {
        if(allUsers == null) {
            allUsers = userFacade.findAllUsers();
        }
        return allUsers;
    }
}
