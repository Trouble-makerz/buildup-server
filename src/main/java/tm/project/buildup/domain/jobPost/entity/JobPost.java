package tm.project.buildup.domain.jobPost.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.global.common.entity.BaseEntity;
import tm.project.buildup.global.common.vo.JobPostTarget;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name = "job_post") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class JobPost extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "purpose", nullable = false)
    Purpose purpose;
    @Enumerated(EnumType.STRING)
    @Column(name = "job_post_target", nullable = false)
    private JobPostTarget jobPostTarget;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "content", nullable = false)
    String content;
    @Column(name = "view_count", nullable = false)
    Long viewCount;
    @OneToMany(mappedBy = "jobPost", cascade = ALL)
    @Builder.Default
    List<JobPostImage> jobPostImageList = new ArrayList<>();
    @OneToMany(mappedBy = "jobPost", cascade = ALL)
    @Builder.Default
    List<JobPostComment> jobPostCommentList = new ArrayList<>();
    @OneToMany(mappedBy = "jobPost", cascade = ALL)
    @Builder.Default
    List<JobPostLike> jobPostLikeList = new ArrayList<>();
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    Member member;
    public enum Purpose {
        JOB,PERSON;
    }

    public void addLikeJobPost(JobPostLike jobPostLike) {
        jobPostLikeList.add(jobPostLike);
        jobPostLike.setJobPost(this);
    }
}
