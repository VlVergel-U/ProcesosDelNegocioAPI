package com.procesos.parcial.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Request<T> {
    Date message;
    T object;
}