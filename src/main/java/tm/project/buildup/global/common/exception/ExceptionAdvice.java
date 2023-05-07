package tm.project.buildup.global.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tm.project.buildup.global.common.api.BaseResponse;
import tm.project.buildup.global.common.api.ResponseStatus;


@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<ResponseStatus> BaseExceptionHandle(BaseException exception) {
        log.warn("BaseException. error message: {}", exception.getMessage());
        return new BaseResponse<>(exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<ResponseStatus> ExceptionHandle(Exception exception) {
        log.error("Exception has occured. ", exception);
        return new BaseResponse<>(ResponseStatus.UNEXPECTED_ERROR);
    }
}
