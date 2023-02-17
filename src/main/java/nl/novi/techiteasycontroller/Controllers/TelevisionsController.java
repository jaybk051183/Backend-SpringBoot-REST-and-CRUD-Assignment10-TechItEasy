package nl.novi.techiteasycontroller.Controllers;

import nl.novi.techiteasycontroller.Exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.Exceptions.TvNameTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionsController {
    //Bonusopdracht: Maak in je controller class een Lijst van Strings genaamd "televisionDataBase", dit gaat dienen als onze database.:
    ArrayList<String >televisionDataBase = new ArrayList<>();

    //Bonusopdracht: Zorg dat je GET requests de juiste data returnen uit de lijst. (Gebruik de "id" PathVariable als index van de lijst)
    @GetMapping("television")
    public ResponseEntity<ArrayList<String>> getAllTelevisions() {
        //Voeg televisie items toe aan de lijst:
        televisionDataBase.add("Sony Bravia");
        televisionDataBase.add("Samsung QLED");
        televisionDataBase.add("LG OLED");
        //Retourneer de lijst als een responseEntity:
        return new ResponseEntity<>(televisionDataBase, HttpStatus.OK);
    }

/*    Basisopdracht: Ophalen van de television entiteiten uit de database door GetMapping
   @GetMapping("television/{id}")
    public ResponseEntity<String> getTelevisionById(@PathVariable int id) {
        if (id == 5){
            throw new RecordNotFoundException("Novi is de beste");
        }
        return new ResponseEntity<>("television: " + id, HttpStatus.OK);*/


    @GetMapping("television/{id}")
    public ResponseEntity<String> getTelevisionById(@PathVariable int id) {
        String television;
        // Controleer of de opgegeven id overeenkomt met een geldige televisie-ID:
        if (id != 1 && id != 2 && id != 3) {
            //Geef een foutmelding in een ResponseEntity met statuscode BAD_REQUESTals het id niet overeenkomt:
            throw new RecordNotFoundException("Opgegeven ID kan niet gevonden worden");
        } else {
            //Als het id wel overeenkomt, haal dan de bijbehorende televisiegegevens op en retourneer een String in een ResponseEntity met statuscode OK:
            if (id == 1) {
                television = "Sony Bravia";
            } else if (id == 2) {
                television = "Samsung QLED";
            } else {
                television= "LG OLED";
            }
            return new ResponseEntity<>(television, HttpStatus.OK);
        }
    }


/*    Basisopdracht:
    @PutMapping("television/{id}")
    public ResponseEntity<String>updateTelevisionsList(@PathVariable int id, @RequestParam String name){
        return ResponseEntity.noContent().build();
    }*/

    //Bonusopdracht: Zorg dat je PUT request de string op positie x in de "database" wijzigt. (gebruik hier de index van de lijst)
    @PutMapping("television/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable int id, @RequestBody String television) {
        //Controleer of het opgegeven indexnummer (id) binnen de array valt:
        if (id >= televisionDataBase.size() || id < 0) {
            //Als indexnummer buiten het bereik van de lijst valt, retourneer een foutmelding:
            return new ResponseEntity<>("Invalid index", HttpStatus.BAD_REQUEST);
            //Anders wijzig de stringwaarde op de positie id in de lijst m.b.v. de set-methode.
            // Geef vervolgens een ResponseEntity met statuscode OK:
        } else {
            televisionDataBase.set(id, television);
            return new ResponseEntity<>("Television corrected successfully", HttpStatus.OK);
        }
    }

/*    Basisopdracht: Maken van entiteiten in je database door PostMapping
    @PostMapping("television")
    public ResponseEntity<String>addTelevisionsList(@RequestBody String television) {
        //Location: null, omdat je nog geen URI kunt returnen welke verwijst naar localhost 8080/television/1
        return ResponseEntity.created(null).body(television);
    }*/

    //  Bonusopdracht: Zorg dat je POST request een nieuwe string aan de lijst toevoegd. (denk bijvoorbeeld aan een merknaam of typenaam van een tv)
    @PostMapping("television")
    public ResponseEntity<String> addTelevision(@RequestBody String television) {
        //Voeg m.b.v. de add methode een television als String toe aan de lijst.
        if (television.length() > 20){
            throw new TvNameTooLongException("Name of the television is too long");
        }
        else {
            televisionDataBase.add(television);
            return new ResponseEntity<>("Television added successfully", HttpStatus.CREATED);
        }
    }

/*    Basisopdracht:
    @DeleteMapping("television/{id}")
    public ResponseEntity<String>deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }*/

    //Bonusopdracht: Zorg dat je DELETE request de string op positie x in de "database" verwijderd. (verwijderen kun je het best nabootsen door de waarde naar null wijzigen, net als bij PUT)
    @DeleteMapping("television/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable int id) {
        //Controleer of het opgegeven indexnummer (id) binnen de array voorkomt:
        if (id >= televisionDataBase.size() || id < 0) {
            //Als het buiten de array  valt, retourneer  een foutmelding in een ResponseEntity met statuscode BAD_REQUEST
            return new ResponseEntity<>("Invalid index", HttpStatus.BAD_REQUEST);
        }
        //Anders verwijder de stringwaarde op de positie id in de lijst door deze te wijzigen in null met behulp van de set-methode.
        //  Vervolgens return  een ResponseEntity met een bevestiging dat de televisie succesvol is verwijderd met statuscode OK.
        else {
            televisionDataBase.set(id, null);
            return new ResponseEntity<>("Television deleted successfully", HttpStatus.OK);
        }

    }

}





