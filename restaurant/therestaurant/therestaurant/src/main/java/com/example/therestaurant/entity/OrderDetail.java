package com.example.therestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id_id")
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "menu_id_id")
    private Menu menu_id;

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Integer qty;

    @Column(nullable = false)
    private Float total_amount;
}
