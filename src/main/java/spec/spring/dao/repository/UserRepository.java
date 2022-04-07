package spec.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spec.spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
