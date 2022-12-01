/*
 * Created by Rob Guldi on 2022.12.1
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */

package edu.vt.EntityBeans;

import java.io.Serializable;

public class Stop implements Serializable{

    private String number;
    private String name;
    private String latitude;
    private String longitude;
    // Can be empty
    private String routes;


    /*
    =============================================================
    Class constructors for instantiating a Brewery entity object.
    =============================================================
     */
    public Stop() {
    }

    public Stop(String number, String name, String routes, String longitude, String latitude) {
        this.number = number;
        this.name = name;
        this.routes = routes;
        this.longitude = longitude;
        this.latitude = latitude;

    }


    public String getNumber() {
        return number;
    }

    public void setId(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    /*
    ================================
    Instance Methods Used Internally
    ================================
     */

    // Generate and return a hash code value for the object with database primary key id
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the Brewery object identified by 'object' is the same as the Brewery object identified by 'id'
     Parameter object = Brewery object identified by 'object'
     Returns True if the Brewery 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Stop)) {
            return false;
        }
        Stop other = (Stop) object;
        return (this.number != null || other.number == null) && (this.number == null || this.number.equals(other.number));
    }


}
