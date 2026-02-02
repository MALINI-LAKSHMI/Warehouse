package com.warehouse.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.warehouse.bean.Shipment;
import com.warehouse.util.DBUtil;

public class ShipmentDAO {

    // 1️⃣ Find shipment by ID
    public Shipment findShipment(String shipmentID) {
        Shipment shipment = null;

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "SELECT * FROM SHIPMENT_TBL WHERE shipment_id = ?")) {

            ps.setString(1, shipmentID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                shipment = new Shipment();
                shipment.setShipmentID(rs.getString("shipment_id"));
                shipment.setItemDescription(rs.getString("item_description"));
                shipment.setTotalQuantity(rs.getInt("total_quantity"));
                shipment.setAvailableQuantity(rs.getInt("available_quantity"));
                shipment.setReceivedDate(rs.getDate("received_date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return shipment;
    }

    // 2️⃣ View all shipments
    public List<Shipment> viewAllShipments() {
        List<Shipment> list = new ArrayList<>();

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement("SELECT * FROM SHIPMENT_TBL");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Shipment s = new Shipment();
                s.setShipmentID(rs.getString("shipment_id"));
                s.setItemDescription(rs.getString("item_description"));
                s.setTotalQuantity(rs.getInt("total_quantity"));
                s.setAvailableQuantity(rs.getInt("available_quantity"));
                s.setReceivedDate(rs.getDate("received_date"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️⃣ Insert new shipment
    public boolean insertShipment(Shipment s) {
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "INSERT INTO SHIPMENT_TBL VALUES (?,?,?,?,?)")) {

            ps.setString(1, s.getShipmentID());
            ps.setString(2, s.getItemDescription());
            ps.setInt(3, s.getTotalQuantity());
            ps.setInt(4, s.getAvailableQuantity());
            ps.setDate(5, new java.sql.Date(s.getReceivedDate().getTime()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4️⃣ Update available quantity
    public boolean updateAvailableQuantity(String shipmentID, int newQty) {
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "UPDATE SHIPMENT_TBL SET available_quantity=? WHERE shipment_id=?")) {

            ps.setInt(1, newQty);
            ps.setString(2, shipmentID);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5️⃣ Delete shipment
    public boolean deleteShipment(String shipmentID) {
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "DELETE FROM SHIPMENT_TBL WHERE shipment_id=?")) {

            ps.setString(1, shipmentID);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
