import java.util.ArrayList;
import java.util.List;

public class Local {
    protected String nome;
    protected String endereco;
    protected String cidade;
    protected String estado;
    protected String pais;
    protected int capacidade;
    protected String tipo;

    public static List<Local> listaLocais = new ArrayList<>();  // Lista para armazenar os locais cadastrados

    public Local(String nome, String endereco, String cidade, String estado, String pais, int capacidade, String tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.capacidade = capacidade;
        this.tipo = tipo;
        listaLocais.add(this);
    }

    protected void exibirDetalhesLocal() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço: " + endereco);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("País: " + pais);
        System.out.println("Capacidade: " + capacidade + " pessoas");
        System.out.println("Tipo: " + tipo);
    }

    public static void listarLocais() {
        if (listaLocais.isEmpty()) {
            System.out.println("Nenhum local cadastrado.");
        } else {
            System.out.println("-------------------Lista de Locais-------------------");
            for (int i = 0; i < listaLocais.size(); i++) {
                Local local = listaLocais.get(i);
                System.out.println((i + 1) + ". " + local.getNome() + " - " + local.getEndereco() + " - " + local.getTipo() + " - " + local.getCapacidade() + " pessoas");
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }
}