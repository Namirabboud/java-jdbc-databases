package com.pluralsight.order.dao;

import com.pluralsight.order.dto.ParamsDto;
import com.pluralsight.order.util.Database;
import com.pluralsight.order.util.ExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO to update an order
 */
public class UpdateOrderDao {
    private String query = "UPDATE orders o SET o.order_status = ? WHERE o.order_id = ?";
    private Database database;

    /**
     * Constructor
     * @param database Database object
     */
    public UpdateOrderDao(Database database) {
        this.database = database;
    }

    /**
     * Updates the status of an order
     * @param paramsDto Object with the parameters for the operation
     * @return Number of affected rows
     */
    public int updateOrderStatus(ParamsDto paramsDto) {
        int numberResults = 0;

        try (Connection con = this.database.getConnection();
             PreparedStatement ps = createPreparedStatement(con, paramsDto)
        ) {
            numberResults = executeUpdate(ps);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex);
        }

        return numberResults;
    }

    /**
     * Creates a PreparedStatement object to update the order
     * @param con Connnection object
     * @param paramsDto Object with the parameters to set on the PreparedStatement
     * @return A PreparedStatement object
     * @throws SQLException In case of an error
     */
    private PreparedStatement createPreparedStatement(Connection con, ParamsDto paramsDto) throws SQLException {
        PreparedStatement statement = con.prepareStatement(this.query);
        statement.setString(1, paramsDto.getStatus());
        statement.setLong(2, paramsDto.getOrderId());
        return statement;
    }

    /**
     *
     * @param ps PreparedStatement
     * @return number of updated columns
     * @throws SQLException in case of an error
     */
    private int executeUpdate(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }
}
