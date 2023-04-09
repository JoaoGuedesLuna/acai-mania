package main.java.com.acaimania.model.decorator;

import main.java.com.acaimania.model.Acai;

/**
 * Essa classe é uma implementação da classe Decorator Additional. Ela representa o adicional
 * Granola.
 *
 * @author João Guedes.
 */
public class Muesli extends Additional {

    public static final Double ADDITIONAL_PRICE = 1.0;

    public Muesli(Acai acai) {
        super(acai);
        Double price = super.getACAI().getPrice() + Muesli.ADDITIONAL_PRICE;
        super.setPrice(price);
    }

    @Override
    public String list() {
        return super.getACAI().list().concat("\n✔ Granola");
    }

}