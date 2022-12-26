package id.anggra.blogpostbackend.controller;

import id.anggra.blogpostbackend.model.Blogpost;
import id.anggra.blogpostbackend.repository.BlogpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class BlogpostController
{
    @Autowired
    BlogpostRepository blogpostRepository;

    @GetMapping("/blogposts")
    public ResponseEntity<List<Blogpost>> getAllBlogposts()
    {
        try {
            List<Blogpost> blogposts = blogpostRepository.findAll();

            if (blogposts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(blogposts, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/blogposts/{id}")
    public ResponseEntity<Blogpost> getBlogpostById(@PathVariable("id") String id)
    {
        Optional<Blogpost> blogpost = blogpostRepository.findById(id);

        if(blogpost.isPresent())
        {
            return new ResponseEntity<>(blogpost.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/blogposts")
    public ResponseEntity<Blogpost> createBlogpost(@Valid @RequestBody Blogpost blogpost)
    {
        try {
            Blogpost _blogpost = blogpostRepository.save(new Blogpost(blogpost.getTitle(), blogpost.getContent()));
            return new ResponseEntity<>(_blogpost, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/blogposts/{id}")
    public ResponseEntity<Blogpost> updateBlogpost(@PathVariable("id") String id, @Valid @RequestBody Blogpost blogpost)
    {
        Optional<Blogpost> blogpostData = blogpostRepository.findById(id);

        if (blogpostData.isPresent()) {
            Blogpost _blogpost = blogpostData.get();
            _blogpost.setTitle(blogpost.getTitle());
            _blogpost.setContent(blogpost.getContent());
            return new ResponseEntity<>(blogpostRepository.save(_blogpost), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/blogposts/{id}")
    public ResponseEntity<String> deleteBlogpost(@PathVariable("id") String id)
    {
        try {
            blogpostRepository.deleteById(id);
            return new ResponseEntity<>("Blogpost was deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
