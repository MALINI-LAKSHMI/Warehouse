package com.warehouse.dao;

import java.sql.*;

import com.warehouse.bean.Dispatch;
import com.warehouse.util.DBUtil;

public class DispatchDAO {

    // 1️⃣ Generate dispatch ID
    public int generateDispatchID() {
        int id = 0;

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs =
                     st.executeQuery("SELECT NVL(MAX(dispatch_id),80000)+1 FROM DISPATCH_TBL")) {

            if (rs.next())
                id = rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // 2️⃣ Record dispatch
    public boolean recordDispatch(Dispatch d) {
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "INSERT INTO DISPATCH_TBL VALUES (?,?,?,?,?,?)")) {

            ps.setInt(1, d.getDispatchID());
            ps.setString(2, d.getShipmentID());
            ps.setString(3, d.getDestination());
            ps.setInt(4, d.getQuantityDispatched());
            ps.setDate(5, new java.sql.Date(d.getDispatchDate().getTime()));
            ps.setString(6, d.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3️⃣ Cancel dispatch
    public boolean removeDispatch(int dispatchID) {
        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps =
                     con.prepareStatement(
                       "UPDATE DISPATCH_TBL SET status='CANCELLED' WHERE dispatch_id=?")) {

            ps.setInt(1, dispatchID);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
