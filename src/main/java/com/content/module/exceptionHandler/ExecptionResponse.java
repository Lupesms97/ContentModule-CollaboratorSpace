package com.content.module.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecptionResponse {
    private String message;
    private Integer status;
    private LocalDateTime dateOccurrence;

}
