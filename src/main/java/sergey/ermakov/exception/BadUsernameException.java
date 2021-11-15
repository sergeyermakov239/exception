package sergey.ermakov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadUsernameException extends ResponseStatusException {
    public BadUsernameException(){
        super(HttpStatus.BAD_REQUEST);
    }
}
