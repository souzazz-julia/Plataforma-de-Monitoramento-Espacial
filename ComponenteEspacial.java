public abstract class ComponenteEspacial {
    protected int id;
    protected String nome;
    protected boolean ligado;
    protected double temperatura;

    public ComponenteEspacial(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.ligado = false;
        this.temperatura = 20.0;
    }

    public void ligar() {
        this.ligado = true;
        System.out.println(nome + " ligado.");
    }

    public void desligar() {
        this.ligado = false;
        System.out.println(nome + " desligado.");
    }

    // Cada componente descreve seu estado a seu modo
    public abstract String exibirStatus();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isLigado() {
        return ligado;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
}
