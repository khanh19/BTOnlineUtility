/*
 * Created by Rob Guldi on 2022.12.1
 * Copyright © 2022 Robert Guldi. All rights reserved.
 */

package edu.vt.EntityBeans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

// The @Entity annotation designates this class as a JPA Entity class representing the UserSurvey table in the BevqDB database.
@Entity

// Name of the database table represented
@Table(name = "BusRoute")

@NamedQueries({
        @NamedQuery(name = "Route.findAll", query = "SELECT u FROM Route u")})


public class Route implements Serializable {

    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the UserSurvey table in the BevqDB database.

    CREATE TABLE BusRoute
    (
       id INT UNSIGNED NOT NULL AUTO_INCREMENT,
       route_name VARCHAR(64) NOT NULL,
       start_time TIME NOT NULL,
       end_time TIME NOT NULL,
       start_trip TIME NOT NULL,
       frequency INT NOT NULL,
       days_running VARCHAR(64) NOT NULL,
       time_check TIME NOT NULL,
       stop_name VARCHAR(1024) NOT NULL,
       bus_size INT NOT NULL,
       latitude DECIMAL(8, 6) NOT NULL,
       longitude DECIMAL(9, 6) NOT NULL,
       PRIMARY KEY (id)
    );
    ========================================================
     */

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "route_name")
    private String routeName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "start_time")
    @Temporal(TemporalType.DATE)
    private Date startTime;

    @Basic(optional = false)
    @NotNull
    @Column(name = "end_time")
    @Temporal(TemporalType.DATE)
    private Date endTime;

    @Basic(optional = false)
    @NotNull
    @Column(name = "frequency")
    private Integer frequency;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "days_running")
    private String daysRunning;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "time_check")
    private String timeCheck;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "stop_name")
    private String stopName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "bus_size")
    private Integer busSize;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "latitudes")
    private String latitudes;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "longitudes")
    private String longitudes;

    public Route() {
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    ======================================================
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getDaysRunning() {
        return daysRunning;
    }

    public void setDaysRunning(String daysRunning) {
        this.daysRunning = daysRunning;
    }

    public String getTimeCheck() {
        return timeCheck;
    }

    public void setTimeCheck(String timeCheck) {
        this.timeCheck = timeCheck;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getBusSize() {
        return busSize;
    }

    public void setBusSize(Integer busSize) {
        this.busSize = busSize;
    }

    public String getLatitudes() {
        return latitudes;
    }

    public void setLatitudes(String latitudes) {
        this.latitudes = latitudes;
    }

    public String getLongitudes() {
        return longitudes;
    }

    public void setLongitudes(String longitudes) {
        this.longitudes = longitudes;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
     Checks if the UserSurvey object identified by 'object' is the
     same as the UserSurvey object identified by 'id'
     Parameter object = UserSurvey object identified by 'object'
     Returns True if the UserSurvey 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    // Return String representation of database primary key id
    @Override
    public String toString() {
        return id.toString();
    }

}
