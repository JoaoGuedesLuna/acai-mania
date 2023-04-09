package main.java.com.acaimania.service;

import main.java.com.acaimania.strategy.DeliveryStrategy;

/**
 * Essa é uma classe de serviço (Service) para regras de negócio relacionadas ao delivery.
 *
 * @author João Guedes.
 */
public class DeliveryService {

    private final DeliveryStrategy deliveryStrategy;

    public DeliveryService(DeliveryStrategy deliveryStrategy) {
        this.deliveryStrategy = deliveryStrategy;
    }

    /**
     * Esse método retorna o valor de entrega do serviço prestado conforme as regras de negócio.
     *
     * @return Retorna o valor de entrega do serviço prestado conforme as regras de negócio.
     */
    public double calculateDeliveryPrice() {
        return deliveryStrategy.deliveryPrice();
    }

}