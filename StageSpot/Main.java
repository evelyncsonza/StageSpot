import java.util.Scanner;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        boolean mostrarMenuPrincipal = true;

        while (continuar) {
            if (mostrarMenuPrincipal) {
                exibirMenuPrincipal();
                mostrarMenuPrincipal = false;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                // Salvar usuários antes de encerrar
                try {
                    Serializacao.salvarUsuarios(Usuario.getUsuariosCadastrados(), "usuarios.dat");
                    System.out.println("Usuários salvos com sucesso!");
                } catch (IOException e) {
                    System.out.println("Erro ao salvar usuários: " + e.getMessage());
                }
                continuar = false;
            } else if (opcao == 1) {
                try {
                    Usuario usuario = Usuario.cadastrarUsuario(scanner);
                    exibirOpcoesUsuario(usuario, scanner);
                } catch (UsuarioException e) {
                    System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                }
                mostrarMenuPrincipal = true;
            } else if (opcao == 2) {
                try {
                    Usuario usuarioLogado = Usuario.fazerLogin(scanner);
                    if (usuarioLogado != null) {
                        exibirOpcoesLogado(usuarioLogado, scanner);
                    }
                } catch (UsuarioException e) {
                    System.out.println("Erro ao fazer login: " + e.getMessage());
                }
                mostrarMenuPrincipal = true;
            } else if (opcao == 3) {
                Evento.listarEventos(scanner);
                mostrarMenuPrincipal = true;
            } else if (opcao == 4) {
                Artista.exibirArtistasDoArquivo();
                mostrarMenuPrincipal = true;
            } else if (opcao == 5) {
                Show.listarShows();
                mostrarMenuPrincipal = true;
            } else if (opcao == 6) {
                Festival.listarFestivais(scanner);
                mostrarMenuPrincipal = true;
            } else if (opcao == 7) {
                Encontro.listarEncontros();
                mostrarMenuPrincipal = true;
            } else {
                System.out.println("Opção inválida.");
                mostrarMenuPrincipal = true;
            }
        }

        scanner.close();
    }

    public static void exibirOpcoesUsuario(Usuario usuario, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("1 - Fazer login");
            System.out.println("2 - Exibir detalhes do usuário");
            System.out.println("3 - Salvar usuários");
            System.out.println("4 - Carregar usuários");
            System.out.println("5 - Voltar para o menu principal");
            System.out.println("0 - Encerrar o programa");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                try {
                    Serializacao.salvarUsuarios(Usuario.getUsuariosCadastrados(), "usuarios.dat");
                    System.out.println("Usuários salvos com sucesso!");
                } catch (IOException e) {
                    System.out.println("Erro ao salvar usuários: " + e.getMessage());
                }
                continuar = false;
            } else if (opcao == 1) {
                try {
                    Usuario usuarioLogado = Usuario.fazerLogin(scanner);
                    if (usuarioLogado != null) {
                        exibirOpcoesLogado(usuarioLogado, scanner);
                    }
                } catch (UsuarioException e) {
                    System.out.println("Erro ao fazer login: " + e.getMessage());
                }
            } else if (opcao == 2) {
                usuario.exibirDetalhesUsuario();
            } else if (opcao == 3) {
                try {
                    Serializacao.salvarUsuarios(Usuario.getUsuariosCadastrados(), "usuarios.dat");
                    System.out.println("Usuários salvos com sucesso!");
                } catch (IOException e) {
                    System.out.println("Erro ao salvar usuários: " + e.getMessage());
                }
            } else if (opcao == 4) {
                try {
                    List<Usuario> usuariosCarregados = Serializacao.carregarUsuarios("usuarios.dat");
                    Usuario.setUsuariosCadastrados(usuariosCarregados);
                    System.out.println("Usuários carregados com sucesso!");
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Erro ao carregar usuários: " + e.getMessage());
                }
            } else if (opcao == 5) {
                continuar = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void exibirOpcoesLogado(Usuario usuario, Scanner scanner) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("1 - Cadastrar um show");
            System.out.println("2 - Cadastrar um festival");
            System.out.println("3 - Cadastrar um encontro de fãs");
            System.out.println("4 - Voltar para a página de login");
            System.out.println("0 - Encerrar o programa");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                try {
                    Serializacao.salvarUsuarios(Usuario.getUsuariosCadastrados(), "usuarios.dat");
                    System.out.println("Usuários salvos com sucesso!");
                } catch (IOException e) {
                    System.out.println("Erro ao salvar usuários: " + e.getMessage());
                }
                continuar = false;
            } else if (opcao == 1) {
                System.out.println("-------------------Cadastro de Show-------------------");
                Show.cadastrarShow(scanner);
            } else if (opcao == 2) {
                Festival.cadastrarFestival(scanner);
            } else if (opcao == 3) {
                System.out.println("-------------------Cadastro de Encontro de Fãs-------------------");
                Encontro.cadastrarEvento(scanner);
            } else if (opcao == 4) {
                continuar = false;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    public static void exibirMenuPrincipal() {
        System.out.println("\nBem-vindo ao StageSpot");
        System.out.println("1 - Cadastrar um usuário");
        System.out.println("2 - Fazer login");
        System.out.println("3 - Listar agenda de eventos");
        System.out.println("4 - Listar os artistas");
        System.out.println("5 - Listar Shows");
        System.out.println("6 - Listar Festivais");
        System.out.println("7 - Listar Encontros de Fãs");
        System.out.println("0 - Encerrar o programa");
    }
}