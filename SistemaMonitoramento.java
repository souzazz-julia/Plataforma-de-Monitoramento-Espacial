import java.util.Scanner;

public class SistemaMonitoramento {
    private static Sensor[] sensores;
    private static SistemaPropulsao[] propulsores;
    private static DadosMissao dadosMissao;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        sensores = new Sensor[] {
                new SensorTemperatura(100),
                new SensorPressao(150),
                new SensorRadiacao(300)
        };

        propulsores = new SistemaPropulsao[] {
                new PropulsaoQuimica(1, "Motor Principal", 5.0),
                new PropulsaoEletrica(2, "Motor Auxiliar", 80.0)
        };

        dadosMissao = new DadosMissao("1234");

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            System.out.println();
            switch (opcao) {
                case 1 -> verificarSensores();
                case 2 -> controlarPropulsao();
                case 3 -> gerenciarDados();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("Encerrando o sistema de monitoramento.");
                default -> System.out.println("Opcao invalida.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== PLATAFORMA DE MONITORAMENTO ESPACIAL =====");
        System.out.println("1 - Verificar sensores");
        System.out.println("2 - Controlar propulsao");
        System.out.println("3 - Gerenciar dados da missao");
        System.out.println("4 - Simular alertas");
        System.out.println("5 - Exibir status completo");
        System.out.println("0 - Sair");
    }

    private static void verificarSensores() {
        System.out.println("--- Leitura dos Sensores ---");
        for (Sensor s : sensores) {
            double valor = s.lerValor();
            String ok = s.verificarFuncionamento() ? "OK" : "FALHA";
            System.out.printf("%-12s | valor: %8.2f | estado: %s%n", s.getTipo(), valor, ok);
        }
    }

    private static void controlarPropulsao() {
        System.out.println("--- Controle de Propulsao ---");
        for (int i = 0; i < propulsores.length; i++) {
            System.out.println((i + 1) + " - " + propulsores[i].getNome());
        }
        int escolha = lerInteiro("Escolha o motor: ");
        if (escolha < 1 || escolha > propulsores.length) {
            System.out.println("Motor invalido.");
            return;
        }
        SistemaPropulsao motor = propulsores[escolha - 1];

        System.out.println("1 - Ligar  |  2 - Desligar  |  3 - Acelerar");
        int acao = lerInteiro("Acao: ");
        switch (acao) {
            case 1 -> motor.ligar();
            case 2 -> motor.desligar();
            case 3 -> {
                int potencia = lerInteiro("Potencia (0-100): ");
                motor.acelerar(potencia);
            }
            default -> System.out.println("Acao invalida.");
        }
    }

    private static void gerenciarDados() {
        System.out.println("--- Dados da Missao ---");
        System.out.println("1 - Ler coordenadas (protegidas)");
        System.out.println("2 - Definir nivel de combustivel");
        System.out.println("3 - Definir numero de tripulantes");
        System.out.println("4 - Definir trajetoria");
        int acao = lerInteiro("Acao: ");
        switch (acao) {
            case 1 -> {
                String senha = lerTexto("Senha: ");
                String coord = dadosMissao.getCoordenadas(senha);
                if (coord != null) {
                    System.out.println("Coordenadas: " + coord);
                }
            }
            case 2 -> {
                double nivel = lerDouble("Nivel de combustivel (0-100): ");
                dadosMissao.setNivelCombustivel(nivel);
            }
            case 3 -> {
                int trip = lerInteiro("Numero de tripulantes: ");
                dadosMissao.setNumeroTripulantes(trip);
            }
            case 4 -> {
                String traj = lerTexto("Trajetoria: ");
                dadosMissao.setTrajetoria(traj);
                System.out.println("Trajetoria definida.");
            }
            default -> System.out.println("Acao invalida.");
        }
    }

    // Le os sensores e classifica cada leitura em um nivel de alerta
    private static void simularAlertas() {
        System.out.println("--- Simulacao de Alertas ---");
        for (Sensor s : sensores) {
            double valor = s.lerValor();
            double limite = limiteDoSensor(s);
            String nivel = classificarAlerta(valor, limite);
            System.out.printf("%-12s | valor: %8.2f | limite: %7.2f | %s%n",
                    s.getTipo(), valor, limite, nivel);
        }

        if (dadosMissao.combustivelBaixo()) {
            System.out.println("COMBUSTIVEL | nivel: " + dadosMissao.getNivelCombustivel()
                    + "% | CRITICO");
        }
    }

    private static String classificarAlerta(double valor, double limite) {
        if (valor > limite) {
            return "CRITICO";
        } else if (valor > limite * 0.8) {
            return "ALERTA";
        } else if (valor > limite * 0.6) {
            return "ATENCAO";
        }
        return "normal";
    }

    private static double limiteDoSensor(Sensor s) {
        if (s instanceof SensorTemperatura t) {
            return t.getLimiteAlerta();
        } else if (s instanceof SensorPressao p) {
            return p.getLimiteAlerta();
        } else if (s instanceof SensorRadiacao r) {
            return r.getLimiteAlerta();
        }
        return 0;
    }

    private static void exibirStatusCompleto() {
        System.out.println("--- Status Completo da Estacao ---");
        System.out.println("Sensores:");
        for (Sensor s : sensores) {
            System.out.println("  " + s.getTipo() + " - "
                    + (s.verificarFuncionamento() ? "operacional" : "em falha"));
        }
        System.out.println("Propulsores:");
        for (SistemaPropulsao p : propulsores) {
            System.out.println("  " + p.exibirStatus());
        }
        System.out.println("Missao:");
        System.out.println("  Combustivel: " + dadosMissao.getNivelCombustivel() + "%");
        System.out.println("  Tripulantes: " + dadosMissao.getNumeroTripulantes());
        System.out.println("  Trajetoria: " + dadosMissao.getTrajetoria());
    }

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Digite um numero inteiro: ");
        }
        return scanner.nextInt();
    }

    private static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Digite um numero: ");
        }
        return scanner.nextDouble();
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.next();
    }
}
