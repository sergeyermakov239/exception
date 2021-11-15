package sergey.ermakov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoUserException extends ResponseStatusException {
    public NoUserException(){
        super(HttpStatus.NOT_FOUND);
    }

}
