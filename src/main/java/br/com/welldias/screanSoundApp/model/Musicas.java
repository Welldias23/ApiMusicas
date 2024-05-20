package br.com.welldias.screanSoundApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeMusica;
    @ManyToOne
    private Artista artista;

    public Musicas() {}
    public Musicas(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Musica -'" + nomeMusica + '\'' +
                ", Artista -" + artista.getNome();
    }
}
