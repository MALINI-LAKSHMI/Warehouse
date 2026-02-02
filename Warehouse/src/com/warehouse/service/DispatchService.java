package com.warehouse.service;

import java.util.ArrayList;
import java.util.List;

import com.warehouse.bean.Shipment;

public class DispatchService {

    private List<Shipment> shipments = new ArrayList<>();

    // CASE 1
    public boolean addNewShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    // CASE 2
    public Shipment viewShipmentDetails(String id) {
        for (Shipment s : shipments) {
            if (s.getShipmentID().equals(id)) {
                return s;
            }
        }
        return null;
    }

    // CASE 3
    public List<Shipment> viewAllShipments() {
        return shipments;
    }

    // CASE 4
    public boolean dispatchShipment(String id, String dest, int qty) {
        for (Shipment s : shipments) {
            if (s.getShipmentID().equals(id)
                    && s.getAvailableQuantity() >= qty) {
                s.setAvailableQuantity(
                        s.getAvailableQuantity() - qty);
                return true;
            }
        }
        return false;
    }

    // CASE 5
    public boolean cancelDispatch(int id) {
        return true;
    }

    // CASE 6
    public boolean removeShipment(String id) {
        return shipments.removeIf(
                s -> s.getShipmentID().equals(id));
    }
}