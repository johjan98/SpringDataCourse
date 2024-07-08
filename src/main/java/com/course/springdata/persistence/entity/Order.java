package com.course.springdata.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pizza_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer", nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime date;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false, columnDefinition = "character(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    //Eager means that when we want to get the Order we also want to get the Item
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @OrderBy("price DESC")
    private List<OrderItem> items;

    //Lazy is used when we don't want to get the information about this relation.
    // To access to this information we need use some method to do that. Ex: orderItem.getCustomer.getName()
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
    @JsonIgnore
    private Customer customer;
}
