package tm.project.buildup.global.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tm.project.buildup.global.common.api.ErrorCode;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final ErrorCode errorCode;
}