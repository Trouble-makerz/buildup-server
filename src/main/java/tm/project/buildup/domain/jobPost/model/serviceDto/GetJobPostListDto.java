package tm.project.buildup.domain.jobPost.model.serviceDto;

import lombok.*;

import static tm.project.buildup.domain.jobPost.entity.JobPost.*;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetJobPostListDto {
    Purpose purpose;
    Integer page;
    Integer size;
    long id;
}
