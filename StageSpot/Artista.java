import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Artista {
    protected String nome;
    protected String genero;
    protected String descricao;
    protected String instagram;

    public static List<Artista> listaArtistas = new ArrayList<>();

    public Artista(String nome, String genero, String descricao, String instagram) {
        this.nome = nome;
        this.genero = genero;
        this.descricao = descricao;
        this.instagram = instagram;
        listaArtistas.add(this);
        salvarArtistaNoArquivo();
    }

    // Método para exibir detalhes do artista
    protected void exibirDetalhesArtista() {
        System.out.println("Nome: " + nome);
        System.out.println("Gênero: " + genero);
        System.out.println("Descrição: " + descricao);
        System.out.println("Instagram: " + instagram);
    }

    public String getNome() {
        return nome;
    }

    // Método estático para listar todos os artistas
    public static void listarArtistas() {
        if (listaArtistas.isEmpty()) {
            System.out.println("Nenhum artista cadastrado.");
        } else {
            System.out.println("-------------------Lista de Artistas-------------------");
            for (int i = 0; i < listaArtistas.size(); i++) {
                Artista artista = listaArtistas.get(i);
                System.out.println((i + 1) + ". " + artista.nome);
            }
        }
    }

    // Método estático para cadastrar um novo artista
    public static Artista cadastrarNovoArtista(Scanner scanner) {
        System.out.println("\n-------------------Cadastro de Novo Artista-------------------");
        System.out.print("Nome do Artista: ");
        String nomeArtista = scanner.nextLine();
        System.out.print("Gênero: ");
        String generoArtista = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricaoArtista = scanner.nextLine();
        System.out.print("Instagram: ");
        String instagramArtista = scanner.nextLine();

        return new Artista(nomeArtista, generoArtista, descricaoArtista, instagramArtista);
    }

    // Método para salvar um artista no arquivo
    private void salvarArtistaNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("artistas.txt", true))) {
            writer.write(this.nome + "," + this.genero + "," + this.descricao + "," + this.instagram);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o artista no arquivo: " + e.getMessage());
        }
    }

    // Método estático para exibir artistas do arquivo
    public static void exibirArtistasDoArquivo() {
        File file = new File("artistas.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo 'artistas.txt' criado.");
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo 'artistas.txt': " + e.getMessage());
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("artistas.txt"))) {
            String line;
            System.out.println("-------------------Lista de Artistas-------------------");
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(",");
                if (dados.length == 4) {  // Verifica se a linha tem 4 elementos
                    System.out.println("Nome: " + dados[0]);
                    System.out.println("Gênero: " + dados[1]);
                    System.out.println("Descrição: " + dados[2]);
                    System.out.println("Instagram: " + dados[3]);
                    System.out.println();
                } else {
                    System.out.println("Linha inválida: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de artistas: " + e.getMessage());
        }
    }
}