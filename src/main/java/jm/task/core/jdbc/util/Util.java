package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static ServiceRegistry serviceRegistry;

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "test";
        String userName = "root";
        String password = "Igorkan325!";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    public static SessionFactory getSession() {

        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test");
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "Igorkan325!");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.setProperties(prop);

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        SessionFactory factory = cfg.buildSessionFactory(serviceRegistry);

        return factory;
    }

    public static void close() {
        if (serviceRegistry != null) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }
}
