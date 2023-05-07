package tm.project.buildup.domain.jobPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.jobPost.entity.JobPostLike;
import tm.project.buildup.global.common.entity.BaseEntity;

import java.util.Optional;

@Repository
public interface JobPostLikeRepository extends JpaRepository<JobPostLike,Long> {
    Optional<JobPostLike> findByJobPostIdAndMemberIdAndState(Long jobPostId, Long userId, BaseEntity.State active);
}
