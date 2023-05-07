package tm.project.buildup.domain.jobPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.jobPost.entity.JobPostComment;


@Repository
public interface JobPostCommentRepository extends JpaRepository<JobPostComment,Long> {
}
