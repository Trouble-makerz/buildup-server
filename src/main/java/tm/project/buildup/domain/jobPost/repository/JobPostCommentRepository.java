package tm.project.buildup.domain.jobPost.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.jobPost.entity.JobPostComment;
import tm.project.buildup.global.common.entity.BaseEntity;

import java.util.List;


@Repository
public interface JobPostCommentRepository extends JpaRepository<JobPostComment,Long> {
    List<JobPostComment> findByJobPostIdAndState(Long postId, BaseEntity.State state, PageRequest pageRequest);
}
