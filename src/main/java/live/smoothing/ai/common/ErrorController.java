package live.smoothing.ai.common;

import com.influxdb.exceptions.InfluxException;
import live.smoothing.common.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(InfluxException.class)
    public ResponseEntity<ErrorResponse> influxException(InfluxException e, HttpServletRequest request) {

        return ResponseEntity.status(e.status()).body(ErrorResponse.builder()
                .status(HttpStatus.valueOf(e.status()))
                .errorMessage(e.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}
