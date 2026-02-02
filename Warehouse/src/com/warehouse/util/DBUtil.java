package com.warehouse.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getDBConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe",
            "warehouse",
            "warehouse123"
        );
        con.setAutoCommit(false); // IMPORTANT for transactions
        return con;
    }
}
