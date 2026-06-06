public abstract class SistemaPropulsao extends ComponenteEspacial {
    protected double potenciaAtual;

    public SistemaPropulsao(int id, String nome) {
        super(id, nome);
        this.potenciaAtual = 0;
    }

    protected boolean potenciaValida(int potencia) {
        if (!ligado) {
            System.out.println(nome + " esta desligado. Ligue o motor antes de acelerar.");
            return false;
        }
        if (potencia < 0 || potencia > 100) {
            System.out.println("Valor invalido: a potencia deve estar entre 0 e 100.");
            return false;
        }
        return true;
    }

    public abstract void acelerar(int potencia);

    public abstract double calcularEmpuxo();

    public double getPotenciaAtual() {
        return potenciaAtual;
    }
}
