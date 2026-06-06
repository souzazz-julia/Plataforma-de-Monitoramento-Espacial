import java.util.Random;

public class SensorRadiacao implements Sensor {
    private double ultimaLeitura;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorRadiacao(double limiteAlerta) {
        this.limiteAlerta = limiteAlerta;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Radiacao simulada entre 0 e 500 mSv
        ultimaLeitura = random.nextDouble() * 500;
        return ultimaLeitura;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String getTipo() {
        return "Radiacao";
    }

    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    public boolean acimaDoLimite() {
        return ultimaLeitura > limiteAlerta;
    }
}
