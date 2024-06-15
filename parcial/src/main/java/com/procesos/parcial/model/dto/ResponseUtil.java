package com.procesos.parcial.model.dto;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ResponseUtil {

    public Response generateResponse (String message, Object data, String status) {

        return Response.builder()
                .date(LocalDate.now())
                .message(List.of(message))
                .data(data)
                .statusCode(status)
                .build();

    }

}
