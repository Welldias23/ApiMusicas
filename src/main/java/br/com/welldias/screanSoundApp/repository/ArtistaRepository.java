package br.com.welldias.screanSoundApp.repository;

import br.com.welldias.screanSoundApp.model.Artista;
import br.com.welldias.screanSoundApp.model.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musicas> buscarmusicaPorArtista(String nome);
}
