package org.example;

import org.example.config.MyConfig;
import org.example.model.User;
import org.example.service.ConnectKataApi;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ConnectKataApi connectKataApi = context.getBean("connectKataApi", ConnectKataApi.class);

        User newUser = new User(3L, "James", "Brown", (byte) 33);
        User editUser = new User(3L, "Thomas", "Shelby", (byte) 33);

        connectKataApi.getAllUsers();

        System.out.println(connectKataApi.newUser(newUser).getBody() + connectKataApi.editUser(editUser).getBody() + connectKataApi.deleteUser(3L).getBody());

    }
}
