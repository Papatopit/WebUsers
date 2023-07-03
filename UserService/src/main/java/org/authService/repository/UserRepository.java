package org.authService.repository;

import org.authService.entity.Role;
import org.authService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Role> findRoleByUserId(@Param("userId") Long userId);
}
