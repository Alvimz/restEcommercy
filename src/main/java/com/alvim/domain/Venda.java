package com.alvim.domain;

import java.util.List;

public class Venda {
    private int id;
    private String metodoPagamento;
    private List<VendaItens> itens;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public List<VendaItens> getItens() {
        return itens;
    }

    public void setItens(List<VendaItens> itens) {
        this.itens = itens;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

}
