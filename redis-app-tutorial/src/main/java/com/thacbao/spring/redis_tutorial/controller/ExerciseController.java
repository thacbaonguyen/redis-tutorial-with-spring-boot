package com.thacbao.spring.redis_tutorial.controller;

import com.thacbao.spring.redis_tutorial.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis-tutorial")
public class ExerciseController{

    @Autowired
    private ExerciseService exerciseService;
    @PostMapping("/insert")
    public ResponseEntity<?> insertNew(@RequestBody Map<String, String> request){
        return exerciseService.insertNew(request);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getExerciseAndFilter(@RequestParam(required = false) String order,
                                                  @RequestParam(required = false) String by,
                                                  @RequestParam(required = false) String search){
        return exerciseService.getExerciseAndFilter(order, by, search);
    }

    @GetMapping("/view-question/{code}")
    public ResponseEntity<?> viewDetailsQuestion(@PathVariable String code){
        return exerciseService.viewDetailsQuestion(code);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteExercise(@PathVariable String code){
        return exerciseService.deleteExercise(code);
    }
}
