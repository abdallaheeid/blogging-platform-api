package org.abdallah.bloggingplatformapi.blogpost.repository;

import org.abdallah.bloggingplatformapi.blogpost.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    BlogPost findBlogPostById(Long id);

    void deleteBlogPostById(Long id);

    BlogPost findBlogPostByTitle(String title);

    List<BlogPost> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String title,
            String content,
            String category
    );
}
