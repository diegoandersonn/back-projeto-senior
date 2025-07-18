package com.example.projeto_spring.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class JogadorRepositoryTest {

    @Test
    void findByTimeId() {
    }

    @Test
    void existsByTimeIdAndNumeroCamisa() {
    }

    @Test
    void findByTimeIdAndNumeroCamisa() {
    }
}