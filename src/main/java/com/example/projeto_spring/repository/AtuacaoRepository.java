package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Atuacao;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AtuacaoRepository extends JpaRepository<Atuacao, UUID> {
    List<Atuacao> findByJogadorId(UUID jogadorId);

    @Query(value = """
                SELECT a.* FROM atuacoes a
                JOIN partidas p ON a.partida_id = p.id
                WHERE p.time_id = :timeId
            """, nativeQuery = true)
    List<Atuacao> findByTimeId(UUID timeId);

    Boolean existsByJogadorIdAndPartidaId(@NotNull UUID uuid, @NotNull UUID uuid1);
}
