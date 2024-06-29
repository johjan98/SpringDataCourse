package com.course.springdata.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class OrderItemPK implements Serializable {

    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK orderItemPK = (OrderItemPK) o;
        return Objects.equals(idItem, orderItemPK.idItem) && Objects.equals(idOrder, orderItemPK.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idOrder);
    }
}
