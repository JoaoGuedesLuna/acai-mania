package main.java.com.acaimania.model.decorator;

import main.java.com.acaimania.model.Acai;

/**
 * Essa classe é uma implementação da classe Decorator Additional. Ela representa o adicional
 * Paçoca.
 *
 * @author João Guedes
 */
public class Pacoca extends Additional {

    public static final Double ADDITIONAL_PRICE = 1.0;

    public Pacoca(Acai acai) {
        super(acai);
        Double price = super.getACAI().getPrice() + Pacoca.ADDITIONAL_PRICE;
        super.setPrice(price);
    }

    @Override
    public String list() {
        return super.getACAI().list().concat("\n✔ Paçoca");
    }

}