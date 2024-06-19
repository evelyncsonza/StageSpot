import java.io.*;
import java.util.List;


public class Serializacao {
    // Método para salvar objetos de Usuário em um arquivo
    public static void salvarUsuarios(List<Usuario> usuarios, String nomeArquivo) throws IOException {
        FileOutputStream fos = new FileOutputStream(nomeArquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(usuarios);
        oos.close();
        fos.close();
    }

    // Método para carregar objetos de Usuário de um arquivo
    public static List<Usuario> carregarUsuarios(String nomeArquivo) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(nomeArquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Usuario> usuarios = (List<Usuario>) ois.readObject();
        ois.close();
        fis.close();
        return usuarios;
    }
}
