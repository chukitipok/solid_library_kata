package fr.esgi.solid_library_kata.core.services;

import com.google.gson.Gson;
import fr.esgi.solid_library_kata.core.models.User;
import fr.esgi.solid_library_kata.core.utils.FileUtils;
import fr.esgi.solid_library_kata.core.utils.ResourceConverter;
import fr.esgi.solid_library_kata.core.utils.ResourceWriter;

import java.util.List;
import java.util.Optional;

public class UsersService {
    private static final String usersFilePath = "users.json";

    public static List<User> findAll() {
        return new ResourceConverter().convertArray(usersFilePath, User[].class);
    }

    public static Optional<User> findOne(User user) {
        var users = findAll();

        if (users == null) {
            return Optional.empty();
        }

        return users.stream()
                .filter(u -> u.getFirstName().equals(user.getFirstName()))
                .filter(u -> u.getLastName().equals(user.getLastName()))
                .filter(u -> u.getRole().equals(user.getRole()))
                .findAny();
    }

    public static Optional<User> findOneById(int id) {
        var users = findAll();

        if (users == null) {
            return Optional.empty();
        }

        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    public static Optional<User> save(User user) {
        var users = findAll();

        if(!users.isEmpty()) {
            var lastUser = users.get(users.size() - 1);
            user.setId(lastUser.getId() + 1);
        } else {
            user.setId(1);
        }

        users.add(user);

        var json = new Gson().toJson(users);
        var file = FileUtils.getResourceFile(usersFilePath);
        new ResourceWriter().write(file.getAbsolutePath(), json);

        return Optional.of(user);
    }
}
