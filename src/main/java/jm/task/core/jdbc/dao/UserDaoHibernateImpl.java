package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static Session ses;
    static {
        try {
            ses = Util.getSession().openSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public UserDaoHibernateImpl() {

    }

    public void close() {
        ses.close();
        Util.close();
    }

    @Override
    public void createUsersTable() {
        try{
            ses.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            ses.createSQLQuery(sql).executeUpdate();
            ses.getTransaction().commit();

        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            ses.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            ses.createSQLQuery(sql).executeUpdate();
            ses.getTransaction().commit();

        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            ses.beginTransaction();
            ses.save(new User(name, lastName, age));
            ses.getTransaction().commit();
        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            ses.beginTransaction();
            User us = (User) ses.get(User.class, id);
            ses.delete(us);
            ses.getTransaction().commit();
        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> res = null;
        try {
            ses.beginTransaction();
            res = ses.createQuery("from users").list();
            ses.getTransaction().commit();
        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();
        }
        return res;
    }

    @Override
    public void cleanUsersTable() {
        try {
            ses.beginTransaction();
            ses.createQuery("delete from users").executeUpdate();
            ses.getTransaction().commit();
        } catch (Exception ex) {
            ses.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
