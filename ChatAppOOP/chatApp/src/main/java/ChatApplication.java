import java.util.Scanner;

public class ChatApplication {
    private static ChatRoom chatRoom = new ChatRoom();
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    register(scanner);
                } else if (choice == 2) {
                    login(scanner);
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            } else {
                System.out.println("1. Send Message");
                System.out.println("2. View Chat History");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    sendMessage(scanner);
                } else if (choice == 2) {
                    viewChatHistory();
                } else if (choice == 3) {
                    logout();
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(username, password);
        chatRoom.addUser(user);
        System.out.println("User registered successfully.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (chatRoom.authenticateUser(username, password)) {
            currentUser = new User(username, password);
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void sendMessage(Scanner scanner) {
        System.out.print("Enter message: ");
        String content = scanner.nextLine();
        Message message = new Message(currentUser, content);
        chatRoom.addMessage(message);
        System.out.println("Message sent.");
    }

    private static void viewChatHistory() {
        System.out.println("Chat Box:");
        for (Message message : chatRoom.getMessages()) {
            System.out.println(message);
        }
    }

    private static void logout() {
        currentUser = null;
        System.out.println("Logged out.");
    }
}
