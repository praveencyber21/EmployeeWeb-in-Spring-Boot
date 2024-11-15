package com.praveen.EmployeeWeb.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private T data;
    private int error_code;
    private String message;
    private boolean success;

}
