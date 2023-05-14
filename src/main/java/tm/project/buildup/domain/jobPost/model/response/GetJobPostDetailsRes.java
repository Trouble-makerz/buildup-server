package tm.project.buildup.domain.jobPost.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetJobPostDetailsRes {
    @Schema(description = "작성자 닉네임", example = "kim minsick")
    String nickname;
    @Schema(description = "제목", example = "haha...")
    String title;
    @Schema(description = "본문", example = "haha...")
    String content;
    @Schema(description = "좋아요 개수", example = "1")
    Long likeNum;
    @Schema(description = "게시글 작성 시간", example = "30초 전")
    String jobPostTime;
    @Schema(description = "게시글 댓글 개수", example = "3")
    Long commentNum;
    @Schema(description = "게시글 조회수", example = "2813")
    Long viewCount;
    @Schema(description = "이미지 주소", example = "https://firebasestorage.googleapis.com/iufhgoisfhgoishof.jpg")
    String imageUrl;
}
