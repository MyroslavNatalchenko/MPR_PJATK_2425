package pl.edu.pjatk.MPR_Spring_PRJ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SchoolExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value={SchoolNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(SchoolNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
