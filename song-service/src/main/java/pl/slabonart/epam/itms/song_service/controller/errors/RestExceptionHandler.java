package pl.slabonart.epam.itms.song_service.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.slabonart.epam.itms.song_service.exceptions.InvalidRequestException;
import pl.slabonart.epam.itms.song_service.exceptions.SongMetaDataNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, InvalidRequestException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(SongMetaDataNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, SongMetaDataNotFoundException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
