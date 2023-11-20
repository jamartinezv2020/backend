package UdeA.zerohunger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;

public class HomeNotFoundException extends Exception{
    @ExceptionHandler(HomeNotFoundException.class)
    public ResponseEntity<String> handleHomeNotFoundException(HomeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}

