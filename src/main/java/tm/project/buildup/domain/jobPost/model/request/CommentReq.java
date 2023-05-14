package tm.project.buildup.domain.jobPost.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentReq {
    @Schema(description = "댓글 내용", example = "haha...")
    String content;
}
