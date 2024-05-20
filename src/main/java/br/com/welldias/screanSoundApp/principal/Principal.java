package br.com.welldias.screanSoundApp.principal;

import br.com.welldias.screanSoundApp.model.Artista;
import br.com.welldias.screanSoundApp.model.Musicas;
import br.com.welldias.screanSoundApp.model.TipoArtista;
import br.com.welldias.screanSoundApp.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;

    public Principal(ArtistaRepository repository) {
        this.repositorio = repository;
    }

    public void exibemenu() {
        var opcao = -1;
        Scanner leitura = new Scanner(System.in);

    while (opcao != 9) {
        var menu = """
                ***Screan Sound Music***
                
                1- Cadastrar Artistas 
                2- Cadastrar Musicas
                3- Listar Musicas 
                4- Buscars musicas por artista
                5- Pesquisar dados sobre um artista
                
                
                9- Sair
                """;

        System.out.println(menu);
        opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao) {
            case 1:
                cadastrarArtista();
                break;
            case 2:
                cadastrarMusica();
                break;
            case 3:
                listarMusica();
                break;
            case 4:
                buscarMusicaPorArtista();
                break;
            case 5:
                pesquisarDadosSobreArtista();
                break;
            case 9:
                System.out.println("Aplicacao encerrada!");
                break;
            default:
                System.out.println("Opcao invalida");
          
        }
    }
    }

    private void pesquisarDadosSobreArtista() {
    }

    private void buscarMusicaPorArtista() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Qual artista voce quer buscar musicas");
        var nome = leitura.nextLine();
        List<Musicas> musicas = repositorio.buscarmusicaPorArtista(nome);
        musicas.forEach(System.out::println);
        
    }

    private void listarMusica() {
       List<Artista> listaMusicas = repositorio.findAll();
        listaMusicas.forEach(a -> a.getMusicas().forEach(System.out::println));
        
    }

    private void cadastrarMusica() {
        Scanner leitura = new Scanner(System.in);
        System.out.println("Voce deseja cadastrar musica de qual artista:");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if (artista.isPresent()) {
            System.out.println("Informe o tituo da musica:");
            var nomeMusica = leitura.nextLine();
            Musicas musica = new Musicas(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("artista nao encontrado");
        }
        
    }

    private void cadastrarArtista() {
        Scanner leitura = new Scanner(System.in);

        var cadastrarnovo = "s";


    while (cadastrarnovo.equalsIgnoreCase("s")) {
        System.out.println("Informe o nome do artista.");
        var nome = leitura.nextLine();
        System.out.println("Informe o tipo desse artista: (Solo, dupla ou Banda)");
        var tipo = leitura.nextLine();

        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
        Artista artista = new Artista(nome, tipoArtista);
        repositorio.save(artista);
        System.out.println("Gostaria de cadastrar outro artista: S/N");
        cadastrarnovo = leitura.nextLine();
    }
    }
}
