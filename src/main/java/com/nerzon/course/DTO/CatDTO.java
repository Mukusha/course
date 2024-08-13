package com.nerzon.course.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Сущность кота")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CatDTO {
    @Schema(description = "Имя", example = "Василий")
    String name;
    @Schema(description = "Вес", example = "5")
    int weight;
    @Schema(description = "Возраст", example = "3")
    int age;
}
