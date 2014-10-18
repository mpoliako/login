/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.login.dao;

import com.bionic.login.manager.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Artem
 */
public class DAOUser {

    public static boolean find(String login, String password) {

        try {
            Class.forName(Config.getInstance().getProperty(Config.DRIVER));
            Connection cn = null;
            try {
                cn = DriverManager.getConnection(Config.getInstance().getProperty(Config.URL));
                PreparedStatement st = null;
                try {
                    st = cn.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
                    st.setString(1, login);
                    st.setString(2, password);
                    ResultSet rs = null;
                    try {
                        rs = st.executeQuery();
                        return rs.next();
                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                } finally {
                    if (st != null) {
                        st.close();
                    }
                }
            } finally {
                if (cn != null) {
                    cn.close();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
