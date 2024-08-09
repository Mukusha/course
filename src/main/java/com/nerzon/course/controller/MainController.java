package com.nerzon.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainController {
    @Autowired
    private CatRepo catRepo;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat) {
        log.info("New row: " + catRepo.save(cat));
    }

    @SneakyThrows // исключение просто выбросится - без обработки
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row!";
        }
        return catRepo.save(cat).toString();
    }
}
