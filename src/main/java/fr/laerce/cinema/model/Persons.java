package fr.laerce.cinema.model;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

//attention cette classe est un bean persistant(nom de classe = nom de table)
@Entity
@Table(name = "persons")
public class Persons {
    private long id;
    private String surname;
    private String givenname;
    private Integer birthYear;
    private String imagePath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 60)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "givenname", nullable = true, length = 40)
    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    @Basic
    @Column(name = "birth_year", nullable = true)
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Basic
    @Column(name = "image_path", nullable = true, length = 80)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persons persons = (Persons) o;

        if (id != persons.id) return false;
        if (surname != null ? !surname.equals(persons.surname) : persons.surname != null) return false;
        if (givenname != null ? !givenname.equals(persons.givenname) : persons.givenname != null) return false;
        if (birthYear != null ? !birthYear.equals(persons.birthYear) : persons.birthYear != null) return false;
        if (imagePath != null ? !imagePath.equals(persons.imagePath) : persons.imagePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (givenname != null ? givenname.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
