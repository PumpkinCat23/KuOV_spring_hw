package spec.spring.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spec.spring.config.DataConfig;
import spec.spring.dao.repository.UserRepository;
import spec.spring.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void getAll() {
        List<User> result = userRepository.findAll();
        assertEquals(0, result.size());
    }

    @Test
    public void create() {
        User user = new User();
        user.setFirstName("Olga");
        user.setLastName("Kuptsova");
        user.setUsername("OK");
        user.setPassword("123456");
        user.setCreatedAT(LocalDateTime.now());
        user.setActive(true);

        userRepository.save(user);
        assertEquals(1, userRepository.count());
        assertEquals("OK", userRepository.findById(1L).
                orElseThrow(NoSuchElementException::new).getUsername());
    }

    @Test
    void delete() {
        userRepository.deleteById(1L);
        assertEquals(0, userRepository.count());
    }

    @Test
    void userPostComment() {
        User user = userRepository.findById(1L).orElseThrow(NoSuchElementException::new);
        assertEquals(0, user.getComments().size());
//        assertEquals(0, user.getPost.size());
    }
}
