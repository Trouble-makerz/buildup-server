package tm.project.buildup.domain.member.entity;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tm.project.buildup.domain.jobPost.entity.JobPost;
import tm.project.buildup.domain.jobPost.entity.JobPostComment;
import tm.project.buildup.domain.jobPost.entity.JobPostLike;
import tm.project.buildup.global.common.vo.Role;
import tm.project.buildup.global.common.entity.BaseEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseEntity {
    @Column(name = "fcm_id", nullable = false)
    private String fcm_id;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @OneToOne(mappedBy = "member", cascade = ALL)
    private SocialLogin socialLogin;

    @OneToMany(mappedBy = "member", cascade = ALL)
    @Builder.Default
    List<JobPost> jobCommentList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL)
    @Builder.Default
    List<JobPostComment> jobPostCommentList = new ArrayList<>();
    @OneToMany(mappedBy = "member", cascade = ALL)
    @Builder.Default
    List<JobPostLike> jobPostLikeList = new ArrayList<>();

    public void setSocialLogin(SocialLogin socialLogin) {
        this.socialLogin = socialLogin;
        socialLogin.setMember(this);
    }

    public void addJobPostComment(JobPostComment jobPostComment) {
        this.jobPostCommentList.add(jobPostComment);
        jobPostComment.setJobPost(this);
    }
}
