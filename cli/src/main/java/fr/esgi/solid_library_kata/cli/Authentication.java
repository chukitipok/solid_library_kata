package fr.esgi.solid_library_kata.cli;

import fr.esgi.solid_library_kata.core.models.User;
import fr.esgi.solid_library_kata.core.services.UsersService;

import java.util.Optional;
import java.util.Scanner;

public class Authentication {

    public Optional<User> login() {
        Scanner scanner = new Scanner(System.in);
        Optional<User> user = Optional.empty();
        var count = 0;

        while (user.isEmpty() && count < 3) {
            System.out.print("Enter your login ID: ");
            var id = scanner.nextInt();

            user = UsersService.findOneById(id);

            if(user.isEmpty()) {
                System.out.println("User not found.");
            }
            count++;
        }

        return user;
    }

    public Optional<User> register() {
        Scanner scanner = new Scanner(System.in);
        Optional<User> user = Optional.empty();
        var count = 0;

        while (user.isEmpty() && count < 3) {
            System.out.print("Enter your first name: ");
            var firstName = scanner.next();
            System.out.print("Enter your last name: ");
            var lastName = scanner.next();
            var role = setUserRole();

            var tmpUser = new User(firstName, lastName, role);
            user = UsersService.save(tmpUser);

            if(user.isEmpty()) {
                System.out.println("User already exists.");
            }
            count++;
        }
        return user;
    }

    private String setUserRole() {
        Scanner scanner = new Scanner(System.in);
        var role = "";

        do {
            System.out.print("Enter your role (librarian | guest | member) : ");
            role = scanner.next();

            if (!role.equals("librarian") && !role.equals("guest") && !role.equals("member")) {
                System.out.println("You must choose one of the offered options.");
            }
        } while (!role.equals("librarian") && !role.equals("guest") && !role.equals("member"));

        return role;
    }
}
