package tm.project.buildup.domain.jobPost.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.global.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class JobPostComment extends BaseEntity {
    @Column(name = "content", nullable = false)
    String content;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "job_post_id")
    JobPost jobPost;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    Member member;
}