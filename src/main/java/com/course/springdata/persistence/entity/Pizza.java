package com.course.springdata.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //This allows to get dates about creation or modification
@Entity
@Table(name = "pizza")
public class Pizza extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "smallint")
    private Boolean vegetarian;

    @Column(columnDefinition = "smallint")
    private Boolean vegan;

    @Column(columnDefinition = "smallint", nullable = false)
    private Boolean available;
}
