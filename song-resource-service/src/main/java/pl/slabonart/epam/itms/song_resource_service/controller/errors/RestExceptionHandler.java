package pl.slabonart.epam.itms.song_resource_service.controller.errors;

import org.apache.tika.exception.TikaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.xml.sax.SAXException;
import pl.slabonart.epam.itms.song_resource_service.exception.InvalidFileException;
import pl.slabonart.epam.itms.song_resource_service.exception.ResourceNotFoundException;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request, InvalidFileException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(TikaException.class)
    public ResponseEntity<Object> handleTikaException(HttpServletRequest request, TikaException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(SAXException.class)
    public ResponseEntity<Object> handleSAXException(HttpServletRequest request, SAXException ex) {
        return buildResponseEntity(
                ErrorResponse.builder()
                        .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                        .message(ex.getMessage())
                        .timeStamp(LocalDateTime.now())
                        .build());
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}
