package com.nerzon.course.controller;

import com.nerzon.course.DTO.CatDTO;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "main_methods", description="Главная страница котиков")
@RequiredArgsConstructor
@RestController
public class MainController {
    @Autowired
    private CatRepo catRepo;

    @Operation(
            summary = "Кладет нового котика в базу",
            description = "Получает DTO кота и билдером сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info(
                "New row: " + catRepo.save(
                        Cat.builder()
                                .age(catDTO.getAge())
                                .weight(catDTO.getWeight())
                                .name(catDTO.getName())
                                .build())
        );
    }

    @Operation(
            summary = "Возвращает список котиков"
    )
    @SneakyThrows // исключение просто выбросится - без обработки
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @Operation(
            summary = "Возвращает данные кота по id"
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Удаляет кота по id"
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @Operation(
            summary = "Изменяет данные кота"
    )
    @PutMapping("/api")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row!";
        }
        return catRepo.save(cat).toString();
    }
}
