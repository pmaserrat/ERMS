package com.team19.controller.model;

/**
 * Created by Booker on 11/24/16.
 */
public class RowResourceReport {

    private Integer rowNumber;
    private String primaryEmergencySupportFunction;
    private Integer  totalResources;
    private Integer  resourcesInUse ;

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getPrimaryEmergencySupportFunction() {
        return primaryEmergencySupportFunction;
    }

    public void setPrimaryEmergencySupportFunction(String primaryEmergencySupportFunction) {
        this.primaryEmergencySupportFunction = primaryEmergencySupportFunction;
    }

    public Integer getTotalResources() {
        return totalResources;
    }

    public void setTotalResources(Integer totalResources) {
        this.totalResources = totalResources;
    }

    public Integer getResourcesInUse() {
        return resourcesInUse;
    }

    public void setResourcesInUse(Integer resourcesInUse) {
        this.resourcesInUse = resourcesInUse;
    }

    @Override
    public String toString() {
        return "RowResourceReport{" +
                "rowNumber=" + rowNumber +
                ", primaryEmergencySupportFunction='" + primaryEmergencySupportFunction + '\'' +
                ", totalResources=" + totalResources +
                ", resourcesInUse=" + resourcesInUse +
                '}';
    }
}
