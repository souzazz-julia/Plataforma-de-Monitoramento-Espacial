import java.util.Random;

public class SensorTemperatura implements Sensor {
    private double ultimaLeitura;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorTemperatura(double limiteAlerta) {
        this.limiteAlerta = limiteAlerta;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Temperatura simulada entre -50 e 150 C
        ultimaLeitura = -50 + random.nextDouble() * 200;
        return ultimaLeitura;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String getTipo() {
        return "Temperatura";
    }

    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    public boolean acimaDoLimite() {
        return ultimaLeitura > limiteAlerta;
    }
}
