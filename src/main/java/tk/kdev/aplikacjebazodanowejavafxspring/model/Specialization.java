package tk.kdev.aplikacjebazodanowejavafxspring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "specializations")
    private Set<User> user = new HashSet<>();

    public Specialization(String specialization){
        this.specialization = specialization;
    }

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

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
