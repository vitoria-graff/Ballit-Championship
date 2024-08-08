package dados;

public class Time {
    private final String nome;
    private final String gritoDeGuerra;
    private final int anoFundacao;
    private int pontos;
    private int blots;
    private int plifs;
    private int advrunghs;

    public Time(String nome, String gritoDeGuerra, int anoFundacao) {
        this.nome = nome;
        this.gritoDeGuerra = gritoDeGuerra;
        this.anoFundacao = anoFundacao;
        this.pontos = 0; // Inicializa a pontuação com zero
        this.blots = 0;
        this.plifs = 0;
        this.advrunghs = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getGritoDeGuerra() {
        return gritoDeGuerra;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public int getPontos() {
        return pontos;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public int getTotalBlots() {
        return blots;
    }

    public int getTotalPlifs() {
        return plifs;
    }

    public int getTotalAdvrunghs() {
        return advrunghs;
    }

    public void adicionarBlots(int blots) {
        this.blots += blots;
    }

    public void adicionarPlifs(int plifs) {
        this.plifs += plifs;
    }

    public void adicionarAdvrunghs(int advrunghs) {
        this.advrunghs += advrunghs;
    }

    @Override
    public String toString() {
        return nome + " - " + pontos + " pontos";
    }
}

