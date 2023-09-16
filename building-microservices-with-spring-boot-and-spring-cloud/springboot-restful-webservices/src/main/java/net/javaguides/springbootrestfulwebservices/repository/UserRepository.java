package net.javaguides.springbootrestfulwebservices.repository;

import net.javaguides.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vsushko
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
