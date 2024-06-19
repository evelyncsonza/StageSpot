// Classe Ingresso com método isGratuito adicionado
class Ingresso {
    private String tipo;
    private String setor;
    private double preco;
    private String siteCompra;
    private String cupomDesconto;

    public Ingresso(String tipo, String setor, double preco, String siteCompra, String cupomDesconto) {
        this.tipo = tipo;
        this.setor = setor;
        this.preco = preco;
        this.siteCompra = siteCompra;
        this.cupomDesconto = cupomDesconto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSetor() {
        return setor;
    }

    public double getPreco() {
        return preco;
    }

    public String getSiteCompra() {
        return siteCompra;
    }

    public String getCupomDesconto() {
        return cupomDesconto;
    }

    public boolean isGratuito() {
        return "Gratuito".equalsIgnoreCase(tipo) && "Gratuito".equalsIgnoreCase(setor) && preco == 0 && "Gratuito".equalsIgnoreCase(siteCompra) && "Gratuito".equalsIgnoreCase(cupomDesconto);
    }
}