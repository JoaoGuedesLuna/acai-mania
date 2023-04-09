package main.java.com.acaimania.strategy;

/**
 * Essa ‘interface’ é uma abstração de um delivery de produto. Essa ‘interface’ está sendo usada
 * conforme o Design Pattern Strategy.
 *
 * @author João Guedes.
 */
public interface DeliveryStrategy {

    /**
     * Esse método retorna o valor de entrega do serviço prestado.
     *
     * @return Retorna o valor de entrega do serviço prestado.
     */
    double deliveryPrice();

}