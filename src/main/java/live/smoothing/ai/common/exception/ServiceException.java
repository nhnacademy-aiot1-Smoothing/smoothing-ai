package live.smoothing.ai.common.exception;

import live.smoothing.ai.common.ErrorCode;
import live.smoothing.common.exception.CommonException;

public class ServiceException extends CommonException {
    public ServiceException(ErrorCode errorCode) {

        super(errorCode.getStatus(), errorCode.getMessage());
    }
}
