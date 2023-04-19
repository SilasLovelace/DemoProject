package backend.Kwote.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Trades")
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@Getter
@Setter
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tradeId;
    String trade;
    Boolean isValid = false;
    @OneToMany (mappedBy = "tradeEntity")
    List <ProjectEntity> projectEntityList = new ArrayList<>();
}
