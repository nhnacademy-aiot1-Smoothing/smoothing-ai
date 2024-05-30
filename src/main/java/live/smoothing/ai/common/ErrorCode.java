package live.smoothing.ai.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SAVE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "로그 저장 실패");

    private final HttpStatus status;
    private final String message;
}
