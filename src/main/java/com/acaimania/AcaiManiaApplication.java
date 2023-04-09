package main.java.com.acaimania;

import main.java.com.acaimania.model.*;
import main.java.com.acaimania.model.decorator.*;
import main.java.com.acaimania.service.DeliveryService;
import main.java.com.acaimania.strategy.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe com os menus da aplicação.
 *
 * @author João Guedes.
 */
public class AcaiManiaApplication {

    /**
     * Método que inicia a aplicação.
     */
    public void start() {
        this.showAcaiOptionsMenu();
    }

    /**
     * Método que exibe um menu de entrada de dados.
     *
     * @param menu Menu que será exibido.
     *
     * @param validInputs Entradas válidas.
     *
     * @return Entrada que o usuário digitou no menu.
     */
    private String showInputMenu(StringBuilder menu, String validInputs) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        validInputs = validInputs.toUpperCase();
        do {
            this.clearScreen();
            System.out.print(menu);
            userInput = scanner.next();
            if (userInput.length() > 1 || !validInputs.contains(userInput.toUpperCase())) {
                this.showInvalidInputMessage();
            }
            else {
                this.showEndLine();
                return userInput;
            }
        } while(true);
    }

    /**
     * Método que exibe um menu onde o usuário poderá escolher se deseja uma porção pequena ou uma
     * porção grande de açaí.
     */
    private void showAcaiOptionsMenu() {
        StringBuilder acaiOptionsMenu = this.acaiOptionsMenu();
        String validOptions = "12X";
        String userOption = this.showInputMenu(acaiOptionsMenu, validOptions);
        this.triggerAcaiOptionsMenuOption(userOption);
    }

    /**
     * Método que retorna um StringBuilder contendo as opções de açaí disponíveis.
     *
     * @return Retorna um StringBuilder contendo as opções de açaí disponíveis.
     */
    private StringBuilder acaiOptionsMenu() {
        Acai smallAcai = SmallAcai.getInstance();
        Acai bigAcai = BigAcai.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n\n");
        sb.append("       Escolha uma das opções de tamanho:\n\n");
        sb.append("\n    [1] - Açaí no copo (").append(smallAcai.getQuantity()).append(") -> R$")
                .append(smallAcai.getPrice());
        sb.append("\n    [2] - Açaí na tigela (").append(bigAcai.getQuantity()).append(") -> R$")
                .append(bigAcai.getPrice());
        sb.append("\n    [x] - Sair");
        sb.append("\n\n                  Opção: ");
        return sb;
    }

    /**
     * Método que aciona uma opção do menu de opções de açaí conforme a escolha que o usuário fez.
     *
     * @param userOption Opção do usuário.
     */
    private void triggerAcaiOptionsMenuOption(String userOption) {
        switch (userOption.toUpperCase()) {
            case "1" -> this.showAdditionalOptionsMenu(SmallAcai.getInstance());
            case "2" -> this.showAdditionalOptionsMenu(BigAcai.getInstance());
            case "X" -> this.showByeMessage();
            default -> throw new IllegalArgumentException("Invalid option.");
        }
    }

    /**
     * Método que exibe um menu onde o usuário poderá escolher quais adicionais ele deseja adicionar
     * ao seu açaí.
     *
     * @param userAcai Açaí do usuário.
     */
    private void showAdditionalOptionsMenu(Acai userAcai) {
        StringBuilder additionalOptionsMenu = this.additionalOptionsMenu();
        String validOptions = "123456SX";
        String userOption = this.showInputMenu(additionalOptionsMenu, validOptions);
        this.triggerAdditionalOptionsMenuOption(userAcai, userOption);
    }

    /**
     * Método que retorna um StringBuilder contendo as opções de adicionais disponíveis.
     *
     * @return Retorna um StringBuilder contendo as opções de adicionais disponíveis.
     */
    private StringBuilder additionalOptionsMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n");
        sb.append("\n     Escolha uma das opções de adicionais:\n");
        sb.append("\n        [1] - Leite Condesado -> R$").append(CondensedMilk.ADDITIONAL_PRICE);
        sb.append("\n        [2] - Leite em pó -> R$").append(MilkPowder.ADDITIONAL_PRICE);
        sb.append("\n        [3] - Paçoca -> R$").append(Pacoca.ADDITIONAL_PRICE);
        sb.append("\n        [4] - Granola -> R$").append(Muesli.ADDITIONAL_PRICE);
        sb.append("\n        [5] - Kiwi -> R$").append(Kiwi.ADDITIONAL_PRICE);
        sb.append("\n        [6] - Morango -> R$").append(Strawberry.ADDITIONAL_PRICE);
        sb.append("\n        [s] - Sem mais adicionais");
        sb.append("\n        [x] - Sair");
        sb.append("\n\n                 Opção: ");
        return sb;
    }

    /**
     * Método que aciona uma opção do menu de opções de adicionais conforme a escolha que o usuário fez.
     *
     * @param userAcai Açaí do usuário.
     *
     * @param userOption Opção do usuário.
     */
    private void triggerAdditionalOptionsMenuOption(Acai userAcai, String userOption) {
        switch (userOption.toUpperCase()) {
            case "X" -> this.showByeMessage();
            case "S" -> this.showDeliveryOptionsMenu(userAcai);
            default -> {
                userAcai = this.addAdditional(userAcai, userOption);
                this.showAdditionalOptionsMenu(userAcai);
            }
        }
    }

    /**
     * Método que adiciona um adicional ao açaí conforme a escolha do usuário.
     *
     * @param userAcai Açaí do usuário.
     *
     * @param additionalOption Opção de adicional que o usuário escolheu.
     *
     * @return Retorna um açaí conforme a opção de adicional do usuário.
     */
    private Acai addAdditional(Acai userAcai, String additionalOption) {
        return switch (additionalOption.toUpperCase()) {
            case "1" -> new CondensedMilk(userAcai);
            case "2" -> new MilkPowder(userAcai);
            case "3" -> new Pacoca(userAcai);
            case "4" -> new Muesli(userAcai);
            case "5" -> new Kiwi(userAcai);
            case "6" -> new Strawberry(userAcai);
            default -> throw new IllegalArgumentException("Unknown additional option.");
        };
    }

    /**
     * Método que exibe um menu onde o usuário poderá escolher o serviço de entrega que ele deseja.
     *
     * @param userAcai Açaí do usuário.
     */
    private void showDeliveryOptionsMenu(Acai userAcai) {
        StringBuilder deliveryOptionsMenu = this.deliveryOptionsMenu();
        String validOptions = "1234X";
        String userOption = this.showInputMenu(deliveryOptionsMenu, validOptions);
        this.triggerDeliveryOptionsMenuOption(userAcai, userOption);
    }

    /**
     * Método que retorna um StringBuilder contendo todas as opções de serviço de entrega.
     *
     * @return Retorna um StringBuilder contendo todas as opções de serviço de entrega.
     */
    private StringBuilder deliveryOptionsMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n");
        sb.append("\n       Escolha uma das opções de entrega:\n");
        sb.append("\n         [1] - Ifood -> R$").append(this.getDeliveryServiceOption("1").calculateDeliveryPrice());
        sb.append("\n         [2] - NineNineFood -> R$").append(this.getDeliveryServiceOption("2").calculateDeliveryPrice());
        sb.append("\n         [3] - UberEats -> R$").append(this.getDeliveryServiceOption("3").calculateDeliveryPrice());
        sb.append("\n         [4] - YourSelfFood -> R$").append(this.getDeliveryServiceOption("4").calculateDeliveryPrice());
        sb.append("\n         [x] - Sair");
        sb.append("\n\n                   Opção: ");
        return sb;
    }

    /**
     * Método que aciona uma opção do menu de opções de entrega conforme a escolha que o usuário fez.
     *
     * @param userAcai Açaí do usuário.
     *
     * @param userOption Opção do usuário.
     */
    private void triggerDeliveryOptionsMenuOption(Acai userAcai, String userOption) {
        if (userOption.equalsIgnoreCase("X")) {
            this.showByeMessage();
            return;
        }
        DeliveryService deliveryServiceChosed = this.getDeliveryServiceOption(userOption);
        double deliveryPrice = deliveryServiceChosed.calculateDeliveryPrice();
        this.showFinalizeOrderMenu(userAcai, deliveryPrice);
    }

    /**
     * Método que retorna uma implementação de um DeliveryService conforme a opção do usuário.
     *
     * @param userOption Opção do usuário.
     *
     * @return Retorna uma implementação de um DeliveryService conforme a opção do usuário.
     */
    private DeliveryService getDeliveryServiceOption(String userOption) {
        return switch (userOption) {
            case "1" -> new DeliveryService(IFood.getInstance());
            case "2" -> new DeliveryService(NineNineFood.getInstance());
            case "3" -> new DeliveryService(UberEats.getInstance());
            case "4" -> new DeliveryService(YourSelfFood.getInstance());
            default -> throw new IllegalArgumentException("Unknown delivery service.");
        };
    }

    /**
     * Método que exibe o menu de finalização do pedido.
     * 
     * @param userAcai Açaí do usuário.
     *             
     * @param deliveryPrice Preço cobrado pelo serviço de entrega.
     */
    private void showFinalizeOrderMenu(Acai userAcai, double deliveryPrice) {
        double totalPrice = userAcai.getPrice() + deliveryPrice;
        StringBuilder finalizeOrderMenu = this.finalizeOrderMenu(userAcai, deliveryPrice, totalPrice);
        String validOptions = "SN";
        String userOption = this.showInputMenu(finalizeOrderMenu, validOptions);
        this.triggerFinalizeOrderMenuOption(userAcai, deliveryPrice, totalPrice, userOption);
    }

    /**
     * Método que retorna um StringBuilder contendo o menu de finalização do pedido.
     *
     * @param userAcai Açaí do usuário.
     *
     * @param deliveryPrice Preço cobrado pelo serviço de entrega.
     *
     * @param totalPrice Valor total do pedido (açaí + taxa de entrega).
     *
     * @return Retorna um StringBuilder contendo o menu de finalização do pedido.
     */
    private StringBuilder finalizeOrderMenu(Acai userAcai, double deliveryPrice, double totalPrice) {
        StringBuilder sb = new StringBuilder();
        sb.append("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n");
        sb.append("\n🔘 Preparando o pedido");
        sb.append("\n\n".concat(userAcai.list()));
        sb.append("\n\n🔘 Açaí pronto\n");
        sb.append("\n-----------------------------------------------");
        sb.append("\n             > Preço Açaí: R$").append(userAcai.getPrice());
        sb.append("\n             > Preço Entrega: R$").append(deliveryPrice);
        sb.append("\n-----------------------------------------------");
        sb.append("\n             VALOR TOTAL: R$").append(totalPrice);
        sb.append("\n-----------------------------------------------");
        sb.append("\n\n           Confirmar compra [s/n]? ");
        return sb;
    }

    /**
     * Método que aciona uma ação do menu de finalização do pedido conforme a opção do usuário.
     *
     * @param userAcai Açaí do usuário.
     *
     * @param deliveryPrice Preço cobrado pelo serviço de entrega.
     *
     * @param totalPrice Valor total do pedido (açaí + taxa de entrega).
     *
     * @param userOption Opção do usuário.
     */
    private void triggerFinalizeOrderMenuOption(Acai userAcai, Double deliveryPrice, Double totalPrice, String userOption) {
        if (userOption.equalsIgnoreCase("N")) {
            this.showCancelOrderMessage();
        }
        else {
            String receiptPath = this.generateReceipt(userAcai, deliveryPrice, totalPrice);
            this.showReceiptMenu(receiptPath);
        }
    }

    /**
     * Método que exibe um menu com os dados da nota fiscal e o tempo de entrega do pedido do usuário.
     *
     * @param receiptPath Caminho de diretório da nota fiscal gerada.
     */
    private void showReceiptMenu(String receiptPath) {
        StringBuilder receiptMenu = this.receiptMenu(receiptPath);
        String validOptions = "SN";
        String userOption = this.showInputMenu(receiptMenu, validOptions);
        this.triggerReceiptMenuOption(userOption);
    }

    /**
     * Método que retorna um StringBuilder contendo o menu de recibo da compra.
     *
     * @param receiptPath Caminho de diretório da nota fiscal.
     *
     * @return Retorna um StringBuilder contendo o menu de recibo da compra.
     */
    private StringBuilder receiptMenu(String receiptPath) {
        StringBuilder sb = new StringBuilder();
        int minutes = new Random().nextInt(15,36);
        sb.append("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨");
        sb.append("\n\nObrigado pela sua compra! 🤩");
        sb.append("\n\nSeu pedido será entregue em ").append(minutes).append(" minutos.");
        sb.append("\n\nSua nota fiscal foi gerada e está no arquivo de");
        sb.append("\nnome ").append(receiptPath).append(".");
        sb.append("\n\nEsse é um mostruário da sua nota ⬇\n\n");
        sb.append(this.getReceipt(receiptPath));
        sb.append("\n          Comprar novamente [s/n]? ");
        return sb;
    }

    /**
     * Método que aciona uma ação do menu de recibo do pedido conforme a opção do usuário.
     *
     * @param userOption Opção do usuário.
     */
    private void triggerReceiptMenuOption(String userOption) {
        switch (userOption.toUpperCase()) {
            case "S" -> this.showAcaiOptionsMenu();
            case "N" -> this.showByeMessage();
            default -> throw new IllegalArgumentException("Unknown user option: ".concat(userOption));
        }
    }

    /**
     * Método que gera a nota fiscal da compra do usuário.
     *
     * @param userAcai Açaí comprado pelo usuário.
     *
     * @param deliveryPrice Preço cobrado pelo serviço de entrega.
     *
     * @param totalPrice Valor total do pedido (açaí + taxa de entrega).
     *
     * @return Retorna o caminho de diretório da nota fiscal do usuário.
     */
    private String generateReceipt(Acai userAcai, Double deliveryPrice, Double totalPrice) {
        LocalDateTime dateOfOrder = LocalDateTime.now();
        String path = dateOfOrder.toString().concat(".NFS-e").replace(":", ";");
        try (FileWriter fileWriter = new FileWriter(path, false);
             BufferedWriter bufferedWriter = new BufferedWriter((fileWriter));
             PrintWriter printWriter = new PrintWriter(bufferedWriter)
        ) {
            StringBuilder receiptDescription = new StringBuilder();
            receiptDescription.append("Data da Compra: ").append(dateOfOrder).append("\n");
            receiptDescription.append(userAcai.list());
            receiptDescription.append("\nPreço do Açaí: R$").append(userAcai.getPrice());
            receiptDescription.append("\nPreço da Entrega: R$").append(deliveryPrice);
            receiptDescription.append("\nPreço Total: R$").append(totalPrice).append("\n");
            printWriter.append(receiptDescription);
            return path;
        }
        catch (IOException e) {
            throw new RuntimeException("Error generating receipt.");
        }
    }

    /**
     * Método que retorna os dados da nota fiscal (arquivo txt) gerada do pedido do usuário.
     *
     * @param path Caminho de diretório da nota fiscal.
     *
     * @return Retorna um StringBuilder contendo os dados da nota fiscal (arquivo txt) gerada do pedido do usuário.
     */
    private StringBuilder getReceipt(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                sb.append(line.concat("\n"));
                line = reader.readLine();
            }
            return sb;
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading receipt.");
        }
    }

    /**
     * Método que exibe uma mensagem caso o usuário opte por não finalizar a compra.
     */
    private void showCancelOrderMessage() {
        this.clearScreen();
        System.out.println("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n\n\n\n");
        System.out.println("    Que pena, você não finalizou sua compra.            ");
        System.out.println("             Esperamos que volte!                       ");
        System.out.println("\n\n\n\n🍨 🍨 🍨 🍨 🍨 🍨 🍨 🍨  🍨 🍨 🍨 🍨 🍨 🍨 🍨 ");
        this.sleep(1000);
    }

    /**
     * Método que exibe uma mensagem de despedida da aplicação.
     */
    private void showByeMessage() {
        this.clearScreen();
        System.out.println("🍨 🍨 🍨 🍨 🍨 🍨 AÇAÍ MANIA 🍨 🍨 🍨 🍨 🍨 🍨\n\n\n\n");
        System.out.println("         Já está indo embora? Que pena.                 ");
        System.out.println("           Esperamos te ver novamente!                  ");
        System.out.println("\n\n\n\n🍨 🍨 🍨 🍨 🍨 🍨 🍨 🍨  🍨 🍨 🍨 🍨 🍨 🍨 🍨");
        this.sleep(1000);
    }

    /**
     * Método que exibe uma mensagem de entrada inválida.
     */
    private void showInvalidInputMessage() {
        System.out.print("\n            ❌ Entrada inválida ❌\n");
        this.showEndLine();
    }

    /**
     * Método que exibe as últimas linhas de um menu.
     */
    private void showEndLine() {
        System.out.println("\n🍨 🍨 🍨 🍨 🍨 🍨 🍨 🍨  🍨 🍨 🍨 🍨 🍨 🍨 🍨");
        this.sleep(1500);
    }

    /**
     * Método com a função de "limpar" a tela do console.
     */
    private void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /**
     * Método que faz uma "pausa" no programa.
     *
     * @param millis Tempo em milissegundos em que o programa será "pausado".
     */
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    public static void main(String[] args) {
        AcaiManiaApplication acaiManiaApp = new AcaiManiaApplication();
        acaiManiaApp.start();
    }

}