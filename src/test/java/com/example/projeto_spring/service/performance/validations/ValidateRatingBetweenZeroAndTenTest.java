package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.infra.exception.RatingNotBetweenZeroAndTen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ValidateRatingBetweenZeroAndTenTest {
    @InjectMocks
    ValidateRatingBetweenZeroAndTen validator;

    public RegisterPerformanceDto dto;

    @BeforeEach
    public void setUp() {
        dto = new RegisterPerformanceDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), UUID.fromString("6b14a96d-c516-448f-adb2-170e16f8c760"), 8.0);
    }

    private static Stream<Arguments> provideInvalidRatings() {
        return Stream.of(
                Arguments.of(-0.1),
                Arguments.of(11.0),
                Arguments.of(-100000.0),
                Arguments.of(100000.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRatings")
    void validate_shouldThrowException_WhenRatingIsOutOfRange(Double ratingParams) {
        RegisterPerformanceDto testDto = new RegisterPerformanceDto(UUID.randomUUID(), UUID.randomUUID(), ratingParams);
        assertThrows(RatingNotBetweenZeroAndTen.class, () -> validator.validate(testDto));
    }

    private static Stream<Arguments> provideValidRatings() {
        return Stream.of(
                Arguments.of(0.1),
                Arguments.of(1.0),
                Arguments.of(10.0),
                Arguments.of(9.9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidRatings")
    void validate_shouldNotThrowException_WhenRatingIsBetweenZeroAndTen(Double ratingParams) {
        RegisterPerformanceDto testDto = new RegisterPerformanceDto(UUID.randomUUID(), UUID.randomUUID(), ratingParams);
        assertDoesNotThrow(() -> validator.validate(testDto));
    }
}