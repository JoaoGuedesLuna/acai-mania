package main.java.com.acaimania.model;

/**
 * Essa classe herda da classe Acai e é uma abstração básica de uma porção pequena de Açaí.
 * O padrão de projeto utilizado para a criação de objetos dessa classe foi o Singleton.
 *
 * @author João Guedes.
 */
public class SmallAcai extends Acai {

    private static SmallAcai smallAcaiInstance;

    private SmallAcai() {
        super();
        super.setPrice(6.5);
        super.setQuantity("250 ml");
    }

    /**
     * Método que retorna uma mesma instância de um SmallAcai.
     *
     * @return Retorna uma mesma instância de um SmallAcai.
     */
    public static SmallAcai getInstance() {
        if (SmallAcai.smallAcaiInstance == null) {
            SmallAcai.smallAcaiInstance = new SmallAcai();
        }
        return SmallAcai.smallAcaiInstance;
    }

    @Override
    public String list() {
        return "✔ ".concat(super.getQuantity()).concat(" de Açaí");
    }

}