package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService impl = new UserServiceImpl();
        impl.createUsersTable();

        impl.saveUser("Test1","Testov1", (byte) 25);
        impl.saveUser("Test2","Testov2", (byte) 26);
        impl.saveUser("Test3","Testov3", (byte) 27);
        impl.saveUser("Test4","Testov4", (byte) 28);

        List<User> ls = impl.getAllUsers();
        ls.forEach(System.out::println);

        impl.cleanUsersTable();

        impl.dropUsersTable();

        impl.close();
    }
}
