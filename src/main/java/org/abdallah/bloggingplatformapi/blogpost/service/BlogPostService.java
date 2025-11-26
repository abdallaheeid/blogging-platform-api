package org.abdallah.bloggingplatformapi.blogpost.service;

import org.abdallah.bloggingplatformapi.blogpost.entity.BlogPost;
import org.abdallah.bloggingplatformapi.blogpost.repository.BlogPostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost createPost(BlogPost blogPost) {
        validateBlogPost(blogPost);

        blogPost.setCreatedAt(LocalDateTime.now());
        blogPost.setUpdatedAt(LocalDateTime.now());

        return blogPostRepository.save(blogPost);
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Blog post with ID " + id + " not found"
                ));
    }

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost updatePost(Long id, BlogPost updatedPost) {
        validateBlogPost(updatedPost);

        BlogPost existingPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Blog post with ID " + id + " not found"
                ));
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setCategory(updatedPost.getCategory());
        existingPost.setTags(updatedPost.getTags());
        existingPost.setUpdatedAt(LocalDateTime.now());

        return blogPostRepository.save(existingPost);
    }

    public void deletePostById(Long id) {
        if (!blogPostRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog post with ID " + id + " not found");
        }
        blogPostRepository.deleteById(id);
    }

    public List<BlogPost> searchPosts(String term) {
        return blogPostRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrCategoryContainingIgnoreCase(
                        term, term, term
                );
    }

    private void validateBlogPost(BlogPost blogPost) {

        if (blogPost.getTitle() == null || blogPost.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Title must not be empty");
        }
    }


}
