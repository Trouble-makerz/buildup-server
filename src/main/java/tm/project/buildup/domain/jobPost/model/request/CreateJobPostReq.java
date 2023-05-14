package tm.project.buildup.domain.jobPost.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tm.project.buildup.global.common.vo.JobPostTarget;

import static tm.project.buildup.domain.jobPost.entity.JobPost.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobPostReq {
    @NotNull(message = "null 안됩니다.")
    @Schema(description = "제목", example = "haha...")
    String title;
    @NotNull(message = "null 안됩니다.")
    @Schema(description = "본문", example = "haha...")
    String content;
    @NotNull(message = "null 안됩니다.")
    @Schema(description = "타겟", example = "ENGINEER,MANAGER,ASSISTANT")
    JobPostTarget jobPostTarget;
    @NotNull(message = "null 안됩니다.")
    @Schema(description = "목표", example = "JOB(구직),PERSON(구인)")
    Purpose purpose;
    @NotNull(message = "null 안됩니다.")
    @Schema(description = "이미지 주소", example = "https://firebasestorage.googleapis.com/iufhgoisfhgoishof.jpg")
    String imageUrl;
}
