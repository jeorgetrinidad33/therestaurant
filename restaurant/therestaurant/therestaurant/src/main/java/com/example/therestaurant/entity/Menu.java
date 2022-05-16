package com.example.therestaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "menu_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Float price;

    private Blob image;

    public enum isAvailable { Available, NotAvailable }
    @Enumerated
    private isAvailable status = isAvailable.Available;

    public  Menu(Category category, String name, String description, Float price, Blob image, isAvailable status) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.status = status;
    }
}
