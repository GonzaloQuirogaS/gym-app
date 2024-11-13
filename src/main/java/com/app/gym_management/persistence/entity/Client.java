package com.app.gym_management.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3)
    private String name;
    @NotBlank
    @Size(min = 3)
    private String surname;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;
    @NotNull
    private Long phone;
    @NotNull
    private LocalDateTime registerDate = LocalDateTime.now();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_discipline",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private Set<Discipline> discipline;

    @OneToMany(mappedBy = "client")
    private Set<Invoice> invoices;

    public Client() {
        this.discipline = new HashSet<>();
    }

    public Client(String name, String surname, Integer age, String email, Long phone, LocalDateTime registerDate, Set<Invoice> invoices) {
        this();
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.registerDate = registerDate;
        this.invoices = invoices;
    }

    public void addDiscipline(Discipline discipline) {
        this.discipline.add(discipline);
        discipline.getClient().add(this);
    }

    public void addInvoice(Invoice invoices) {
        this.invoices.add(invoices);
        invoices.setClient(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(age, client.age) && Objects.equals(email, client.email) && Objects.equals(phone, client.phone) && Objects.equals(registerDate, client.registerDate) && Objects.equals(invoices, client.invoices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, phone, registerDate, invoices);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", registerDate=" + registerDate +
                ", invoices=" + invoices +
                '}';
    }
}