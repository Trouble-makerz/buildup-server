package tm.project.buildup.domain.jobPost.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetJobPostCommentListRes {
    String name;
    String content;
    String time;
}
