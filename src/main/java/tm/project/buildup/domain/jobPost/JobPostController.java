package tm.project.buildup.domain.jobPost;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tm.project.buildup.domain.jobPost.entity.JobPost;
import tm.project.buildup.domain.jobPost.model.request.CreateJobPostReq;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostListRes;
import tm.project.buildup.domain.jobPost.model.serviceDto.CreateJobPostDto;
import tm.project.buildup.domain.jobPost.model.serviceDto.GetJobPostListDto;
import tm.project.buildup.global.common.api.BaseResponse;

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

    @Operation(summary = "구인 구직 생성")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse<String>> creatJobPost(@Validated @RequestBody CreateJobPostReq createJobPostReq) {
        Long id = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
        jobPostService.creatJobPost(new CreateJobPostDto(id, createJobPostReq));
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(SUCCESS));
    }

    @Operation(summary = "구인 구직 글 리스트 조회")
    @Parameter(name = "id", description = "(임시) member id", in = ParameterIn.HEADER)
    @GetMapping(value = "/{purpose}")
    public ResponseEntity<BaseResponse<List<GetJobPostListRes>>> getJobPostList(@Parameter(name = "purpose", description = "blame 의 id", in = ParameterIn.PATH) @PathVariable Purpose purpose,
                                                                                @Parameter(name = "page", description = " 페이지 0이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer page,
                                                                                @Parameter(name = "size", description = "페이지 사이즈 1이상", in = ParameterIn.QUERY) @RequestParam(required = false) Integer size) {
        Long id = Long.valueOf(((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader("id"));
        GetJobPostListDto getJobPostListDto = new GetJobPostListDto(purpose,page,size,id);
        System.out.println(purpose+" "+page+" "+size+" "+id);
        List<GetJobPostListRes> getJobPostListResList = jobPostService.getJobPostList(getJobPostListDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(getJobPostListResList));
    }
}