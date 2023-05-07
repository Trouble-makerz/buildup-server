package tm.project.buildup.domain.jobPost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tm.project.buildup.domain.jobPost.entity.JobPostImage;
@Repository
public interface JobPostImageRepository extends JpaRepository<JobPostImage,Long> {
}
