public class PropulsaoQuimica extends SistemaPropulsao {
    private double consumoCombustivel; // kg por segundo na potencia maxima

    public PropulsaoQuimica(int id, String nome, double consumoCombustivel) {
        super(id, nome);
        this.consumoCombustivel = consumoCombustivel;
    }

    @Override
    public void acelerar(int potencia) {
        if (!potenciaValida(potencia)) {
            return;
        }
        this.potenciaAtual = potencia;
        System.out.println(nome + " (quimica) acelerando a " + potencia + "% de potencia.");
        System.out.println("Consumo atual: " + (consumoCombustivel * potencia / 100) + " kg/s.");
    }

    @Override
    public double calcularEmpuxo() {
        // Empuxo proporcional a potencia e ao consumo de combustivel
        return potenciaAtual * consumoCombustivel * 10;
    }

    @Override
    public String exibirStatus() {
        return "Propulsao Quimica [" + nome + "] - " + (ligado ? "Ligada" : "Desligada")
                + " | Potencia: " + potenciaAtual + "% | Empuxo: " + calcularEmpuxo() + " kN";
    }
}
