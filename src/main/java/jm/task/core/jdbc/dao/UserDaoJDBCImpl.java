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
        execute("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastname VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))");
    }

    public void dropUsersTable() {
        execute("DROP TABLE IF EXISTS users");
    }

    public void saveUser(String name, String lastName, byte age) {
        execute("INSERT INTO `test`.`users` (`name`, `lastName`, `age`) VALUES ('" + name + "', '" + lastName + "', " + age + ");");
    }

    public void removeUserById(long id) {
        execute("DELETE FROM users where id = " + id + ";");
    }

    public List<User> getAllUsers() {
        List<User> ls = new LinkedList<>();
        try (ResultSet rs = executeGetData("SELECT * FROM users;")) {
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age"));
                user.setId(rs.getLong("id"));
                ls.add(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return ls;
    }

    public void cleanUsersTable() {
        execute("DELETE FROM users;");
    }
}
