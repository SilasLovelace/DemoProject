package backend.Kwote.services;

import backend.Kwote.dtos.ProjectPostDto;
import backend.Kwote.entities.ProjectEntity;
import backend.Kwote.repositories.ProjectRepository;
import backend.Kwote.repositories.TradeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TradeCrudRepository tradeCrudRepository;

    public ProjectPostDto postProject (ProjectPostDto projectPostDto){

        ProjectEntity projectEntity = new ProjectEntity()
                .projectName(projectPostDto.getProjectName())
                .projectText(projectPostDto.getProjectText())
                .tradeEntity(tradeCrudRepository.findById(projectPostDto.getTradeId()).get());

        projectRepository.save(projectEntity);

                return projectDtoFromProjectEntity(projectEntity);
    }

    private ProjectPostDto projectDtoFromProjectEntity (ProjectEntity projectEntity){

        return ProjectPostDto.builder()
                .projectId(projectEntity.projectId())
                .projectName(projectEntity.projectName())
                .projectText(projectEntity.projectText())
                .tradeId(projectEntity.tradeEntity().tradeId())
                .trade(projectEntity.tradeEntity().trade())
                .isFinished(projectEntity.isFinished())
                .build();
    }
}
