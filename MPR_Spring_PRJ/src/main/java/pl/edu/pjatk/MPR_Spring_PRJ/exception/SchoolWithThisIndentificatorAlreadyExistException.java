package pl.edu.pjatk.MPR_Spring_PRJ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SchoolWithThisIndentificatorAlreadyExistException extends RuntimeException{
    public SchoolWithThisIndentificatorAlreadyExistException(){
        super("Can not create School. School with such inditificator already exist");
    }
}
