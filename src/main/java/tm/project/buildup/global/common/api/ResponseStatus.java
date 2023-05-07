package tm.project.buildup.global.common.api;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum ResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 400 : Request, Response 오류
     */

    USERS_EMPTY_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일을 입력해주세요."),
    TEST_EMPTY_COMMENT(false, HttpStatus.BAD_REQUEST.value(), "코멘트를 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,HttpStatus.BAD_REQUEST.value(),"중복된 이메일입니다."),
    POST_TEST_EXISTS_MEMO(false,HttpStatus.BAD_REQUEST.value(),"중복된 메모입니다."),

    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND.value(), "값을 불러오는데 실패하였습니다."),

    DUPLICATED_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "중복된 이메일입니다."),
    INVALID_MEMO(false,HttpStatus.NOT_FOUND.value(), "존재하지 않는 메모입니다."),
    FAILED_TO_LOGIN(false,HttpStatus.NOT_FOUND.value(),"없는 아이디거나 비밀번호가 틀렸습니다."),
    EMPTY_JWT(false, 402, "JWT를 입력해주세요."),
    INVALID_JWT(false, 401, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,403,"권한이 없는 유저의 접근입니다."),
    NOT_FIND_USER(false,410,"유저를 찾을 수 없습니다."),
    INVALID_OAUTH_TYPE(false, HttpStatus.BAD_REQUEST.value(), "알 수 없는 소셜 로그인 형식입니다."),
    //피드 430
    OVER_IMAGE(false,432,"이미지는 최대 10개까지 입니다."),
    TEXT_LENGTH_ERROR(false, 433, "피드는 1자 이상 2200자 이하입니다."),
    NOT_USER_FEED(false,431,"유저의 피드가 아닙니다."),
    NOT_FIND_FEED(false,430, "피드를 찾을 수 없습니다."),
    //댓글 450
    NOT_FIND_COMMENT(false,450, "댓글을 찾을 수 없습니다."),
    NOT_USER_COMMENT(false,453,"유저의 댓글이 아닙니다."),
    COMMENT_LENGTH_ERROR(false, 454, "댓글은 1자 이상 220자 이하입니다."),
    //Blame
    NOT_FIND_BLAME(false,470, "신고내역을 찾을 수 없습니다."),
    //관리자 490
    NOT_ADMIN(false,490,"관리자가 아닙니다."),
    NOT_EXIST_PAGE(false,411,"페이지가 존재하지않습니다."),
    INVALID_PAGE_SIZE(false,412,"올바른 페이지 사이즈가 아닙니다."),
    INVALID_STATE(false,413,"유효한 상태값이 아닙니다."),
    INVALID_BLAME_CONTENT(false,414,"올바른 신고 내용이 아닙니다."),
    INVALID_DATE(false,415,"올바른 날자 형식이 아닙니다."),
    INVALID_ACTION(false, 416,"올바른 action이 아닙니다." ),
    /**
     * 500 :  Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),
    PASSWORD_ENCRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "비밀번호 복호화에 실패하였습니다."),


    MODIFY_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저 삭제 실패"),
    MODIFY_FAIL_MEMO(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"메모 수정 실패"),

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 에러가 발생했습니다.");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
