package spec.spring.dao;

import org.springframework.stereotype.Repository;
import spec.spring.entity.Post;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PostJpaDao implements AbstractDAO<Post> {

    //JPQL
    private static String GET_ALL_JPQL = "select p from Post p";

    private EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Post data) {
        em.persist(data);
    }

    @Override
    public List<Post> getAll() {
        return em.createQuery(GET_ALL_JPQL, Post.class).getResultList();
    }

    @Override
    public Post getByID(long postId) {
        return em.find(Post.class, postId);
    }

    @Override
    public void update(Post data) {
        data.setDtUpdated(LocalDateTime.now());
        em.merge(data);
    }

    @Override
    public void delete(long postId) {
        em.remove(getByID(postId));
    }

}
