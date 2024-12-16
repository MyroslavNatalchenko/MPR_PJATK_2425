package pl.edu.pjatk.MPR_Spring_PRJ.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SchoolNotFoundException extends RuntimeException {
    public SchoolNotFoundException() {
        super("School not found");
    }
}
