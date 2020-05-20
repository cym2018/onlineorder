package xyz.cym2018.onlineorder.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
