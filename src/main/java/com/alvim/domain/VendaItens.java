package com.alvim.domain;

import java.math.BigDecimal;

public class VendaItens {
    private int vendaId;
    private int produtoId;
    private int qnt;
    private BigDecimal preco;


    public int getVenda_id() {
        return vendaId;
    }

    public void setVenda_id(int vendaId) {
        this.vendaId = vendaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
