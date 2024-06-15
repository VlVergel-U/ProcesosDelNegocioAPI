package com.procesos.parcial.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Response {

    private LocalDate date;
    private List<String> message;
    private String statusCode;
    private Object data;

}
