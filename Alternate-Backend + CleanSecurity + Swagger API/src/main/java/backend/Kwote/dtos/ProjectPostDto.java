package backend.Kwote.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProjectPostDto {
    Integer projectId;
    String projectName;
    String projectText;
    Integer tradeId;
    String trade;
    Boolean isFinished;
}
