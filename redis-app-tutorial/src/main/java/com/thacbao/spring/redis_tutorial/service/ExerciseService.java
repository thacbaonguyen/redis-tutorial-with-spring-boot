package com.thacbao.spring.redis_tutorial.service;

import com.thacbao.spring.redis_tutorial.dao.ExerciseDao;
import com.thacbao.spring.redis_tutorial.dto.ExerciseDto;
import com.thacbao.spring.redis_tutorial.model.Exercise;
import com.thacbao.spring.redis_tutorial.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository repository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ExerciseDao exerciseDao;
    public ResponseEntity<?> insertNew(Map<String, String> request){
        try {
            Exercise exercise = new Exercise();
            exercise.setCode(request.get("code"));
            exercise.setTitle(request.get("title"));
            exercise.setPaper(request.get("paper"));
            exercise.setInput(request.get("input"));
            exercise.setOutput(request.get("output"));
            exercise.setPaper(request.get("paper"));
            repository.save(exercise);
            // Clear all related caches when the data changes
            clearCache();// // Delete related cache
            return new ResponseEntity<>("Create success", HttpStatus.OK);
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ResponseEntity<?> getExerciseAndFilter(String order, String by, String search){
        String cacheKey = "exerciseFilter";
        if(order == null && by == null && search == null)
            cacheKey += ":";
        if(order != null && by != null)
            cacheKey += ":" + order + ":" + by;
        if(search != null)
            cacheKey += ":" + search;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        try {
            // Inspect cache
            List<ExerciseDto> cacheEx = (List<ExerciseDto>) valueOperations.get(cacheKey);
            if(cacheEx != null){
                System.out.println("caching with cacheKey: " + cacheKey);
                return new ResponseEntity<>(cacheEx, HttpStatus.OK); // If the cache is valid, return the data stored in the cache
            }
            // Perform a query if the cache is empty
            List<ExerciseDto> exerciseDtos = exerciseDao.getExerciseAndFilter(order, by, search); // queries
            valueOperations.set(cacheKey, exerciseDtos, 10, TimeUnit.HOURS); // save cache with ttl time = 10 hours
            return new ResponseEntity<>(exerciseDtos, HttpStatus.OK);
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
    public ResponseEntity<?> viewDetailsQuestion(String code){
        // Create cacheKey
        String cacheKey = "exerciseDetails:" + code;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        try {
            // Inspect cache
            ExerciseDto cacheEx = (ExerciseDto) valueOperations.get(cacheKey);
            if(cacheEx != null) {
                System.out.println("caching with cacheKey: " + cacheKey);
                return new ResponseEntity<>(cacheEx, HttpStatus.OK); // If the cache is valid, return the data stored in the cache
            }
            // Perform a query if the cache is empty
            ExerciseDto exerciseDto = exerciseDao.viewDetailsQuestion(code);
            valueOperations.set(cacheKey, exerciseDto, 10, TimeUnit.HOURS); // save cache with ttl time = 10 hours
            return new ResponseEntity<>(exerciseDto, HttpStatus.OK);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public ResponseEntity<?> deleteExercise(String code){
        try {
            exerciseDao.deleteExercise(code);
            String cacheKey = "exerciseDetails:" + code;
            redisTemplate.delete(cacheKey);// Clear cache directly
            clearCache(); // Delete related cache
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    private void clearCache(){
        redisTemplate.delete(redisTemplate.keys("exerciseFilter:*"));
    }
}
