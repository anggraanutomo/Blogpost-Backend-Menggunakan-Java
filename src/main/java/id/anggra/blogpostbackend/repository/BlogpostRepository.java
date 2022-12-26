package id.anggra.blogpostbackend.repository;

import id.anggra.blogpostbackend.model.Blogpost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BlogpostRepository extends MongoRepository<Blogpost, String>
{
    List<Blogpost>findByTitleContaining(String title);
    List<Blogpost>findByContentContaining(String content);
}
