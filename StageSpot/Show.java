import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Show extends Evento {
    private static Set<Show> listaShows = new HashSet<>();


    private String horaFim;


    public Show(String nome, String data, String horaInicio, String horaFim, String descricao, int classificacaoIndicativa, Usuario usuario, Local local, Artista artista, Ingresso ingresso) {
        super(nome, data, horaInicio, horaFim, "Show", descricao, classificacaoIndicativa, usuario, local, artista, ingresso);
        this.horaFim = horaFim;
        listaShows.add(this);
    }

    @Override
    public void cancelarEvento() {
    }

    @Override
    public void alterarData(String novaData) {
        this.data = novaData;
    }

    @Override
    public void alterarHora(String novaHora) {
        this.horaFim = novaHora;
    }

    // Implementação do método calcularDuracao para Show
    @Override
    public int calcularDuracao() {
        return calcularDiferencaHoras(horaInicio, horaFim);
    }

    // Método para calcular a diferença de horas entre duas strings de hora
    private int calcularDiferencaHoras(String horaInicio, String horaFim) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date dateInicio = format.parse(horaInicio);
            Date dateFim = format.parse(horaFim);

            long diferencaMillis = dateFim.getTime() - dateInicio.getTime();
            int diferencaMinutos = (int) (diferencaMillis / (60 * 1000));
            return diferencaMinutos / 60; // Retorna a diferença em horas
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void cadastrarShow(Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            Usuario usuario = new Usuario("Nome do Usuário", "email@exemplo.com", "123");

            Artista artista = null;
            if (!Artista.listaArtistas.isEmpty()) {
                Artista.listarArtistas();
                System.out.println("\n1 - Escolher Artista");
                System.out.println("2 - Cadastrar Novo Artista");
                int opcaoArtista = scanner.nextInt();
                scanner.nextLine();

                if (opcaoArtista == 1) {
                    System.out.print("Digite o número do Artista: ");
                    int numeroArtista = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroArtista > 0 && numeroArtista <= Artista.listaArtistas.size()) {
                        artista = Artista.listaArtistas.get(numeroArtista - 1);
                    } else {
                        System.out.println("Número inválido. Tentando novamente.");
                        continue;
                    }
                } else if (opcaoArtista == 2) {
                    artista = Artista.cadastrarNovoArtista(scanner);
                } else {
                    System.out.println("Opção inválida. Tentando novamente.");
                    continue;
                }
            } else {
                artista = Artista.cadastrarNovoArtista(scanner);
            }

            Local local = null;
            if (!Local.listaLocais.isEmpty()) {
                Local.listarLocais();
                System.out.println("\n1 - Escolher Local");
                System.out.println("2 - Cadastrar Novo Local");
                int opcaoLocal = scanner.nextInt();
                scanner.nextLine();

                if (opcaoLocal == 1) {
                    System.out.print("Digite o número do Local: ");
                    int numeroLocal = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroLocal > 0 && numeroLocal <= Local.listaLocais.size()) {
                        local = Local.listaLocais.get(numeroLocal - 1);
                    } else {
                        System.out.println("Número inválido. Tentando novamente.");
                        continue;
                    }
                } else if (opcaoLocal == 2) {
                    local = cadastrarNovoLocal(scanner);
                } else {
                    System.out.println("Opção inválida. Tentando novamente.");
                    continue;
                }
            } else {
                local = cadastrarNovoLocal(scanner);
            }

            System.out.println("\n-------------------Cadastro de Ingresso-------------------");
            System.out.print("Tipos de Ingresso (ex: meia entrada, inteira...): ");
            String tipoIngresso = scanner.nextLine();
            System.out.print("Setores disponíveis (ex: pista, área vip, camarote...): ");
            String setor = scanner.nextLine();
            System.out.print("Preços a partir de: ");
            double preco = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Site de Compra: ");
            String siteCompra = scanner.nextLine();
            System.out.print("Cupom de Desconto: ");
            String cupomDesconto = scanner.nextLine();

            Ingresso ingresso = new Ingresso(tipoIngresso, setor, preco, siteCompra, cupomDesconto);

            System.out.println("\n-------------------Cadastro de Show-------------------");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Data: ");
            String data = scanner.nextLine();
            System.out.print("Hora de Início: ");
            String horaInicio = scanner.nextLine();
            System.out.print("Hora de Término: ");
            String horaFim = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Classificação Indicativa: ");
            int classificacaoIndicativa = scanner.nextInt();
            scanner.nextLine();

            Show novoShow = new Show(nome, data, horaInicio, horaFim, descricao, classificacaoIndicativa, usuario, local, artista, ingresso);
            listaShows.add(novoShow);

            System.out.println("\nShow cadastrado com sucesso!!");
            System.out.println("1 - Cadastrar um novo show");
            System.out.println("2 - Voltar para a página inicial");
            System.out.println("0 - Encerrar o programa");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                continuar = false;
            } else if (opcao == 1) {
                Show.cadastrarShow(scanner);
            } else if (opcao == 2) {
                continuar = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void listarShows() {
        if (listaShows.isEmpty()) {
            System.out.println("Nenhum show cadastrado.");
        } else {
            System.out.println("-------------------Lista de Shows-------------------");
            for (Show show : listaShows) {
                int duracao = show.calcularDuracao();
                System.out.println("Nome: " + show.getNome() + ", Data: " + show.getData() + ", Hora de Início: " + show.getHoraInicio() + ", Hora de Término: " + show.getHoraFim()
                        + ", Duração: " + duracao + " horas"
                        + ", Artista: " + show.getArtista().getNome() + ", Preço do Ingresso a partir de: " + show.getIngresso().getPreco()
                        + ", Local: " + show.getLocal().getNome());
            }
        }
    }

    @Override
    protected void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("------ Detalhes do Show ------");
        System.out.println("Duração: " + calcularDuracao() + " horas");
        System.out.println("Artista: " + getArtista().getNome());
        System.out.println("Ingresso:");
        System.out.println("Tipos de ingresso: " + ingresso.getTipo());
        System.out.println("Setores disponíveis: " + ingresso.getSetor());
        System.out.println("Preço do ingresso a partir de: " + ingresso.getPreco());
        System.out.println("Site de Compra: " + ingresso.getSiteCompra());
        System.out.println("Cupom de Desconto: " + ingresso.getCupomDesconto());
    }


    public Artista getArtista() {
        return artista;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public Local getLocal() {
        return local;
    }

    public String getHoraFim() {
        return horaFim;
    }

    private static Local cadastrarNovoLocal(Scanner scanner) {
        System.out.println("\n-------------------Cadastro de Local-------------------");
        System.out.print("Nome do Local: ");
        String nomeLocal = scanner.nextLine();
        System.out.print("Endereço: ");
        String enderecoLocal = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidadeLocal = scanner.nextLine();
        System.out.print("Estado: ");
        String estadoLocal = scanner.nextLine();
        System.out.print("País: ");
        String paisLocal = scanner.nextLine();
        System.out.print("Capacidade: ");
        int capacidadeLocal = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo de Local: ");
        System.out.println("1 - Teatro");
        System.out.println("2 - Estádio");
        System.out.println("3 - Shopping");
        System.out.println("4 - Casa de Show");
        System.out.println("5 - Parque");
        System.out.println("6 - Auditório");
        int tipoLocalOpcao = scanner.nextInt();
        scanner.nextLine();

        String tipoLocal;
        switch (tipoLocalOpcao) {
            case 1:
                tipoLocal = "Teatro";
                break;
            case 2:
                tipoLocal = "Estádio";
                break;
            case 3:
                tipoLocal = "Shopping";
                break;
            case 4:
                tipoLocal = "Casa de Show";
                break;
            case 5:
                tipoLocal = "Parque";
                break;
            case 6:
                tipoLocal = "Auditório";
                break;
            default:
                System.out.println("Opção inválida. Definindo como 'Outro'.");
                tipoLocal = "Outro";
        }

        Local local = new Local(nomeLocal, enderecoLocal, cidadeLocal, estadoLocal, paisLocal, capacidadeLocal, tipoLocal);
        return local;
    }
}