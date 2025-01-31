package com.kdac.globeconnect.entities;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.kdac.globeconnect.enums.CommentStatus; // Import Enum for status

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Reference to the post the comment belongs to

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")  // Correctly reference the user_id column
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment; // Reference to the parent comment (nullable for top-level comments)

    @Column(length = 500) // Limit the content size
    private String content; // Comment content

    @Enumerated(EnumType.STRING)
    private CommentStatus status; // Status of the comment (active or deleted)

    private LocalDateTime createdAt; // Timestamp when the comment was created

    private LocalDateTime updatedAt; // Timestamp when the comment was last updated

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = CommentStatus.ACTIVE; // Set status to ACTIVE when a comment is created
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Method to delete the comment (soft delete)
    public void deleteComment() {
        this.status = CommentStatus.DELETED; // Change the status to DELETED
        this.updatedAt = LocalDateTime.now(); // Update the timestamp when the comment is deleted
    }
}
