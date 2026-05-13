package org.example.repository;
// It handles operations like:
//saving data
//searching
//updating
//deleting
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Spring Boot automatically creates the implementation at runtime.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
