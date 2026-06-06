import java.util.Random;

public class SensorPressao implements Sensor {
    private double ultimaLeitura;
    private double limiteAlerta;
    private boolean funcionando;
    private Random random;

    public SensorPressao(double limiteAlerta) {
        this.limiteAlerta = limiteAlerta;
        this.funcionando = true;
        this.random = new Random();
    }

    @Override
    public double lerValor() {
        // Pressao simulada entre 0 e 200 kPa
        ultimaLeitura = random.nextDouble() * 200;
        return ultimaLeitura;
    }

    @Override
    public boolean verificarFuncionamento() {
        return funcionando;
    }

    @Override
    public String getTipo() {
        return "Pressao";
    }

    public double getLimiteAlerta() {
        return limiteAlerta;
    }

    public boolean acimaDoLimite() {
        return ultimaLeitura > limiteAlerta;
    }
}
