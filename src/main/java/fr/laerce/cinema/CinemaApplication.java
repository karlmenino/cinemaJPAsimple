package fr.laerce.cinema;

import fr.laerce.cinema.dao.PersonsDao;
import fr.laerce.cinema.model.Persons;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
    }

//    @Bean
//
//    public CommandLineRunner runner(PersonsDao dao){
//        return new CommandLineRunner() {
//            @Override
//            @Transactional
//            public void run(String... args) throws Exception {
//                List<Persons> persons = dao.getAll();
//                for (Persons p: persons
//                     ) {
//                    System.out.println(p.getSurname());
//                }
//                dao.delete (30);
//
//
//            }
//        };
//    }

}

