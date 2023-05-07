package tm.project.buildup.domain.jobPost;

import tm.project.buildup.domain.jobPost.model.response.GetJobPostListRes;
import tm.project.buildup.domain.jobPost.model.serviceDto.CreateJobPostDto;
import tm.project.buildup.domain.jobPost.model.serviceDto.GetJobPostListDto;

import java.util.List;

public interface JobPostService {

    void creatJobPost(CreateJobPostDto createJobPostDto);

    List<GetJobPostListRes> getJobPostList(GetJobPostListDto purpose);
}
