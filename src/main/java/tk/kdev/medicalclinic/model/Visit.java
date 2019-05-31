package tk.kdev.medicalclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

@Entity
@Table(name = "visit")
public class Visit {

    public Visit(LocalDate visitDate, LocalTime visitTime, User patient, User doctor) {
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Visit() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visit")
    private Long id;

    @Column(name = "visitDate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate visitDate;

    @Column(name = "visitTime")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime visitTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id_user")
    private User patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id_user")
    private User doctor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalTime visitTime) {
        this.visitTime = visitTime;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", visitTime=" + visitTime +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
