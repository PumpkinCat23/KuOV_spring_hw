package spec.spring.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spec.spring.config.DataConfig;
import spec.spring.dao.repository.RoleRepository;
import spec.spring.entity.Role;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class RoleRepositoryTest {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    public void getAll() {
        List<Role> result = roleRepository.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void create() {
        Role role = new Role();
        role.setName("admin");

        roleRepository.save(role);
        assertEquals(1, roleRepository.count());
        assertEquals("admin", roleRepository.findById(4L).orElseThrow(NoSuchElementException::new).getRoleId());
    }

    @Test
    void delete() {
        roleRepository.deleteById(1L);
        assertEquals(0, roleRepository.count());
    }

}

