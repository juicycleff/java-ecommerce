package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM users u WHERE u.username = :username")
    User findOneByUsername(String username);
}
