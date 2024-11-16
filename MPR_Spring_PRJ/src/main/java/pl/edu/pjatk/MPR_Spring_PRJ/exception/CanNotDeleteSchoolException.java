package pl.edu.pjatk.MPR_Spring_PRJ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CanNotDeleteSchoolException extends RuntimeException{
    public CanNotDeleteSchoolException(){
        super("Can Not Delete School. School with such ID does not exist");
    }

}
