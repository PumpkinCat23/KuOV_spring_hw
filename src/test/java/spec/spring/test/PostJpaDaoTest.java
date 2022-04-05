package spec.spring.test;

import javafx.geometry.Pos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spec.spring.config.DataConfig;
import spec.spring.dao.AbstractDAO;
import spec.spring.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class PostJpaDaoTest {

    private final AbstractDAO<Post> postDao;

    @Autowired
    public PostJpaDaoTest(AbstractDAO<Post> dao) {
        this.postDao = dao;
    }

    @Test
    public void getAll(){
        List<Post> result = postDao.getAll();
        assertEquals(3, result.size());
    }

    @Test
    public void create(){
        Post post = new Post();
        post.setTitle("Day 4");
        post.setContent("All is ok again");
        post.setDtCreated(LocalDateTime.now());

        postDao.create(post);
        assertEquals(4, postDao.getAll().size());
        assertEquals("Day 4", postDao.getByID(4).getTitle());
    }

    @Test
    public void update(){
        Post post = postDao.getByID(1);
        post.setTitle("Day 4");

        postDao.update(post);
        assertEquals(3, postDao.getAll().size());
        assertEquals("Day 4", postDao.getByID(1).getTitle());
        assertNotNull(postDao.getByID(1).getDtUpdated());
    }

    @Test
    void delete(){
        postDao.delete(1);
        assertEquals(2, postDao.getAll().size());
    }

    @Test
    void postTagComment(){
        Post post = postDao.getByID(1);
        assertEquals(2, post.getTags().size());
        assertEquals(3, post.getComments().size());
    }
}
