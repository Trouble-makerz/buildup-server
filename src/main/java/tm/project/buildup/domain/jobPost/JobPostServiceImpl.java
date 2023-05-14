package tm.project.buildup.domain.jobPost;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tm.project.buildup.domain.jobPost.entity.JobPost;
import tm.project.buildup.domain.jobPost.entity.JobPostComment;
import tm.project.buildup.domain.jobPost.entity.JobPostImage;
import tm.project.buildup.domain.jobPost.entity.JobPostLike;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostCommentListRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostDetailsRes;
import tm.project.buildup.domain.jobPost.model.response.GetJobPostListRes;
import tm.project.buildup.domain.jobPost.model.serviceDto.CreateJobPostDto;
import tm.project.buildup.domain.jobPost.model.serviceDto.GetJobPostListDto;
import tm.project.buildup.domain.jobPost.repository.JobPostCommentRepository;
import tm.project.buildup.domain.jobPost.repository.JobPostImageRepository;
import tm.project.buildup.domain.jobPost.repository.JobPostLikeRepository;
import tm.project.buildup.domain.jobPost.repository.JobPostRepository;
import tm.project.buildup.domain.member.MemberService;
import tm.project.buildup.domain.member.entity.Member;
import tm.project.buildup.global.common.entity.BaseEntity;
import tm.project.buildup.global.common.exception.BaseException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static tm.project.buildup.global.common.api.ResponseStatus.NOT_FIND_POST;
import static tm.project.buildup.global.common.entity.BaseEntity.State.ACTIVE;
import static tm.project.buildup.global.common.entity.BaseEntity.State.INACTIVE;

@Transactional
@RequiredArgsConstructor
@Service
public class JobPostServiceImpl implements JobPostService {
    private final MemberService memberService;
    private final JobPostRepository jobPostRepository;
    private final JobPostLikeRepository jobPostLikeRepository;
    private final JobPostCommentRepository jobPostCommentRepository;
    private final JobPostImageRepository jobPostImageRepository;
    @Override
    public void creatJobPost(CreateJobPostDto createJobPostDto) {
        JobPostImage jobPostImage = JobPostImage.builder()
                .url(createJobPostDto.getImageUrl()).build();
        JobPost jobPost = JobPost.builder()
                .content(createJobPostDto.getContent())
                .title(createJobPostDto.getTitle())
                .viewCount(0L)
                .purpose(createJobPostDto.getPurpose())
                .jobPostTarget(createJobPostDto.getJobPostTarget())
                .member(memberService.getMember(createJobPostDto.getId())).build();
        jobPost.addImage(jobPostImage);
        jobPostRepository.save(jobPost);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetJobPostListRes> getJobPostList(GetJobPostListDto getJobPostListDto) {
        PageRequest pageRequest = PageRequest.of(getJobPostListDto.getPage(), getJobPostListDto.getSize());
        List<JobPost> jobPostList = jobPostRepository.findByState(ACTIVE, pageRequest);
        return jobPostList.stream()
                .map(jobPost -> GetJobPostListRes.builder()
                        .id(jobPost.getId())
                        .feedLikeSelf(checkLikeJobPost(getJobPostListDto.getId(), jobPost.getId()))
                        .userName(jobPost.getMember().getNickname())
                        .viewCount(jobPost.getViewCount())
                        .jobPostTime(convertTime(jobPost.getCreatedAt()))
                        .title(jobPost.getTitle())
                        .content(jobPost.getContent())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public void likePost(Long postId, Long memberId) {
        Member member = memberService.getMember(memberId);
        JobPost jobPost = jobPostRepository.findByIdAndState(postId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));
        Optional<JobPostLike> jobPostLikeOptional = jobPostLikeRepository.findByJobPostIdAndMemberId(postId, memberId);
        //좋아요가 존재
        if (jobPostLikeOptional.isPresent()) {
            //좋아요가 활동중인지
            if (jobPostLikeOptional.get().getState().equals(ACTIVE)) {
                jobPostLikeOptional.get().updateState(INACTIVE);
                //비활동중인지
            } else {
                jobPostLikeOptional.get().updateState(ACTIVE);
            }
            //좋아요가 존재하지 않음
        } else {
            JobPostLike jobPostLike = JobPostLike.builder()
                    .member(member).build();
            jobPost.addLikeJobPost(jobPostLike);
        }
    }

    @Override
    public void createComment(Long postId, Long memberId, String content) {
        Member member = memberService.getMember(memberId);
        JobPost jobPost = jobPostRepository.findByIdAndState(postId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));
        JobPostComment jobPostComment = JobPostComment.builder()
                .content(content)
                .build();
        jobPost.addJobPostComment(jobPostComment);
        member.addJobPostComment(jobPostComment);
    }

    @Override
    public GetJobPostDetailsRes jobPostDetail(Long postId) {
        JobPost jobPost = jobPostRepository.findByIdAndState(postId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));
        JobPostImage jobPostImage = jobPostImageRepository.findByJobPostIdAndState(postId,ACTIVE);
        jobPost.addViewCount();
        return GetJobPostDetailsRes.builder()
                .jobPostTime(convertTime(jobPost.getCreatedAt()))
                .content(jobPost.getContent())
                .imageUrl(jobPostImage.getUrl())
                .commentNum((long) jobPost.getJobPostCommentList().size())
                .likeNum((long) jobPost.getJobPostLikeList().size())
                .nickname(jobPost.getMember().getNickname())
                .viewCount(jobPost.getViewCount())
                .title(jobPost.getTitle()).build();
    }

    @Override
    public List<GetJobPostCommentListRes> getCommentList(Long postId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        JobPost jobPost = jobPostRepository.findByIdAndState(postId, ACTIVE)
                .orElseThrow(() -> new BaseException(NOT_FIND_POST));
        List<JobPostComment> jobPostCommentList = jobPostCommentRepository.findByJobPostIdAndState(postId, ACTIVE,pageRequest);
        return jobPostCommentList.stream()
                .map(jobPostComment -> GetJobPostCommentListRes.builder()
                        .name(jobPostComment.getMember().getNickname())
                        .content(jobPostComment.getContent())
                        .time(convertTime(jobPostComment.getCreatedAt()))
                        .build()).collect(Collectors.toList());
    }

    private boolean checkLikeJobPost(Long userId, Long jobPostId) {
        Optional<JobPostLike> feedLike = jobPostLikeRepository.findByJobPostIdAndMemberIdAndState(jobPostId, userId, ACTIVE);
        return feedLike.isPresent();
    }

    public String convertTime(LocalDateTime time) {
        final int SEC = 60;
        final int MIN = 60;
        final int HOUR = 24;
        final int DAY = 30;
        long id = ChronoUnit.MILLIS.between(time, LocalDateTime.now());
        long diffTime = id / 1000;
        String msg;
        if (diffTime < SEC) {
            // sec
            msg = diffTime + "초 전";
        } else if ((diffTime /= SEC) < MIN) {
            // min
            msg = diffTime + "분 전";
        } else if ((diffTime /= MIN) < HOUR) {
            // hour
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= HOUR) <= DAY) {
            // day
            msg = (diffTime) + "일 전";
        } else {
            msg = time.getMonth() + "월 " + time.getDayOfMonth() + "일";
        }
        return msg;
    }
}
