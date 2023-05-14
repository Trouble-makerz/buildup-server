package tm.project.buildup.domain.jobPost.model.serviceDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tm.project.buildup.domain.jobPost.entity.JobPost;
import tm.project.buildup.domain.jobPost.model.request.CreateJobPostReq;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.global.common.vo.JobPostTarget;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostDto {
    //user Id
    Long id;
    String title;
    String content;
    String imageUrl;
    JobPostTarget jobPostTarget;
    JobPost.Purpose purpose;

    public CreateJobPostDto(Long id, CreateJobPostReq createJobPostReq) {
        this.id =id;
        this.title = createJobPostReq.getTitle();
        this.content = createJobPostReq.getContent();
        this.imageUrl = createJobPostReq.getImageUrl();
        this.jobPostTarget = createJobPostReq.getJobPostTarget();
        this.purpose = createJobPostReq.getPurpose();
    }
}
