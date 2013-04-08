package cn.iver.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static Connection connection = null;
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:108/da563f7d9a260432eb101d35c9e77c480";
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "dvo2uhcinlgj", "rcimzxwrkwoh");
            System.out.println("Connect success fully");
            return connection;

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
            e.printStackTrace();

        }
        return null;

    }

    public static void main(String[] args) throws Exception{
        Connection conn = DB.getConnection();
        System.out.println(conn.isClosed());;
        conn.close();
        System.out.println(conn.isClosed());;
    }
}