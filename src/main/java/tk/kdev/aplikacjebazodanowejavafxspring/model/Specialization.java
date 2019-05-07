package tk.kdev.aplikacjebazodanowejavafxspring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Specialization {
    @Id
    @GeneratedValue
    private Long id;

    private String specialization;

    public Specialization(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
