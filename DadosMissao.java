public class DadosMissao {
    private String coordenadas;
    private String codigoAcesso;
    private double nivelCombustivel;
    private String trajetoria;
    private int numeroTripulantes;
    private final String senha;

    public DadosMissao(String senha) {
        this.senha = senha;
        this.coordenadas = "0, 0, 0";
        this.codigoAcesso = "INDEFINIDO";
        this.nivelCombustivel = 100.0;
        this.trajetoria = "INDEFINIDA";
        this.numeroTripulantes = 0;
    }

    // Dados sensiveis exigem senha para leitura e escrita
    public String getCoordenadas(String senha) {
        if (autenticar(senha)) {
            return coordenadas;
        }
        System.out.println("Acesso negado: senha incorreta.");
        return null;
    }

    public void setCoordenadas(String senha, String coordenadas) {
        if (autenticar(senha)) {
            this.coordenadas = coordenadas;
            System.out.println("Coordenadas atualizadas.");
        } else {
            System.out.println("Acesso negado: senha incorreta.");
        }
    }

    public String getCodigoAcesso(String senha) {
        if (autenticar(senha)) {
            return codigoAcesso;
        }
        System.out.println("Acesso negado: senha incorreta.");
        return null;
    }

    public void setCodigoAcesso(String senha, String codigoAcesso) {
        if (autenticar(senha)) {
            this.codigoAcesso = codigoAcesso;
            System.out.println("Codigo de acesso atualizado.");
        } else {
            System.out.println("Acesso negado: senha incorreta.");
        }
    }

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(double nivelCombustivel) {
        if (nivelCombustivel < 0 || nivelCombustivel > 100) {
            System.out.println("Valor invalido: combustivel deve estar entre 0 e 100.");
            return;
        }
        this.nivelCombustivel = nivelCombustivel;
        if (combustivelBaixo()) {
            System.out.println("ALERTA: nivel de combustivel abaixo de 20%!");
        }
    }

    public boolean combustivelBaixo() {
        return nivelCombustivel < 20;
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        this.trajetoria = trajetoria;
    }

    public int getNumeroTripulantes() {
        return numeroTripulantes;
    }

    public void setNumeroTripulantes(int numeroTripulantes) {
        if (numeroTripulantes < 0) {
            System.out.println("Valor invalido: numero de tripulantes nao pode ser negativo.");
            return;
        }
        this.numeroTripulantes = numeroTripulantes;
    }

    private boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }
}
