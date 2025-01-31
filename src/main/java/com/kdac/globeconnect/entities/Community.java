package com.kdac.globeconnect.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kdac.globeconnect.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Integer id;

    @Column(length = 50, nullable = false)
    private String communityName;

    @Column(length = 250)
    private String description;

    @Column(length = 50)
    private String image;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;  // Correctly mapped to Category entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Reference to the user who created or is managing the community
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private int postCount = 0;
    private int reactionCount = 0;
    private int commentCount = 0;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();

 

    @PrePersist
    @PreUpdate
    public void onSaveOrUpdate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        updatedAt = LocalDateTime.now();

        this.postCount = this.posts.size();
       
       
    }

    // Constructor with necessary fields
    public Community(String communityName, String description, String image, Status status, Category category, User user) {
        this.communityName = communityName;
        this.description = description;
        this.image = image;
        this.status = status;
        this.category = category;
        this.user = user;
    }
}
