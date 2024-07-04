import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private static final String USERS_FILE = "data/users.dat";
    private static final String MESSAGES_FILE = "data/messages.dat";

    private List<Message> messages;
    private List<User> users;

    public ChatRoom() {
        messages = loadMessages();
        users = loadUsers();
    }

    public void addUser(User user) {
        users.add(user);
        saveUsers();
    }

    public void addMessage(Message message) {
        messages.add(message);
        saveMessages();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.validatePassword(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveMessages() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MESSAGES_FILE))) {
            oos.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Message> loadMessages() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MESSAGES_FILE))) {
            return (List<Message>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
