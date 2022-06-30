package com.zoomcar.jdbi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * since ${project.version}
 *
 * @author AbhishekT on 16/05/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarGroup extends BaseDTO implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private String displayName;
    private int status = 1;
    @Min(30)
    private int waitPeriod;
    @Min(1)
    private int carType;
    private int dealCarType;

    public CarGroup(int id, String createdBy, String modifiedBy, Date createdAt, Date modifiedAt, String name, String displayName, Integer status, int wait_period, int car_type, int deal_car_type) {
        super(id, createdBy, modifiedBy, createdAt, modifiedAt);
        this.name = name;
        this.displayName = displayName;
        this.status = status;
        this.waitPeriod = wait_period;
        this.carType = car_type;
        this.dealCarType = deal_car_type;
    }

    public CarGroup() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getWaitPeriod() {
        return waitPeriod;
    }

    public void setWaitPeriod(int waitPeriod) {
        this.waitPeriod = waitPeriod;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public int getDealCarType() {
        return dealCarType;
    }

    public void setDealCarType(int dealCarType) {
        this.dealCarType = dealCarType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarGroup carGroup = (CarGroup) o;
        return getWaitPeriod() == carGroup.getWaitPeriod() &&
                getCarType() == carGroup.getCarType() &&
                getDealCarType() == carGroup.getDealCarType() &&
                Objects.equals(getName(), carGroup.getName()) &&
                Objects.equals(getDisplayName(), carGroup.getDisplayName()) &&
                Objects.equals(getStatus(), carGroup.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getDisplayName(), getStatus(), getWaitPeriod(), getCarType(), getDealCarType());
    }

    @Override
    public String toString() {
        return "CarGroup{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", status=" + status +
                ", waitPeriod=" + waitPeriod +
                ", carType=" + carType +
                ", dealCarType=" + dealCarType +
                "} " + super.toString();
    }
}
