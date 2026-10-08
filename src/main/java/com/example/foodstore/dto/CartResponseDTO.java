package com.example.foodstore.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDTO {

    private Long id;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
