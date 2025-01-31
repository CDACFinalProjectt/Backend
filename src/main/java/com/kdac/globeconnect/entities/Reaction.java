package com.kdac.globeconnect.entities;

import com.kdac.globeconnect.enums.ReactionType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"post", "user", "reactionType"}) // Ensures equality based on post, user, and reaction type
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reaction_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING) // Store reaction type as a String in DB
    private ReactionType reactionType;

    private LocalDateTime reactedAt; // Timestamp for when the reaction was made

    // Constructor with post, user, and reactionType
    public Reaction(Post post, User user, ReactionType reactionType) {
        this.post = post;
        this.user = user;
        this.reactionType = reactionType;
    }

    // Set the reactedAt timestamp before persisting
    @PrePersist
    public void onCreate() {
        reactedAt = LocalDateTime.now(); // Set the reaction time when the reaction is created
    }
}
