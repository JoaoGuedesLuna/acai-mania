package main.java.com.acaimania.strategy;

/**
 * Essa classe é uma implementação da estratégia DeliveryStrategy, nela também está sendo
 * usado o Design Pattern Singleton.
 *
 * @author João Guedes.
 */
public class IFood implements DeliveryStrategy {

    private static IFood iFoodInstance;

    private IFood() {
        super();
    }

    /**
     * Método que retorna uma mesma instância da classe IFood.
     *
     * @return Retorna uma mesma instância da classe IFood.
     */
    public static IFood getInstance() {
        if (IFood.iFoodInstance == null) {
            IFood.iFoodInstance = new IFood();
        }
        return IFood.iFoodInstance;
    }

    @Override
    public double deliveryPrice() {
        return 3.0;
    }

}