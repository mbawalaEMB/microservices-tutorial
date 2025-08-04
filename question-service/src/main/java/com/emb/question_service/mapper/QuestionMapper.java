package com.emb.question_service.mapper;

import com.emb.question_service.dto.QuestionWrapper;
import com.emb.question_service.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper interface for converting between {@link Question} and {@link QuestionWrapper}.
 * Run mvn clean install, if the bean cannot be found when starting the application.
 * The maven command will generate a QuestionMapperImpl class in target folder.
 */
@Mapper(componentModel = "spring")
public interface QuestionMapper {

    /**
     * Converts a {@link QuestionWrapper} DTO to a JPA {@link Question}.
     *
     * @param dto the DTO to convert
     * @return the mapped JPA entity
     */
    @Mapping(target = "rightAnswer", ignore = true)
    @Mapping(target = "difficultyLevel", ignore = true)
    @Mapping(target = "category", ignore = true)
    Question toEntity(QuestionWrapper dto);

    /**
     * Converts a {@link Question} JPA entity to a {@link QuestionWrapper} DTO.
     *
     * @param entity the entity to convert
     * @return the mapped DTO
     */
    QuestionWrapper toDto(Question entity);

}
