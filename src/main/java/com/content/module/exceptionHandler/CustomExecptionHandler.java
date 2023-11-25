package com.content.module.exceptionHandler;

import com.content.module.posts.exepctionsHandles.PostNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExecptionHandler {

    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<ExecptionResponse> PostNotFound(PostNotFound ex) {
        ExecptionResponse response = new ExecptionResponse(ex.getMessage(), 404, LocalDateTime.now());
        return ResponseEntity.status(404).body(response);
    }
}
