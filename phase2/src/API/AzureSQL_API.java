package API;

import java.sql.*;
import java.util.List;


public class AzureSQL_API {

    private Connection connection;

    /**
     * Constructor to initialize the connection to the Azure SQL database
     */
    public AzureSQL_API() {
        String connectionURL = "jdbc:sqlserver://csc207lads.database.windows.net:1433;" +
                                "database=csc207database;" +
                                "user=csc207admin@csc207lads;" +
                                "password=Yeetthe207lads;" +
                                "encrypt=true;" +
                                "trustServerCertificate=false;" +
                                "hostNameInCertificate=*.database.windows.net;" +
                                "loginTimeout=30";
        try {
            this.connection = DriverManager.getConnection(connectionURL);
        } catch (SQLException e) {
            this.connection = null;
            e.printStackTrace();
        }
    }

    /**
     * Method to get data from all columns from a table that satisfies an optional condition. If no condition
     * is provided, the whole table is returned.
     * @param table: String that represents the name of the table
     * @param condition: String that is the condition in SQL ("" for no condition)
     * @return ResultSet object that has the output from executing the query
     * @throws SQLException: Thrown if the query is not successfully executed
     */
    public ResultSet getAllColumnsFromTable(String table, String condition) throws SQLException {
        String query = "SELECT * FROM " + table + " WHERE " + condition + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    /**
     * Method to get selected columns from a table that satisfies an optional condition. If no condition
     * is provided all the rows of the columns selected are returned
     * @param columns: ArrayList of Strings containing the columns to be selected
     * @param table: String that represents the name of the table
     * @param condition: String that is the condition in SQL ("" for no condition)
     * @return ResultSet object that has the output from executing the query
     * @throws SQLException: Thrown if the query is not successfully executed
     */
    public ResultSet getSelectedColumnsFromTable(List<String> columns, String table, String condition) throws SQLException {
        String joinedColumns = String.join(", ", columns);
        String query = "SELECT " + joinedColumns + " FROM " + table + " WHERE " + condition + ";";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
}
