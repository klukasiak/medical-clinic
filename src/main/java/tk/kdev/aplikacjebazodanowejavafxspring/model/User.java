package tk.kdev.aplikacjebazodanowejavafxspring.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id_role")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
                CascadeType.MERGE
        })
    @JoinTable(name = "user_specializations",
        joinColumns = {@JoinColumn (name = "id_user")},
        inverseJoinColumns = { @JoinColumn (name = "id_specialization")})
    private Set<Specialization> specializations;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_addresses",
            joinColumns = {@JoinColumn (name = "id_user")},
            inverseJoinColumns = { @JoinColumn (name = "id_address")})
    private Set<Address> addresses;

    public User(String username, String password, Role role, String firstName, String lastName, String pesel, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
}

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", specializations=" + specializations +
                '}';
    }
}
