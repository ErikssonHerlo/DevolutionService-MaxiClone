package com.erikssonherlo.common.application.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor

public class ApiResponse<T> {
    private Integer code;
    private String message;
    private HttpStatus status;
    private T data;

    public ApiResponse(Integer code, String message, HttpStatus status, T data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
