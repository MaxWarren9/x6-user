package com.sds.x6_user.exception;

import com.sds.x6_user.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    public class ExceptionController {
        @ExceptionHandler(UserException.class)
        public ResponseEntity<ApiError> handleUserError(final UserException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiError(false, e.getMessage()));
        }
}
