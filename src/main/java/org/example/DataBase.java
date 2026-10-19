package org.example;
import java.sql.*;

public class DataBase {
    private static DataBase instance;
    private Connection connection;

    private DataBase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:database.db");
    }


    public static DataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }


    public boolean insertPiatto(String piatto, double prezzo, int quantita) {
        //contrllo connessione database
        try {
            if (!connection.isValid(5)) {
                throw new RuntimeException("Connessione persa");
            }
        } catch (SQLException e) {
            return false;
        }

        String query = "Insert into Sushi_Menu(piatto,prezzo, quantita) values(?,?,?)";

        PreparedStatement Statement = null;
        try {
            Statement = connection.prepareStatement(query);
            Statement.setString(1, piatto);
            Statement.setDouble(2, prezzo);
            Statement.setInt(3, quantita);
            Statement.execute();

        } catch (SQLException e) {
            return false;
        }
        return true;

    }

    public void SelectAll(String table) {
        try {
            if (!connection.isValid(5)) {
                throw new RuntimeException("Connessione persa");
            }
        } catch (SQLException e) {
            throw new  RuntimeException("Connessione persa");
        }
        String query = "Select * from " + table;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                IO.println();
                IO.println("ID: " + resultSet.getInt(1));
                IO.println("Nom: " + resultSet.getString(2));
                IO.println("Prezzo: " + resultSet.getDouble(3));
                IO.println("Quantita: " + resultSet.getInt(4));
            }

        }catch (Exception e){
            throw new  RuntimeException(e);
        }
    }
}
