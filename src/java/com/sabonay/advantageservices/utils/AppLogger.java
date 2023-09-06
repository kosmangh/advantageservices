package com.sabonay.advantageservices.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
public class AppLogger {

    public static void printPayload(Logger logger, String msg, Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        logger.info(msg + "::\n" + gson.toJson(object));
    }

    public static void printPayloadCompact(Logger logger, String msg, Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        logger.info(msg + "::\n" + gson.toJson(object));
    }

    public static void info(Logger logger, String message) {
        logger.info(":: " + message);
    }

    // Error Level
    public static void warn(Logger log, String message) {
        log.warn(":\n" + message);
    }

    public static void error(org.apache.log4j.Logger log, Exception e, String message) {
        log.error(message + ":\nerror reason - " + e.getMessage());
        log.error(message + ":\nerror exception - " + Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
    }

    public static void sqlException(Logger log, SQLException ex, String message) {
        log.info("Exception. Reason: " + ex.getMessage() + ". " + ex.getSQLState() + ". " + ex.getErrorCode());
        log.info(message + "Reason: " + ex.getMessage());
        log.info(Arrays.toString(ex.getStackTrace()).replace(", ", "\n"));
    }

    public static void printDBResponse(Logger logger, String msg, String rspCode, String rspMsg) {
        logger.info(msg + "DB response : DBCode: " + rspCode + ", DBMessage: " + rspMsg);
    }

    public static void printDBResponse(Logger logger, String rspCode, String rspMsg) {
        AppLogger.info(logger, "DB response code: " + rspCode + "\nresponse msg: " + rspMsg);
    }
}
