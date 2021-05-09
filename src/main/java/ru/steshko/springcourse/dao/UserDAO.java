package ru.steshko.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.steshko.springcourse.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index(){
       return jdbcTemplate.query("SELECT * FROM \"users\"",new BeanPropertyRowMapper<>(User.class));
    }

    public User show(int id){
       return jdbcTemplate.query("SELECT * FROM \"users\" WHERE id=?",new Object[]{id},new BeanPropertyRowMapper<>(User.class))
               .stream().findAny().orElse(null); // заменить на эксепшен
    }

    public User findByUserByEmail(String s){
        return jdbcTemplate.query("SELECT * FROM \"users\" WHERE email=?",new Object[]{s},new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null); // заменить на эксепшен
    }

    public void save(User person) {
      jdbcTemplate.update("INSERT INTO  \"users\" (name, surname, email, password) VALUES (?,?,?,?)",person.getName(),person.getSurname(),
              person.getEmail(),person.getPassword());
    }

    public void update(int id, User person) {

        jdbcTemplate.update("UPDATE   \"users\" SET name =?, surname=?, email=? WHERE id=?",
                person.getName(),person.getSurname(),
                person.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"users\" WHERE id=?",id);
    }
}
