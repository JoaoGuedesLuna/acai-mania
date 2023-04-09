package main.java.com.acaimania.strategy;

/**
 * Essa classe é uma implementação da estratégia DeliveryStrategy, nela também está sendo
 * usado o Design Pattern Singleton.
 *
 * @author João Guedes.
 */
public class YourSelfFood implements DeliveryStrategy {

    private static YourSelfFood yourSelfFoodInstance;

    private YourSelfFood() {
        super();
    }

    /**
     * Método que retorna uma mesma instância da classe YourSelfFood.
     *
     * @return Retorna uma mesma instância da classe YourSelfFood.
     */
    public static YourSelfFood getInstance() {
        if (YourSelfFood.yourSelfFoodInstance == null) {
            YourSelfFood.yourSelfFoodInstance = new YourSelfFood();
        }
        return YourSelfFood.yourSelfFoodInstance;
    }

    @Override
    public double deliveryPrice() {
        return 3.0;
    }

}