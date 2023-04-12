package com.webshop.Webshop.Backend.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Accessors(chain = true, fluent = true)
@Getter
@Setter
@Entity
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(unique = true)
    public String token;

    public String tokenType = "BEARER";

    public boolean revoked;

    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserEntity user;

}
