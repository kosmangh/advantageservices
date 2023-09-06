/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.advantageservices.services.dao;

import com.sabonay.advantageservices.utils.AppLogger;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Wale Akanni
 */
public class DataConnection {

    private static final Logger log = Logger.getLogger(DataConnection.class.getName());

    public static Connection getDatabaseConnection() {
        Context initCtx = null;
        DataSource ds = null;
        Connection conn = null;
        try {
            initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("jdbc/merchantwebservices");
            conn = ds.getConnection();
            return conn;
        } catch (SQLException | NamingException e) {
            AppLogger.error(log, e, "Error SQLException connecting to jdbc/merchantwebservices return null");
            return null;
        }
    }

}
