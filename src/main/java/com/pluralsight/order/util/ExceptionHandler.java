package com.pluralsight.order.util;

import java.sql.SQLException;

/**
 * Utility class to handle exceptions
 */
public class ExceptionHandler {

    /**
     * Method to extract and print information from a SQLException
     * @param sqlException Exception from which information will be extracted
     */
    public static void handleException(SQLException sqlException) {
        System.out.println("Error code: " + sqlException.getErrorCode());
        System.out.println("Sql State: " + sqlException.getSQLState());
        System.out.println("Error message: " + sqlException.getMessage());
        sqlException.printStackTrace();
    }
}
