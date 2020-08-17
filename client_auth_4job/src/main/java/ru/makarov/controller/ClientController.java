package ru.makarov.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.makarov.model.Person;

import java.util.List;

/**
 * Main conrtoller for REST API.
 */
@RestController
public class ClientController {
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    /**
     * Sample GET req: curl -i http://localhost:8084/client.
     *
     * @return - all data in JSON format.
     */
    @GetMapping("/client")
    public List<Person> findAll() {
        RestTemplate rest = new RestTemplate();
        return rest.exchange(API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {
                }).getBody();
    }

    /**
     * Sample GET req: curl -i http://localhost:8084/person/{id}.
     *
     * @param id - person by ID for return.
     * @return - person data by id in JSON format.
     */
    @GetMapping("/client/{id}")
    public Person findById(@PathVariable int id) {
        RestTemplate rest = new RestTemplate();
        List<Person> list = findAll();
        Person entity =  list.get(id);
        return rest.getForObject(API_ID, Person.class,entity.getId());
    }
//
    /**
     * Sample POST req: curl -H 'Content-Type: application/json' -X POST -d '{"login":"job4j@gmail.com","password":"123"}' http://localhost:8084/client/.
     *
     * @param person - in JSON {:} format.
     * @return - post result. add parameter to DATA.
     */
    @PostMapping("/client")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        RestTemplate rest = new RestTemplate();
        Person entity = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<Person>(entity,
                HttpStatus.CREATED
        );
    }

    /**
     * Sample PUT req: curl -H 'Content-Type: curl -i -H 'Content-Type: application/json' -X PUT -d '{"id":"5","login":"supportNew@job4j.com","password":"123"}' http://localhost:8084/client/
     *
     * @param person - update person by ID(took from JSON).
     * @return - update data in DATA.
     */
    @PutMapping("/client")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        RestTemplate rest = new RestTemplate();
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    /**
     * Sample DELETE req: curl -i -X DELETE http://localhost:8084/client/{id}
     *
     * @param id - id person to delete.
     * @return - delete person by ID.
     */
    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        RestTemplate rest = new RestTemplate();
        List<Person> list = findAll();
        Person entity =  list.get(id);
        rest.delete(API_ID,entity.getId());
        return ResponseEntity.ok().build();
    }
}