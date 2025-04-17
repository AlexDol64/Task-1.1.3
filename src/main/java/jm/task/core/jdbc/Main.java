package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Катя", "Рожкова", (byte) 30);
        userService.saveUser("Петр", "Петров", (byte) 28);
        userService.saveUser("Юля", "Мельникова", (byte) 20);
//        userService.dropUsersTable();
//        userService.cleanUsersTable();
//        userService.removeUserById(1);
        List<User> list = userService.getAllUsers();
        for (User user : list){
            System.out.println(user);
        }

    }
}
