package main.java.com.acaimania.strategy;

/**
 * Essa classe é uma implementação da estratégia DeliveryStrategy, nela também está sendo
 * usado o Design Pattern Singleton.
 *
 * @author João Guedes.
 */
public class NineNineFood implements DeliveryStrategy {

    private static NineNineFood nineNineFoodInstance;

    private NineNineFood() {
        super();
    }

    /**
     * Método que retorna uma mesma instância da classe NineNineFood.
     *
     * @return Retorna uma mesma instância da classe NineNineFood.
     */
    public static NineNineFood getInstance() {
        if (NineNineFood.nineNineFoodInstance == null) {
            NineNineFood.nineNineFoodInstance = new NineNineFood();
        }
        return NineNineFood.nineNineFoodInstance;
    }

    @Override
    public double deliveryPrice() {
        return 4.0;
    }

}