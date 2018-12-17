package fr.laerce.cinema.web;


import fr.laerce.cinema.dao.PersonsDao;

import fr.laerce.cinema.model.Persons;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

//pour dire a springboot qu'il est un controller web on écrit cette phrase
@Controller
public class MainController {
    //on peut utiliser cette méthode avec autowired et component dans le servlet DataModel
    @Autowired
    PersonsDao personDao;


    //Pour mapper la servlet,ça remplace ce que l'on met dans web.xml.
    @GetMapping("/")
    public String main(Model M) {
        //on ajoute a l'objet model la clef nom et karl
        M.addAttribute ("nom", "karl");
        M.addAttribute ("actor", personDao.getAll ());
        M.addAttribute ("persons",new Persons () );
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }
    @GetMapping("/modifier/{id}")
    public String modifier(Model M,@PathVariable("id")long id) {
        M.addAttribute ("acteur", personDao.getById (id) );
        //on return la chaine string index de façon à ouvrir index.html
        return "index";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String ajoute(@Valid Persons p) {
        personDao.save (p);
        return "ajout";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        personDao.delete (id);
        //on return la chaine string index de façon à ouvrir index.html
        return "delete";
    }

    @Value("${url2}")
    private String url2;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImageAsResponseEntity2(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename = url2 + id;
            File i = new File (filename);
            FileInputStream in = new FileInputStream (i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache ().getHeaderValue ());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return null;
    }
}
