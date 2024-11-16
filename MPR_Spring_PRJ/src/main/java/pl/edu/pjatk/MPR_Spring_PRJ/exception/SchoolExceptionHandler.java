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

    @ExceptionHandler(value={CanNotEditSchoolException.class})
    public ResponseEntity<Object> handleCanNotEdit(CanNotEditSchoolException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={CanNotDeleteSchoolException.class})
    public ResponseEntity<Object> handleCanNotDelete(CanNotDeleteSchoolException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={SchoolWithThisIndentificatorAlreadyExistException.class})
    public ResponseEntity<Object> handleCanNotCreate(SchoolWithThisIndentificatorAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value = {CanNotDeleteSchoolException.class, CanNotEditSchoolException.class, SchoolNotFoundException.class})
//    public ResponseEntity<Object> handleExceptions(RuntimeException ex){
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
