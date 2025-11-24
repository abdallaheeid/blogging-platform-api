package org.abdallah.bloggingplatformapi.blogpost.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
