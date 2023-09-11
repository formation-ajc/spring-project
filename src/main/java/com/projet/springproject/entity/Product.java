package com.projet.springproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private double price = 0;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category_id;
}
