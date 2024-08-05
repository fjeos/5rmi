package com.ormi5.movieblog.usercontroller;

import com.ormi5.movieblog.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    List<User> findByUsernameContaining(String keyword);
}