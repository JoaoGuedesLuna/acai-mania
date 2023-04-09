package main.java.com.acaimania.model;

/**
 * Essa classe é uma abstração básica de uma porção de Açaí.
 *
 * @author João Guedes.
 */
public abstract class Acai {

    private Double price;
    private String quantity;

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * Método que lista todos os ingredientes do açaí.
     *
     * @return Retorna uma String contendo todos os ingredientes do açaí.
     */
    public abstract String list();

}