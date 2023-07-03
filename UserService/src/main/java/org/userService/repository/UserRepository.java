package org.userService.repository;

import org.userService.entity.Role;
import org.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findRoleByUserId(@Param("id") Long id);

}
