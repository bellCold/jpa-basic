package org.jpa;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "ORDERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Order(Long id, OrderStatus orderStatus, Member member) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.member = member;
    }
}
