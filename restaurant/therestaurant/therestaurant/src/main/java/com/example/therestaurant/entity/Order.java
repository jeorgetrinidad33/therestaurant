package com.example.therestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id_id")
    private User customer_id;

    @Column(nullable = false)
    private Timestamp order_date;

    @Column(nullable = false)
    private Float total_amount;

    private Date payment_date;

    private String payment_type;

    private Date estimated_delivery_time;

    private Date delivered_time;

    private enum isDelivered { Processing, OnTheWay, Delivered }
    @Enumerated
    private isDelivered order_status = isDelivered.Processing;
}
