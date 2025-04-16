package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static SessionFactory sessionFactory;
    private static Connection dbConnection;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DATABASE = "postgres";
    private static final String USERNAME = "postgres";
    private static final String PASS = "admin";

    public static Connection getJdbcConnection() {

        while (dbConnection == null) {

            try {
                Class.forName(DRIVER);
                System.out.println("Драйвер успешно загружен");
            } catch (ClassNotFoundException e) {
                System.out.println("Не удалось загрузить драйвер");
                break;
            }
            try {
                dbConnection = DriverManager.getConnection(
                        URL + DATABASE, USERNAME, PASS);
                System.out.println("Успешное подключение к БД");
            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к БД");
                break;
            }
        }
        return dbConnection;

    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "admin");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}