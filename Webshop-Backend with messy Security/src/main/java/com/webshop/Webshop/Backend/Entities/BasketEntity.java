package com.webshop.Webshop.Backend.Entities;

import com.webshop.Webshop.Backend.enums.ShippingEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name="Baskets")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "invoiceAddressId")
    private AddressEntity invoiceAddressEntity;

    @ManyToOne
    @JoinColumn(name = "deliveryAddressId")
    private AddressEntity deliveryAddressEntity;

    @ManyToOne
    @JoinColumn (name = "userId")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    private ShippingEnum shippingType;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;
    @OneToMany(mappedBy = "basketEntity")
    private List<BasketProductsEntity> basketProductsEntityList;



}
