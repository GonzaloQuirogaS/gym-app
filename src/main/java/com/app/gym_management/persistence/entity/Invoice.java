package com.app.gym_management.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private String discipline;
    private LocalDateTime createdTime;

    private Integer total;

    @ManyToOne
    @JsonIgnore
    private Client client;

    public Invoice() {
    }

    public Invoice(Long id, String number, String discipline, LocalDateTime createdTime, Integer total, Client client) {
        this.id = id;
        this.number = number;
        this.discipline = discipline;
        this.createdTime = createdTime;
        this.total = total;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(number, invoice.number) && Objects.equals(discipline, invoice.discipline) && Objects.equals(createdTime, invoice.createdTime) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discipline, createdTime, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", discipline='" + discipline + '\'' +
                ", createdTime=" + createdTime +
                ", total=" + total +
                '}';
    }
}
