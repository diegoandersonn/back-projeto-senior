package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.nacionalidade.DtoCadastroNacionalidade;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "nacionalidades")
@Entity(name = "Nacionalidade")
@Getter
@Setter
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
    private String continente;

    public Nacionalidade(DtoCadastroNacionalidade dto) {
        this.nome = dto.nome();
        this.imagemBandeira = dto.imagemBandeira();
        this.sigla = dto.sigla();
        this.continente = dto.continente();
    }
}
