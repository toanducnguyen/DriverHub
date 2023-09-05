package com.drivehub.CartList.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="CartList")
public class CartList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long userId;
    @Column
    private long productId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private statusEnum statusEnum;

    public CartList(long userId, long productId, CartList.statusEnum statusEnum) {
        this.userId = userId;
        this.productId = productId;
        this.statusEnum = statusEnum;
    }

    public enum statusEnum {
        Pending,Paid,Cancelled;
    }
}
