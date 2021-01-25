package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection conn;

    static {
        try {
            conn = Util.getMySQLConnection();
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void execute(String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet executeGetData(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return rs;
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        execute(sql);
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        execute(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO `test`.`users` (`name`, `lastName`, `age`) VALUES ('" + name + "', '" + lastName + "', " + age + ");";
        execute(sql);
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users where id = " + id + ";";
        execute(sql);
    }

    public List<User> getAllUsers() {
        List<User> ls = new LinkedList<>();
        String sql = "SELECT * FROM users;";
        try (ResultSet rs = executeGetData(sql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                byte age = rs.getByte("age");

                User user = new User(name, lastname, age);
                user.setId(id);

                ls.add(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return ls;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users;";
        execute(sql);
    }
}
