package backend.Kwote.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name="Projects")
@Accessors(chain = true, fluent = true)
@NoArgsConstructor
@Getter
@Setter
public class ProjectEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer projectId;
    String projectName;
    String projectText;
    @ManyToOne
    @JoinColumn (name = "Trade")
    TradeEntity tradeEntity;
    Boolean isFinished = false;
}
