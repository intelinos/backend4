package org.example.back4.dbUtils;

import org.example.back4.beans.User;

import java.sql.SQLException;
import java.util.Collection;

public interface  CheckUserDAO {
    void addUser(User user) throws SQLException;
    void updateUser(Long user_id, User user) throws SQLException;
    User getUserById(Long user_id) throws SQLException;
    Collection<User> getAllUsers() throws SQLException;
    void deleteUser(User user) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
}
