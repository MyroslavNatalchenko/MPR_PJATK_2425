package pl.edu.pjatk.MPR_Spring_PRJ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CanNotEditSchoolException extends RuntimeException {
    public CanNotEditSchoolException() {
        super("School not found. It is impossible to edit School that does not exist");
    }
}
