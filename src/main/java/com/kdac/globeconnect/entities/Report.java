package com.kdac.globeconnect.entities;

import com.kdac.globeconnect.enums.ReportStatus;
import com.kdac.globeconnect.enums.ReportType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;  // Type of report (post/comment)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key to the User who reported
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")  // Foreign key to Post, can be null if it's a comment report
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")  // Foreign key to Comment, can be null if it's a post report
    private Comment comment;

    private String reportReason;  // Reason for reporting

    @Enumerated(EnumType.STRING)
    private ReportStatus reportStatus;  // Status of the report (PENDING, RESOLVED, etc.)

    private LocalDateTime createdAt;  // Timestamp when the report was created
    private LocalDateTime updatedAt;  // Timestamp when the report was last updated
}
