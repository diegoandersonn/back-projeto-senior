package com.example.projeto_spring.domain.time;

import com.example.projeto_spring.dto.time.DtoAtualizarTime;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "times")
@Entity(name = "Time")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String estadio;
    private Double saldoTransferencias;
    private Long nacionalidadeId;

    public Time(@Valid DtoCadastroTime dto) {
        this.nome = dto.nome();
        this.estadio = dto.estadio();
        this.saldoTransferencias = dto.saldoTransferencias();
        this.nacionalidadeId = dto.nacionalidadeId();
    }

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
