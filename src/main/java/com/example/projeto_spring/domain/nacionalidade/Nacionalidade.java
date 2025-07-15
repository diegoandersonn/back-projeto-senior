package com.example.projeto_spring.domain.nacionalidade;

import com.example.projeto_spring.dto.nacionalidade.DtoCadastroNacionalidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "nacionalidades")
@Entity(name = "Nacionalidade")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nacionalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "imagem")
    private String imagemBandeira;
    private String sigla;

    public Nacionalidade(DtoCadastroNacionalidade dto) {
        this.nome = dto.nome();
        this.imagemBandeira = dto.imagemBandeira();
        this.sigla = dto.sigla();
    }
}
