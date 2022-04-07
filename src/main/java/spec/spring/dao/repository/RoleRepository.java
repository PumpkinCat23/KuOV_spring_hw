package spec.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spec.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
