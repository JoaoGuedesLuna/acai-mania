package main.java.com.acaimania.model;

/**
 * Essa classe herda da classe Acai e é uma abstração básica de uma porção grande de Açaí.
 * O padrão de projeto utilizado para a criação de objetos dessa classe foi o Singleton.
 *
 * @author João Guedes.
 */
public class BigAcai extends Acai {

    private static BigAcai bigAcaiInstance;

    private BigAcai() {
        super();
        super.setPrice(12.0);
        super.setQuantity("500 ml");
    }

    /**
     * Método que retorna uma mesma instância de um BigAcai.
     *
     * @return Retorna uma mesma instância de um BigAcai.
     */
    public static BigAcai getInstance() {
        if (BigAcai.bigAcaiInstance == null) {
            BigAcai.bigAcaiInstance = new BigAcai();
        }
        return BigAcai.bigAcaiInstance;
    }

    @Override
    public String list() {
        return "✔ ".concat(super.getQuantity()).concat(" de Açaí");
    }

}