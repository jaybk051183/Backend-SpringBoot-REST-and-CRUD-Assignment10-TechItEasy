package nl.novi.techiteasycontroller.Controllers;

import nl.novi.techiteasycontroller.Exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelevisionsController {

    @GetMapping("television")
    public ResponseEntity<String> getAllTelevisions() {
        return new ResponseEntity<>("television", HttpStatus.OK);
    }

    //Ophalen van de television entiteiten uit de database door GetMapping
    @GetMapping("television/{id}")
    public ResponseEntity<String> getTelevisionWithId(@PathVariable int id) {
        if (id == 5){
            throw new RecordNotFoundException("Novi is de beste");
        }
        return new ResponseEntity<>("television: " + id, HttpStatus.OK);
    }

    @PutMapping("television/{id}")
    public ResponseEntity<String>updateTelevisionsList(@PathVariable int id, @RequestParam String name){
        return ResponseEntity.noContent().build();
    }

    //Maken van entiteiten in je database door PostMapping
    @PostMapping("television")
    public ResponseEntity<String>addTelevisionsList(@RequestBody String television) {
        //Location: null, omdat je nog geen URI kunt returnen welke verwijst naar localhost 8080/television/1
        return ResponseEntity.created(null).body(television);
    }

    @DeleteMapping("television/{id}")
    public ResponseEntity<String>deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }
}





