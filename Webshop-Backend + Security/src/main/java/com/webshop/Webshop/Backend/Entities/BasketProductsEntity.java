package com.webshop.Webshop.Backend.Entities;

import com.webshop.Webshop.Backend.enums.ShippingEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name="Basketproducts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class BasketProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purchaseId;
    @ManyToOne
    @JoinColumn (name = "basketId")
    private BasketEntity basketEntity;
    @ManyToOne
    @JoinColumn (name = "productId")
    private ProductEntity productEntity;

    private int amount;
    private String remark;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

}
