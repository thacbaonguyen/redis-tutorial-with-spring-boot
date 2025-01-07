package com.thacbao.spring.redis_tutorial.dao;

import com.thacbao.spring.redis_tutorial.dto.ExerciseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExerciseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<ExerciseDto> getExerciseAndFilter(String order, String by, String search) throws SQLDataException {
        try {
            String sql = "select code, title, paper, input, output from exercise "+
                    (search != null ? "where lower(code) like concat('%', :search, '%') " +
                            "or lower(title) like concat('%', :search, '%') " : "") +
                    (order != null && by != null ?  "order by " + by + " " + order + " " : "");
            Query query = entityManager.createNativeQuery(sql);
            if(search != null)
                query.setParameter("search", search);
            List<Object[]> rs = query.getResultList();
            return rs.stream().map(o -> new ExerciseDto(o[0].toString(), o[1].toString(),
                    o[2].toString(), o[3].toString(), o[4].toString())).collect(Collectors.toList());
        }
        catch (Exception ex){
            throw new SQLDataException(ex.getMessage());
        }
    }

    @Transactional
    public ExerciseDto viewDetailsQuestion(String code) throws SQLDataException {
        try {
            String sql = "select code, title, paper, input, output from exercise where code = :code";
            Object[] rs = (Object[]) entityManager.createNativeQuery(sql)
                    .setParameter("code", code)
                    .getSingleResult();
            return new ExerciseDto(rs[0].toString(), rs[1].toString(),
                    rs[2].toString(), rs[3].toString(), rs[4].toString());
        }
        catch (Exception ex){
            throw new SQLDataException(ex.getMessage());
        }
    }

    @Transactional
    public void deleteExercise(String code) throws SQLDataException {
        try {
            String sql = "delete from exercise where code = :code";
            entityManager.createNativeQuery(sql).setParameter("code", code).executeUpdate();
        }
        catch (Exception ex){
            throw new SQLDataException(ex.getMessage());
        }
    }
}
