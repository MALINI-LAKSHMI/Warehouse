package com.warehouse.bean;

import java.util.Date;

public class Shipment {

    private String shipmentID;
    private String itemDescription;
    private int totalQuantity;
    private int availableQuantity;
    private Date receivedDate;

    public String getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    @Override
    public String toString() {
        return "Shipment [ID=" + shipmentID +
               ", Item=" + itemDescription +
               ", Total=" + totalQuantity +
               ", Available=" + availableQuantity +
               ", Date=" + receivedDate + "]";
    }
}