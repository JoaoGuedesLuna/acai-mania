package main.java.com.acaimania.strategy;

/**
 * Essa classe é uma implementação da estratégia DeliveryStrategy, nela também está sendo
 * usado o Design Pattern Singleton.
 *
 * @author João Guedes.
 */
public class UberEats implements DeliveryStrategy {

    private static UberEats uberEatsInstance;

    private UberEats() {
        super();
    }

    /**
     * Método que retorna uma mesma instância da classe UberEats.
     *
     * @return Retorna uma mesma instância da classe UberEats.
     */
    public static UberEats getInstance() {
        if (UberEats.uberEatsInstance == null) {
            UberEats.uberEatsInstance = new UberEats();
        }
        return UberEats.uberEatsInstance;
    }

    @Override
    public double deliveryPrice() {
        return 3.75;
    }

}