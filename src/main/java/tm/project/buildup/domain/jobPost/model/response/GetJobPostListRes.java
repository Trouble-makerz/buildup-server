package tm.project.buildup.domain.jobPost.model.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetJobPostListRes {
    @Schema(description = "구인구직 게시글 Id", example = "1")
    Long id;
    @Schema(description = "게시글 작성자 이름", example = "peter")
    String userName;
    @Schema(description = "게시글 제목", example = "peter hihi")
    String title;
    @Schema(description = "게시글 내용", example = "peter hihi")
    String content;
    @Schema(description = "게시글 조회수", example = "2813")
    Long viewCount;
    @Schema(description = "피드 작성 시간", example = "30초 전")
    String jobPostTime;
    @Schema(description = "피드 좋아요 클릭 여부", example = "true")
    boolean feedLikeSelf;
}
