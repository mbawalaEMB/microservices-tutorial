package com.emb.quiz_service.mapper;

import com.emb.quiz_service.model.Quiz;
import com.emb.quiz_service.model.dto.QuizDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MapStruct mapper interface for converting between {@link Quiz} and {@link QuizDto}.
 * Run mvn clean install, if the bean cannot be found when starting the application.
 * The maven command will generate a QuizMapperImpl class in target folder.
 */
@Mapper(componentModel = "spring")
public interface QuizMapper {

    /**
     * Converts a {@link QuizDto} DTO to a JPA {@link Quiz}.
     *
     * @param dto the DTO to convert
     * @return the mapped JPA entity
     */
    @Mapping(target = "id", ignore = true) // QuizDto does not have id
    @Mapping(target = "questionsIds", ignore = true) // QuizDto does not have questions
    Quiz toEntity(QuizDto dto);

    /**
     * Converts a {@link Quiz} JPA entity to a {@link QuizDto} DTO.
     *
     * @param entity the entity to convert
     * @return the mapped DTO
     */
    @Mapping(target = "category", ignore = true) // Quiz Entity does not have category
    @Mapping(target = "numberOfQuestions", ignore = true) // Quiz Entity does not have numberOfQuestions
    QuizDto toDto(Quiz entity);

}
