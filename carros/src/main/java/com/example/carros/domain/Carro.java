package com.example.carros.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
/*
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
 */
public class Carro {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    private String descricao;

    private String urlFoto;

    private String urlVideo;

    private String latitude;

    private String longitude;
/*
    public Carro(){
    }

    public Carro(Long id, String nome, String tipo){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    */
}
