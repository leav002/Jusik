import java.io.*;
import java.util.*;

class User {
    private String name;
    private String id;
    private String password;

    User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}

public class Inandout {
    private static final String FILE_NAME = "user.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String input = sc.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:

                        return;
                    default:
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    private static void register() {
        System.out.print("이름: ");
        String name = sc.nextLine();

        String id;
        while (true) {
            id = sc.nextLine();
            if (availableId(id)) {
                break;
            } else {
            }
        }

        String password;
        while (true) {
            password = sc.nextLine();
            if (!availablePassword(password)) {
                continue;
            }
            String confirmPassword = sc.nextLine();
            if (password.equals(confirmPassword)) {
                break;
            } else {
            }
        }
        save(name, id, password);
    }

    private static boolean availablePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isLowerCase(c)) hasLowerCase = true;
            if (Character.isDigit(c)) hasDigit = true;
        }
        if (!hasUpperCase) {
        }
        if (!hasLowerCase) {
        }
        if (!hasDigit) {
        }
        return hasUpperCase && hasLowerCase && hasDigit;
    }

    private static boolean availableId(String id) {
        List<User> users = load();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    private static void login() {
        String id = sc.nextLine();
        String password = sc.nextLine();

        List<User> users = load();
        boolean loginSuccess = false;
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                loginSuccess = true;
                break;
            }
        }
        if (!loginSuccess) {
        }
    }

    private static void save(String name, String id, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(name + "," + id + "," + password);
            writer.newLine();
        } catch (IOException e) {
        }
    }

    private static List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
        }
        return users;
    }
}
