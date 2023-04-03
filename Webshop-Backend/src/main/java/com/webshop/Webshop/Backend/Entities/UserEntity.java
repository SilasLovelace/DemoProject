package com.webshop.Webshop.Backend.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="Users")
@Builder
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    @OneToMany (mappedBy = "userEntity")
    private List <BasketEntity> basketEntityList;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

}
