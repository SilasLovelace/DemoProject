package com.webshop.Webshop.Backend.enums;

import jakarta.persistence.Enumerated;

public enum ShippingEnum {
    delivery("Lieferung"),
    pickup("Abholung");
    String label;

    ShippingEnum(String label) {
        this.label = label;
    }
}
