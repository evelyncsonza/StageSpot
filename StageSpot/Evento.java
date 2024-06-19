import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Evento {
    protected String nome;
    protected String data;
    protected String horaInicio;
    protected String horaFim; // Novo atributo
    protected String tipo;
    protected String descricao;
    protected int classificacaoIndicativa;
    protected Usuario usuario;
    protected Local local;
    protected Artista artista;
    protected static Ingresso ingresso;

    protected static List<Evento> listaEventos = new ArrayList<>();

    public Evento(String nome, String data, String horaInicio, String horaFim, String tipo, String descricao, int classificacaoIndicativa, Usuario usuario, Local local, Artista artista, Ingresso ingresso) {
        this.nome = nome;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tipo = tipo;
        this.descricao = descricao;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.usuario = usuario;
        this.local = local;
        this.artista = artista;
        this.ingresso = ingresso;

        listaEventos.add(this);
    }

    public static void listarEventos(Scanner scanner) {
        if (listaEventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
        } else {
            System.out.println("------------------- Lista de Eventos -------------------");
            int index = 1;
            for (Evento evento : listaEventos) {
                System.out.println(index + ". " + evento.tipo + ": " + evento.nome);
                index++;
            }
            System.out.println("0. Voltar para o menu principal");

            System.out.print("Selecione um evento para ver detalhes ou 0 para voltar: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao > 0 && opcao <= listaEventos.size()) {
                Evento eventoSelecionado = listaEventos.get(opcao - 1);
                eventoSelecionado.exibirDetalhes(); //chamada polimórfica
            }
        }
    }

    public abstract void cancelarEvento();

    public abstract void alterarData(String novaData);

    public abstract void alterarHora(String novaHora);

    protected void exibirDetalhes() {
        System.out.println("\nTipo do evento: " + tipo);
        System.out.println("Nome: " + nome);
        System.out.println("Data: " + data);
        System.out.println("Hora de Início: " + horaInicio);
        System.out.println("Hora de Término: " + horaFim);
        System.out.println("Descrição: " + descricao);
        System.out.println("Classificação Indicativa: " + classificacaoIndicativa + " anos");
        System.out.println("Local: " + local.getNome());
        System.out.println("Endereço: " + local.getEndereco());
        System.out.println("Tipo de Local: " + local.getTipo());
        System.out.println("Capacidade do Local: " + local.getCapacidade() + " pessoas");
    }

    public abstract int calcularDuracao();

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }
}