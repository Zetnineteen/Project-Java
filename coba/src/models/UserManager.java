package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_FILE = "users.txt";
    private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                users.add(new User(parts[0], parts[1], parts[2]));
            }
        } catch (IOException e) {
            // Create default admin if file doesn't exist
            users.add(new User("admin", "admin123", "seller"));
            saveUsers();
        }
    }

    public void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(String.format("%s,%s,%s",
                        user.getUsername(), user.getPassword(), user.getRole()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String username, String password) {
        return users.stream().anyMatch(u ->
                u.getUsername().equals(username) && u.getPassword().equals(password));
    }

    public String getUserRole(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .map(User::getRole)
                .orElse("");
    }

    public void addUser(String username, String password, String role) {
        users.add(new User(username, password, role));
        saveUsers();
    }
}
