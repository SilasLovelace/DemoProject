package com.webshop.Webshop.Backend.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
@Entity
@Table(name="Addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
@Getter
@Setter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    private String street;
    private String number;
    private String zip;
    private String city;
    private String country;

    @OneToMany (mappedBy = "invoiceAddressEntity")
    private List <BasketEntity> basketInvoiceEntityList;
    @OneToMany (mappedBy = "deliveryAddressEntity")
    private List <BasketEntity> basketDeliveryEntityList;

}
