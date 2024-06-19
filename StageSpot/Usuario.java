import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario implements Serializable {
    private static List<Usuario> usuariosCadastrados = new ArrayList<>();

    protected String nomeOrganizacao;
    protected String email;
    protected String senha;

    public Usuario(String nomeOrganizacao, String email, String senha) {
        this.nomeOrganizacao = nomeOrganizacao;
        this.email = email;
        this.senha = senha;
    }

    // Método para cadastrar um novo usuário
    public static Usuario cadastrarUsuario(Scanner scanner) throws UsuarioException {
        System.out.println("\n-------------------Cadastro de Usuário-------------------");
        System.out.print("Nome da Organização/Artista: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Verificando se o email já está cadastrado
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.email.equals(email)) {
                throw new UsuarioException("Email já cadastrado. Por favor, use outro email.");
            }
        }

        Usuario novoUsuario = new Usuario(nome, email, senha);

        usuariosCadastrados.add(novoUsuario);

        System.out.println("\nUsuário cadastrado com sucesso!");

        return novoUsuario;
    }

    // Método para fazer login
    public static Usuario fazerLogin(Scanner scanner) throws UsuarioException {
        System.out.println("\n-------------------Login-------------------");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // Verificando se o email e a senha correspondem a algum usuário cadastrado
        for (Usuario usuario : usuariosCadastrados) {
            if (usuario.email.equals(email) && usuario.senha.equals(senha)) {
                System.out.println("\nLogin bem-sucedido!");
                return usuario;
            }
        }

        throw new UsuarioException("Email ou senha incorretos. Por favor, tente novamente.");
    }

    // Método para exibir detalhes do usuário
    public void exibirDetalhesUsuario() {
        System.out.println("Nome da Organização/Artista: " + nomeOrganizacao);
        System.out.println("Email: " + email);
    }

    public static void listarUsuarios() {
        if (usuariosCadastrados.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
        } else {
            System.out.println("-------------------Lista de Usuários Cadastrados-------------------");
            for (Usuario usuario : usuariosCadastrados) {
                System.out.println("Nome: " + usuario.nomeOrganizacao);
                System.out.println("Email: " + usuario.email);
                System.out.println();
            }
        }
    }

    public String getNome() {
        return nomeOrganizacao;
    }

    public String getEmail() {
        return email;
    }

    // Método para salvar usuários em um arquivo
    public static void salvarUsuarios() throws IOException {
        FileOutputStream fos = new FileOutputStream("usuarios.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(usuariosCadastrados);
        oos.close();
        fos.close();
    }

    // Método para carregar usuários de um arquivo
    public static void carregarUsuarios() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("usuarios.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        usuariosCadastrados = (List<Usuario>) ois.readObject();
        ois.close();
        fis.close();
    }

    // Métodos para acessar e definir a lista de usuários cadastrados
    public static List<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    public static void setUsuariosCadastrados(List<Usuario> usuarios) {
        usuariosCadastrados = usuarios;
    }
}
