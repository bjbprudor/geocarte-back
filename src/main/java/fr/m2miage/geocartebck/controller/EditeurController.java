package fr.m2miage.geocartebck.controller;

import fr.m2miage.geocartebck.dao.EditeurRepository;
import fr.m2miage.geocartebck.model.Editeur;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class EditeurController
{

    public static final Logger log = Logger.getLogger(EditeurController.class);

    @Autowired
    private EditeurRepository repo;


    // -------------------Recupere tous les Editeurs---------------------------------------------

    @RequestMapping(value = "/editeur/", method = RequestMethod.GET)
    public ResponseEntity<List<Editeur>> listAllEditeur()
    {
        List<Editeur> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un Editeur------------------------------------------

    @RequestMapping(value = "/editeur/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEditeur(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching Editeur with id {%s}", id);
        log.info(msg);
        Editeur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Editeur with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Editeur>(current, HttpStatus.OK);
    }

    // -------------------Create a Editeur-------------------------------------------

    @RequestMapping(value = "/editeur/", method = RequestMethod.POST)
    public ResponseEntity<?> createEditeur(@RequestBody Editeur target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating Editeur : {%s}", target);
        log.info(msg);

        Editeur current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A Editeur with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/editeur/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Editeur ------------------------------------------------

    @RequestMapping(value = "/editeur/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEditeur(@PathVariable("id") int id, @RequestBody Editeur target)
    {
        String msg = String.format("Updating Editeur with id {%s}",id);
        log.info(msg);

        Editeur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. Editeur with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setCode(target.getCode());
        current.setNom(target.getNom());
        repo.save(current);
        return new ResponseEntity<Editeur>(current, HttpStatus.OK);
    }

    // ------------------- Delete a Editeur-----------------------------------------

    @RequestMapping(value = "/editeur/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEditeur(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching & Deleting Editeur with id {%s}", id);
        log.info(msg);

        Editeur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. Editeur with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<Editeur>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Editeur-----------------------------

    @RequestMapping(value = "/editeur/", method = RequestMethod.DELETE)
    public ResponseEntity<Editeur> deleteAllEditeur()
    {
        log.info("Deleting All Editeur");
        repo.deleteAll();
        return new ResponseEntity<Editeur>(HttpStatus.NO_CONTENT);
    }


}