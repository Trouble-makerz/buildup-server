package tm.project.buildup.domain.jobPost;

import tm.project.buildup.domain.jobPost.model.response.GetJobPostCommentListRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostDetailsRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostListRes;
import tm.project.buildup.domain.jobPost.model.serviceDto.CreateJobPostDto;
import tm.project.buildup.domain.jobPost.model.serviceDto.GetJobPostListDto;

import java.util.List;

public interface JobPostService {

    void creatJobPost(CreateJobPostDto createJobPostDto);

    List<GetJobPostListRes> getJobPostList(GetJobPostListDto purpose);

    void likePost(Long postId, Long userId);

    void createComment(Long postId, Long userId, String content);

    GetJobPostDetailsRes jobPostDetail(Long postId);

    List<GetJobPostCommentListRes> getCommentList(Long postId, Integer page, Integer size);
}
