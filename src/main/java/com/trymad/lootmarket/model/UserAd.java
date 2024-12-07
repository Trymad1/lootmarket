package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "services")
public class UserAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_category_id", nullable = false)
    Category category;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    User author;

    @Column(name = "brief_desc", nullable = false)
    String title;

    @Column(name = "detailed_desc")
    String text;

    Integer quantity;

    @Column(nullable = false)
    Integer price;

    LocalDateTime createDate;

    LocalDateTime updateDate;

    @OneToMany(mappedBy = "service", cascade = CascadeType.MERGE)
    private List<Deal> deals = new ArrayList<>();
}
