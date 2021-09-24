package ru.aldamm.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.aldamm.models.Person;
import java.util.List;


@Component
public class PersonDAO {
    private int ID = 0;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // fixme в контексте дао слоя, на мой взгляд не совсем прозрачное название
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    // fixme аналогично
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    // fixme похоже, что метод называется скорее insert
    public void save(Person person) {
        /* todo
         *  ++i - не thread-safe, т.к. он не атомарный.
         *  Соответственно, если одновременно в разных потоках пользователи иницииуруют сохранение результат будет не однозначный.
         *  В целом, обычно используют sequences в БД для таких целей.
         *  Если при этом будет необходимость получить сгенерированное значение ключа, то можно посмотреть в сторону
         *  KeyHolder или SimpleJdbcInsert
         */
        jdbcTemplate.update("INSERT INTO Person VALUES(?,?,?,?)",++ID, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=? , email=? WHERE id=? ", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
