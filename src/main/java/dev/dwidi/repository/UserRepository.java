package dev.dwidi.repository;

import dev.dwidi.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User findByUserId(Long userId) {
        return find("userId", userId).firstResult();
    }
    public boolean existsByUsername(String username) {
        return find("username", username).firstResult() != null;
    }

    public boolean existsByEmail(String email) {
        return find("email", email).firstResult()!= null;
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return find("phoneNumber", phoneNumber).firstResult()!= null;
    }
}
