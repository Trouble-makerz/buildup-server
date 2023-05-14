package tm.project.buildup.domain.jobPost.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
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
public class JobPostImage extends BaseEntity {
    @Column(name = "url", nullable = false)
    String url;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "job_post_id")
    JobPost jobPost;

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }
}
