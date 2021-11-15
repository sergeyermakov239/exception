package sergey.ermakov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadUserNumberException extends ResponseStatusException {
    public BadUserNumberException(){
        super(HttpStatus.BAD_REQUEST);
    }
}
