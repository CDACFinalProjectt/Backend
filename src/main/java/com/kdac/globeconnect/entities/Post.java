package com.kdac.globeconnect.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.kdac.globeconnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(length = 100)
    private String title;

    private String captions;

    @CreationTimestamp
    private Timestamp createdAt;

    private String image;

    private String video;

    @Enumerated(EnumType.STRING) // Store status as String in DB
    private Status status;

    private int commentsCount;

    private int reactionCount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime postCreatedAt;

    @Column(nullable = false)
    private LocalDateTime postUpdatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Correctly reference the user_id column
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "category_id")  // Make sure this matches the column in the Category entity
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>(); // Relationship with comments

    @PrePersist
    public void prePersist() {
        postCreatedAt = LocalDateTime.now();
        postUpdatedAt = LocalDateTime.now(); 
    }

    @PreUpdate
    public void preUpdate() {
        postUpdatedAt = LocalDateTime.now(); 
    }

}
