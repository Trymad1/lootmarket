package com.trymad.lootmarket.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private Category service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_system_id")
    private PaymentSystem paymentSystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_status_id")
    private DealStatusEntity dealStatus;

    @Column(name = "quantity_purchased")
    private Integer buyedQuantity;

    private LocalDateTime dealStart;

    private LocalDateTime dealEnd;

    @OneToMany(mappedBy = "deal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

}