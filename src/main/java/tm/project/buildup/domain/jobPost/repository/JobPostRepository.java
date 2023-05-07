package tm.project.buildup.domain.jobPost.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.jobPost.entity.JobPost;
import tm.project.buildup.global.common.entity.BaseEntity;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long> {
    List<JobPost> findByState(BaseEntity.State active, PageRequest pageRequest);
}
