package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.mysql.cj.jdbc.StatementImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        ConnectionImpl conn = null;
        StatementImpl stmt = null;
        try {
            conn = Util.getMySQLConnection();
            stmt = (StatementImpl) conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.execute(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        ConnectionImpl conn = null;
        StatementImpl stmt = null;
        try {
            conn = Util.getMySQLConnection();

            stmt = (StatementImpl) conn.createStatement();

            String sql = "DROP TABLE IF EXISTS users";

            stmt.execute(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        ConnectionImpl conn = null;
        StatementImpl stmt = null;
        try {
            conn = Util.getMySQLConnection();

            stmt = (StatementImpl) conn.createStatement();

            String sql = "INSERT INTO `test`.`users` (`name`, `lastName`, `age`) VALUES ('" + name + "', '" + lastName + "', " + age + ");";

            stmt.execute(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        ConnectionImpl conn = null;
        StatementImpl stmt = null;
        try {
            conn = Util.getMySQLConnection();
            stmt = (StatementImpl) conn.createStatement();

            String sql = "DELETE FROM users where id = " + id + ";";
            System.out.println(sql);

            stmt.execute(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        {
            ConnectionImpl conn = null;
            StatementImpl stmt = null;
            List<User> ls = new LinkedList<>();
            try {

                conn = Util.getMySQLConnection();
                stmt = (StatementImpl) conn.createStatement();

                String sql = "SELECT * FROM users;";

                ResultSet rs = stmt.executeQuery(sql);
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
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try {
                    if (stmt != null)
                        conn.close();
                } catch (SQLException se) {
                }// do nothing
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
                return ls;
            }
        }
    }

    public void cleanUsersTable() {
        ConnectionImpl conn = null;
        StatementImpl stmt = null;
        try {
            conn = Util.getMySQLConnection();
            stmt = (StatementImpl) conn.createStatement();

            String sql = "DELETE FROM users;";

            stmt.execute(sql);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
