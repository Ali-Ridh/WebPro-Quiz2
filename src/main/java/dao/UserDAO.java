package dao;

import model.User;
import java.util.List;

public interface UserDAO {
    void addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int userId);
}
