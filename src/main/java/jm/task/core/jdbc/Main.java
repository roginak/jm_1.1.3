package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl impl = new UserServiceImpl();

        impl.createUsersTable(); //Создание таблицы User(ов)

        //Добавление 4 User(ов)
        impl.saveUser("Igor", "Kan", (byte) 23);
        impl.saveUser("Ivan", "Ivanov", (byte) 10);
        impl.saveUser("Julia", "Chang", (byte) 25);
        impl.saveUser("Stephe", "Fox", (byte) 26);

        //Получение всех User из базы
        List<User> ls = impl.getAllUsers();
        System.out.println("Список всех User(ов):");
        for (User user: ls) {
            System.out.println(user);
        }

        //Очистка таблицы User(ов)
        impl.cleanUsersTable();

        //Удаление таблицы
        impl.dropUsersTable();
    }
}
