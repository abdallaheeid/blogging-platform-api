package org.abdallah.bloggingplatformapi.blogpost.dto;

import lombok.Data;
import java.util.List;

@Data
public class BlogPostDto {

    private String title;
    private String content;
    private String category;
    private List<String> tags;

}
