package ru.steshko.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.steshko.springcourse.dao.UserDAO;
import ru.steshko.springcourse.models.Role;
import ru.steshko.springcourse.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User loadUserByUserEmail(String s) throws UsernameNotFoundException {
        User user = userDAO.findByUserByEmail(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User loadUserById(int id) throws UsernameNotFoundException {
        User user = userDAO.show(id);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(User user) {
        User userFromDB = userDAO.findByUserByEmail(user.getEmail());

        if (userFromDB != null) {
            return false;
        }

      //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return true;
    }
    public User findUserById(int userId) {
        Optional<User> userFromDb = Optional.ofNullable(userDAO.show(userId));
        return userFromDb.orElse(new User());
    }

    public boolean deleteUser(int userId) {
        if (userDAO.show(userId)!= null) {
            userDAO.delete(userId);
            return true;
        }
        return false;
    }
    public void updateUser(int userId, User newUser) {
        if (userDAO.show(userId)!= null) {
            userDAO.update(userId,newUser);
        }
    }
    public List<User> allUsers() {
      return userDAO.index();
    }
}
