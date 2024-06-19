import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Festival extends Evento {
    private static List<Festival> listaFestivais = new ArrayList<>();
    public List<Artista> artistas;
    public List<String> patrocinadores;

    public Festival(String nome, String data, String horaInicio, String horaFim, String descricao, int classificacaoIndicativa, Usuario usuario, Local local, Ingresso ingresso, List<Artista> artistas, List<String> patrocinadores) {
        super(nome, data, horaInicio, horaFim, "Festival", descricao, classificacaoIndicativa, usuario, local, artistas.isEmpty() ? null : artistas.get(0), ingresso);
        this.artistas = artistas;
        this.patrocinadores = patrocinadores;
        listaFestivais.add(this);
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

    public static void cadastrarFestival(Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            Usuario usuario = new Usuario("Nome do Usuário", "email@exemplo.com", "123");

            System.out.println("\n-------------------Cadastro de Festival-------------------");
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

            System.out.println("\n-------------------Cadastro de Patrocinadores-------------------");
            List<String> patrocinadores = new ArrayList<>();
            while (true) {
                System.out.print("Nome do Patrocinador (ou 0 para finalizar): ");
                String patrocinador = scanner.nextLine();
                if (patrocinador.equals("0")) {
                    break;
                }
                patrocinadores.add(patrocinador);
            }

            List<Artista> artistas = new ArrayList<>();
            while (true) {
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
                            Artista artistaSelecionado = Artista.listaArtistas.get(numeroArtista - 1);
                            if (artistas.contains(artistaSelecionado)) {
                                System.out.println("Artista já adicionado. Escolha outro.");
                            } else {
                                artistas.add(artistaSelecionado);
                            }
                        } else {
                            System.out.println("Número inválido. Tentando novamente.");
                            continue;
                        }
                    } else if (opcaoArtista == 2) {
                        Artista novoArtista = Artista.cadastrarNovoArtista(scanner);
                        if (artistas.contains(novoArtista)) {
                            System.out.println("Artista já adicionado. Escolha outro.");
                        } else {
                            artistas.add(novoArtista);
                        }
                    } else {
                        System.out.println("Opção inválida. Tentando novamente.");
                        continue;
                    }
                } else {
                    Artista novoArtista = Artista.cadastrarNovoArtista(scanner);
                    if (artistas.contains(novoArtista)) {
                        System.out.println("Artista já adicionado. Escolha outro.");
                    } else {
                        artistas.add(novoArtista);
                    }
                }

                System.out.print("Deseja adicionar mais um artista? (s/n): ");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("n")) {
                    break;
                }
            }

            Festival novoFestival = new Festival(nome, data, horaInicio, horaFim, descricao, classificacaoIndicativa, usuario, local, ingresso, artistas, patrocinadores);
            listaFestivais.add(novoFestival);

            System.out.println("\nFestival cadastrado com sucesso!!");
            System.out.println("1 - Cadastrar um novo festival");
            System.out.println("2 - Voltar para a página inicial");
            System.out.println("0 - Encerrar o programa");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                continuar = false;
            } else if (opcao == 1) {
                Festival.cadastrarFestival(scanner);
            } else if (opcao == 2) {
                continuar = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void listarFestivais(Scanner scanner) {
        listarEventos(scanner); // Chamada polimórfica do método listarEventos
    }

    // Método sobrescrito para exibir detalhes específicos do festival
    @Override
    protected void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("------ Detalhes do Festival ------");
        System.out.println("ARTISTAS:");
        for (Artista artista : artistas) {
            System.out.println("- " + artista.getNome());
        }
        System.out.println("PATROCINADORES:");
        for (String patrocinador : patrocinadores) {
            System.out.println("- " + patrocinador);
        }
        System.out.println("INGRESSO:");
        System.out.println("Tipos de ingresso: " + ingresso.getTipo());
        System.out.println("Setores disponíveis: " + ingresso.getSetor());
        System.out.println("Preço a partir de: " + ingresso.getPreco());
        System.out.println("Site de Compra: " + ingresso.getSiteCompra());
        System.out.println("Cupom de Desconto: " + ingresso.getCupomDesconto());
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