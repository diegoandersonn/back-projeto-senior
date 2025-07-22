package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.time.DtoAtualizarTime;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.UUID;

@Table(name = "times")
@Entity(name = "Time")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String estadio;
    private Double saldoTransferencias;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "nacionalidade_id")
    private Nacionalidade nacionalidade;

//    public Time(@Valid DtoCadastroTime dto) {
//        this.nome = dto.nome();
//        this.estadio = dto.estadio();
//        this.saldoTransferencias = dto.saldoTransferencias();
//        this.usuarioId = dto.usuarioId();
//        this.nacionalidadeId = dto.nacionalidadeId();
//    }

    public void atualizar(@Valid DtoAtualizarTime dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.estadio() != null) {
            this.estadio = dto.estadio();
        }
        if (dto.saldoTransferencias() != null) {
            this.saldoTransferencias = dto.saldoTransferencias();
        }
    }

    public void excluir() {

    }
}
