package com.trymad.lootmarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class GameService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Category category;

    @ManyToOne
    User author;

    @Column(name = "brief_desc")
    String title;

    @Column(name = "detailed_desc")
    String text;

    Integer quanity;

    int price;
}
