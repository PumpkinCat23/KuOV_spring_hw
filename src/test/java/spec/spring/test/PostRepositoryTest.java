package spec.spring.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spec.spring.config.DataConfig;
import spec.spring.dao.AbstractDAO;
import spec.spring.dao.repository.PostRepository;
import spec.spring.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataConfig.class)
@Sql(scripts = "classpath:blog.sql",
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class PostRepositoryTest {

    private final PostRepository postRepository;

    @Autowired
    public PostRepositoryTest(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Test
    public void getAll(){
        List<Post> result = postRepository.findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void create(){
        Post post = new Post();
        post.setTitle("Day 4");
        post.setContent("All is ok again");
        post.setDtCreated(LocalDateTime.now());

        postRepository.save(post);
        assertEquals(4, postRepository.count());
        assertEquals("Day 4", postRepository.findById(4L).orElseThrow(RuntimeException::new).getTitle());
    }

    @Test
    public void update(){
        Post post = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        post.setTitle("Day 4");

        postRepository.save(post);
        assertEquals(3, postRepository.count());
        assertEquals("Day 4", postRepository.findById(1L).orElseThrow(RuntimeException::new).getTitle());
//        assertNotNull(postRepository.findById(1L).orElseThrow().getDtUpdated());
    }

    @Test
    void delete(){
        postRepository.deleteById(1L);
        assertEquals(2, postRepository.count());
    }

    @Test
    void postTagComment(){
        Post post = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertEquals(2, post.getTags().size());
        assertEquals(3, post.getComments().size());
    }

}
