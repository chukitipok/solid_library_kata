package fr.esgi.solid_library_kata.cli;

import fr.esgi.solid_library_kata.core.models.User;
import fr.esgi.solid_library_kata.core.services.UsersService;

import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.exit;

public class App {

    private boolean programFinished;
    private Optional<User> user;

    public App() {
        this.programFinished = false;
        this.user = Optional.empty();
    }

    public void launch() {
        var scanner = new Scanner(System.in);
        System.out.println("1. Login\t2. Register");
        var choice = scanner.nextInt();

        loginOrRegister(choice);

//        while (!programFinished) {
//
//
//        }
    }

    private void loginOrRegister(int choice) {
        switch (choice) {
            case 1:
                user = new Authentication().login();
                break;
            case 2:
                user = new Authentication().register();
                break;
        }
    }

}
