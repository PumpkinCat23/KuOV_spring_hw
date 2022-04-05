package spec.spring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spec.spring.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
