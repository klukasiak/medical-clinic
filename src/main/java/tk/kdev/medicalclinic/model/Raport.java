package tk.kdev.medicalclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "raport")
public class Raport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raport")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "dateRaport")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateRaport;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "raports")
    private Set<User> user = new HashSet<>();

    public Raport() {

    }

    public Raport(String description, LocalDate dateRaport) {
        this.description = description;
        this.dateRaport = dateRaport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public LocalDate getDateRaport() {
        return dateRaport;
    }

    public void setDateRaport(LocalDate dateRaport) {
        this.dateRaport = dateRaport;
    }

    @Override
    public String toString() {
        return "Raport{" +
                ", description='" + description + '\'' +
                ", dateRaport=" + dateRaport +
                '}';
    }

    @PreRemove
    private void deleteRaportFromUser(){
        for(User u : user){
            u.getRaports().remove(this);
        }
    }
}
