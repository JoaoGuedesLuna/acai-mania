package main.java.com.acaimania.model.decorator;

import main.java.com.acaimania.model.Acai;

/**
 * Essa classe é um Decorator do modelo Acai. Representando os adicionais que serão colocados
 * no açaí.
 *
 * @author João Guedes.
 */
public abstract class Additional extends Acai {

    private final Acai ACAI;

    public Additional(Acai acai) {
        this.ACAI = acai;
    }

    public Acai getACAI() {
        return this.ACAI;
    }

}