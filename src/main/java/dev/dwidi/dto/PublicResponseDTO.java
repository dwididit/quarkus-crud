package dev.dwidi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicResponseDTO<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
