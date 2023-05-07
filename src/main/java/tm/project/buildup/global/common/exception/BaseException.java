package tm.project.buildup.global.common.exception;

import lombok.Getter;
import lombok.Setter;
import tm.project.buildup.global.common.api.ResponseStatus;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private ResponseStatus status;

    public BaseException(ResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }
}
