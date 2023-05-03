package tm.project.buildup.global.common.api;

import lombok.Getter;

@Getter
public class BaseResponse<T>{
    private final int statusCode;
    private final String message;
    private final T data;

    public BaseResponse(ResponseCode responseCode, T data) {
        this.statusCode = responseCode.getStatus().value();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public BaseResponse(ResponseCode responseCode) {
        this.statusCode = responseCode.getStatus().value();
        this.message = responseCode.getMessage();
        this.data = null;
    }
}
