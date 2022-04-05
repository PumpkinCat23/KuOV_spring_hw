package spec.spring.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spec.spring.config.DataConfig;
import spec.spring.dao.AbstractDAO;
import spec.spring.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PostJdbcDaoTest {

    private final AbstractDAO<PostDto> postDao;

    @Autowired
    public PostJdbcDaoTest(AbstractDAO<PostDto> dao) {
        this.postDao = dao;
    }

    @Test
    public void getAll(){
        List<PostDto> result = postDao.getAll();
        assertEquals(3, result.size());
    }

    @Test
    public void create(){
        PostDto postDto = new PostDto();
        postDto.setTitle("Day 4");
        postDto.setContent("All is ok again");
        postDto.setDtCreated(LocalDateTime.now());

        postDao.create(postDto);
        assertEquals(4, postDao.getAll().size());
        assertEquals("Day 4", postDao.getByID(4).getTitle());
    }

    @Test
    public void update(){
        PostDto postDto = postDao.getByID(1);
        postDto.setTitle("Day 4");

        postDao.update(postDto);
        assertEquals(3, postDao.getAll().size());
        assertEquals("Day 4", postDao.getByID(1).getTitle());
        assertNotNull(postDao.getByID(1).getDtUpdated());
    }

    @Test
    void delete(){
        postDao.delete(1);
        assertEquals(2, postDao.getAll().size());
    }

}
