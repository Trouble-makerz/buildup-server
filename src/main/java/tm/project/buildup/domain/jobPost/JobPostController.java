package tm.project.buildup.domain.jobPost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tm.project.buildup.domain.jobPost.model.request.CommentReq;
import tm.project.buildup.domain.jobPost.model.request.CreateJobPostReq;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostCommentListRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostDetailsRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostListRes;
import tm.project.buildup.domain.jobPost.model.serviceDto.CreateJobPostDto;
import tm.project.buildup.domain.jobPost.model.serviceDto.GetJobPostListDto;
import tm.project.buildup.global.common.api.BaseResponse;
import tm.project.buildup.global.common.exception.BaseException;

import java.util.List;

import static tm.project.buildup.domain.jobPost.entity.JobPost.*;
import static tm.project.buildup.global.common.api.ResponseStatus.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/post/job")
@Tag(name = "JobPost")
public class JobPostController {
    private final JobPostService jobPostService;

    @Operation(summary = "구인 구직글 작성")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse<String>> creatJobPost(@Validated @RequestBody CreateJobPostReq createJobPostReq) {
        Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
        jobPostService.creatJobPost(new CreateJobPostDto(userId, createJobPostReq));
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(SUCCESS));
    }

    @Operation(summary = "구인 구직 글 리스트 조회")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    @GetMapping(value = "/{purpose}")
    public ResponseEntity<BaseResponse<List<GetJobPostListRes>>> getJobPostList(@Parameter(name = "purpose", description = "blame 의 id", in = ParameterIn.PATH) @PathVariable Purpose purpose,
                                                                                @Parameter(name = "page", description = " 페이지 0이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer page,
                                                                                @Parameter(name = "size", description = "페이지 사이즈 1이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer size) {
        Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
        GetJobPostListDto getJobPostListDto = new GetJobPostListDto(purpose, page, size, userId);
        List<GetJobPostListRes> getJobPostListResList = jobPostService.getJobPostList(getJobPostListDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(getJobPostListResList));
    }

    @Operation(summary = "구인 구직 글 좋아요")
    @PostMapping("/{postId}/like")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    public ResponseEntity<BaseResponse<String>> likeJobPost(@Parameter(name = "postId", description = "post의 id", in = ParameterIn.PATH) @PathVariable Long postId) {
        try {
            Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
            jobPostService.likePost(postId, userId);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>("성공"));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(e.getStatus()));
        }
    }
    @Operation(summary = "구인 구직 글 상세 조회")
    @GetMapping("/{postId}")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    public ResponseEntity<BaseResponse<GetJobPostDetailsRes>> jobPostDetail(@Parameter(name = "postId", description = "post의 id", in = ParameterIn.PATH) @PathVariable Long postId) {
        try {
            Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
            GetJobPostDetailsRes getJobPostDetailsRes = jobPostService.jobPostDetail(postId);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(getJobPostDetailsRes));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(e.getStatus()));
        }
    }

    @Operation(summary = "댓글 작성")
    @PostMapping("/{postId}/comment")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    public ResponseEntity<BaseResponse<String>> postComment(@Parameter(name = "postId", description = "post의 id", in = ParameterIn.PATH) @PathVariable Long postId,
                                                            @RequestBody CommentReq commentReq) {
        try {
            Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
            jobPostService.createComment(postId, userId, commentReq.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>("성공"));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(e.getStatus()));
        }
    }
    @Operation(summary = "댓글 조회")
    @GetMapping("/{postId}/comment")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    public ResponseEntity<BaseResponse<List<GetJobPostCommentListRes>>> getCommentList(@Parameter(name = "postId", description = "post의 id", in = ParameterIn.PATH) @PathVariable Long postId,
                                                                                   @Parameter(name = "page", description = " 페이지 0이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer page,
                                                                                   @Parameter(name = "size", description = "페이지 사이즈 1이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer size) {
        try {
            Long userId = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
            List<GetJobPostCommentListRes> getJobPostCommentListResList = jobPostService.getCommentList(postId,page,size);
            return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(getJobPostCommentListResList));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BaseResponse<>(e.getStatus()));
        }
    }
}