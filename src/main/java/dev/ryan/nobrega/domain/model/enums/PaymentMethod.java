package dev.ryan.nobrega.domain.model.enums;

public enum PaymentMethod {
    P("P", "Pix"),
    C("C", "Cartão de Crédito"),
    D("D", "Cartão de Débito");

    private final String codigo;
    private final String descricao;

    PaymentMethod(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PaymentMethod fromCodigo(String codigo) {
        for (PaymentMethod forma : values()) {
            if (forma.codigo.equalsIgnoreCase(codigo)) {
                return forma;
            }
        }
        throw new IllegalArgumentException("Código de forma de pagamento inválido: " + codigo);
    }
}
