public class PropulsaoEletrica extends SistemaPropulsao {
    private double cargaBateria; // 0 a 100

    public PropulsaoEletrica(int id, String nome, double cargaBateria) {
        super(id, nome);
        this.cargaBateria = cargaBateria;
    }

    @Override
    public void acelerar(int potencia) {
        if (!potenciaValida(potencia)) {
            return;
        }
        if (cargaBateria <= 0) {
            System.out.println(nome + " (eletrica) sem carga na bateria.");
            return;
        }
        this.potenciaAtual = potencia;
        System.out.println(nome + " (eletrica) acelerando a " + potencia + "% de potencia.");
        System.out.println("Carga da bateria: " + cargaBateria + "%.");
    }

    @Override
    public double calcularEmpuxo() {
        // Empuxo menor que o quimico, limitado pela carga disponivel
        return potenciaAtual * (cargaBateria / 100) * 2;
    }

    @Override
    public String exibirStatus() {
        return "Propulsao Eletrica [" + nome + "] - " + (ligado ? "Ligada" : "Desligada")
                + " | Potencia: " + potenciaAtual + "% | Bateria: " + cargaBateria
                + "% | Empuxo: " + calcularEmpuxo() + " kN";
    }
}
