package mabuntu.ecole.ecole2024;

import android.content.Context;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import mabuntu.ecole.ecole2024.tools.Tools;


public class DBConnexion {

    private static Connection con;
    public static Connection create(Context context) {
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        String jdbcUrl = "jdbc:sqldroid:" + "/data/data/" + context.getPackageName() + "/my-database.db";
        try {
            return con = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                Tools.Exp(e);
            }
        }
    }
}
