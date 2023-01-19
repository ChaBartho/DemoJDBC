package be.technifutur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    //Propriétés:
    private final static String URL = "jdbc:postgresql://localhost:6969/northwind";
    private final static String USER = "postgres";
    private final static String PSWD = "-dEba9-5Gm-";



    //Méthodes:
    public static Connection createConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PSWD);
    }







}
