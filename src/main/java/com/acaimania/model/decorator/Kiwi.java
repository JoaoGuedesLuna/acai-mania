package main.java.com.acaimania.model.decorator;

import main.java.com.acaimania.model.Acai;

/**
 * Essa classe é uma implementação da classe Decorator Additional. Ela representa o adicional
 * Kiwi.
 *
 * @author João Guedes.
 */
public class Kiwi extends Additional {

    public static final Double ADDITIONAL_PRICE = 1.0;

    public Kiwi(Acai acai) {
        super(acai);
        Double price = super.getACAI().getPrice() + Kiwi.ADDITIONAL_PRICE;
        super.setPrice(price);
    }

    @Override
    public String list() {
        return super.getACAI().list().concat("\n✔ Kiwi");
    }

}