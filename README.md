# Plataforma de Monitoramento Espacial

Global Solution 2026 - Programação Orientada a Objetos (FIAP)

Sistema de console que simula o monitoramento de uma estação espacial: sensores,
propulsão, dados da missão e alertas automáticos.

## Como compilar e executar

Dentro da pasta `projeto-espacial/`:

```
javac *.java
java SistemaMonitoramento
```

Requer Java 17 ou superior (o `switch` com `->` e o `instanceof` com binding de
variável são usados no menu).

## Estrutura

| Arquivo | Papel |
|---|---|
| `ComponenteEspacial.java` | Classe abstrata base (id, nome, ligado, temperatura) |
| `Sensor.java` | Interface com `lerValor`, `verificarFuncionamento`, `getTipo` |
| `SensorTemperatura.java` | Sensor que implementa `Sensor` |
| `SensorPressao.java` | Sensor que implementa `Sensor` |
| `SensorRadiacao.java` | Sensor que implementa `Sensor` |
| `DadosMissao.java` | Encapsulamento (atributos privados, acesso por senha, validações) |
| `SistemaPropulsao.java` | Classe abstrata que herda de `ComponenteEspacial` |
| `PropulsaoQuimica.java` | Herda de `SistemaPropulsao` |
| `PropulsaoEletrica.java` | Herda de `SistemaPropulsao` |
| `SistemaMonitoramento.java` | Classe principal: menu, loop e sistema de alertas |

## Conceitos de POO aplicados

**Classe abstrata.** `ComponenteEspacial` não pode ser instanciada; define os
métodos concretos `ligar()`/`desligar()` e o método abstrato `exibirStatus()`,
que cada subclasse implementa do seu jeito.

**Interface.** `Sensor` define o contrato dos três métodos obrigatórios,
implementado por `SensorTemperatura`, `SensorPressao` e `SensorRadiacao`. No
menu, os sensores são tratados de forma polimórfica num array `Sensor[]`.

**Encapsulamento.** Em `DadosMissao` todos os atributos são privados.
Coordenadas e código de acesso só podem ser lidos/alterados com a senha correta.
Combustível e número de tripulantes validam valores inválidos, e o combustível
emite alerta automático quando cai abaixo de 20%.

**Herança.** `SistemaPropulsao` é abstrata e herda de `ComponenteEspacial`
(um motor também é um componente da estação). `PropulsaoQuimica` e
`PropulsaoEletrica` herdam dela, usam `super()` no construtor, têm atributos
próprios (consumo de combustível / carga de bateria) e sobrescrevem `acelerar()`
com comportamento distinto.

**Sistema de alertas.** A opção "Simular alertas" lê os sensores e classifica
cada leitura em `normal`, `ATENCAO`, `ALERTA` ou `CRITICO` conforme a proximidade
do limite definido para cada sensor.

## Observação de design

O enunciado lista 11 arquivos e pede que `ComponenteEspacial` tenha pelo menos
uma classe concreta herdeira. Em vez de criar uma classe fora dessa lista,
`SistemaPropulsao` foi modelada como subclasse de `ComponenteEspacial` — um motor
é, de fato, um componente da estação (tem id, nome e liga/desliga).
