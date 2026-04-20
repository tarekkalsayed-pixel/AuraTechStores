package org.example.service;

import org.example.model.User;
import org.springframework.stereotype.Service;

@Service
public class Validation {

    public boolean validateAdminLogin(User user) {
        if ("admin".equals(user.getUsername()) &&
                "12345".equals(user.getPassword())) {
            return true;
        }

        return false;
    }

    public boolean validateUser(User user) {
        if (user.getUsertype().isEmpty() || !(
                user.getUsertype().equals("user")
                        || user.getUsertype().equals("admin")
        )) {
            return false;
        }

        if (user.getUsername().isEmpty() || user.getUsername().length() < 4) {
            return false;
        }

        if (user.getPassword().isEmpty() || user.getPassword().length() <= 5) {
            return false;
        }

        return true;
    }
}
