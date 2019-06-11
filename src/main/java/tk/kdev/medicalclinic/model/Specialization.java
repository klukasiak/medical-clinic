package tk.kdev.medicalclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_specialization")
    private Long id;

    @Column(name = "specialization")
    private String specialization;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "specializations")
    private Set<User> user = new HashSet<>();

    public Specialization(String specialization) {
        this.specialization = specialization;
    }

    public Specialization() {

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

    public void addUser(User u){
        this.user.add(u);
        u.getSpecializations().add(this);
    }

    @Override
    public String toString() {
        return specialization;
    }

    @PreRemove
    private void deleteSpecializationFromUser(){
        for(User u : user){
            u.getSpecializations().remove(this);
        }
    }
}
