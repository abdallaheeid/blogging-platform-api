package org.abdallah.bloggingplatformapi.blogpost.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.abdallah.bloggingplatformapi.blogpost.dto.BlogPostDto;
import org.abdallah.bloggingplatformapi.blogpost.entity.BlogPost;
import org.abdallah.bloggingplatformapi.blogpost.service.BlogPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@Tag(name = "Blog Posts", description = "CRUD operations for blog posts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping("/posts")
    @Operation(summary = "Create a new blog post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post is created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPostDto request){

        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(request.getTitle());
        blogPost.setContent(request.getContent());
        blogPost.setCategory(request.getCategory());
        blogPost.setTags(request.getTags());

        BlogPost createdPost = blogPostService.createPost(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/posts/{id}")
    @Operation(summary = "Get a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post is retrieved"),
            @ApiResponse(responseCode = "404", description = "Post was not found")})
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id){
        BlogPost postById = blogPostService.getPostById(id);
        return ResponseEntity.ok(postById);
    }

    @GetMapping("/posts")
    @Operation(summary = "Get All blog posts")
    @ApiResponse(responseCode = "200", description = "Posts are retrieved successfully")
    public ResponseEntity<List<BlogPost>> getAllBlogPosts(){
        List<BlogPost> allPosts = blogPostService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    @PutMapping("/posts/{id}")
    @Operation(summary = "Update a blog post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post is updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<BlogPost> updateBlogPostById(@PathVariable Long id,  @RequestBody BlogPostDto request){

        BlogPost updatedPost = new BlogPost();
        updatedPost.setTitle(request.getTitle());
        updatedPost.setContent(request.getContent());
        updatedPost.setCategory(request.getCategory());
        updatedPost.setTags(request.getTags());

        BlogPost blogPostUpdated = blogPostService.updatePost(id, updatedPost);
        return ResponseEntity.ok(blogPostUpdated);
    }

    @DeleteMapping("/posts/{id}")
    @Operation(summary = "Delete a post by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post is deleted"),
            @ApiResponse(responseCode = "404", description = "Post was not found")})
    public ResponseEntity<Void> deleteBlogPostById(@PathVariable Long id){
        blogPostService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }




}
