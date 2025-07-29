package com.alvim.service;

import com.alvim.db.DBConnection;
import com.alvim.domain.Venda;
import com.alvim.domain.VendaItens;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class VendaService {

    @PostMapping("/venda")
    public Venda vender(@RequestBody Venda venda){
        try{
            Connection connection = DBConnection.getCurrent().getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda (metodo_pagamento) VALUES (?) RETURNING ID");
            preparedStatement.setString(1,venda.getMetodoPagamento());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idVenda = resultSet.getInt("id");
            venda.setId(idVenda);



            for(VendaItens itens : venda.getItens()){

                itens.setVenda_id(idVenda);
                PreparedStatement psItens = connection.prepareStatement("INSERT INTO venda_itens (venda_id,produto_id,qnt,preco) VALUES (?,?,?,?)");
                PreparedStatement psQnt = connection.prepareStatement("SELECT qnt FROM produto WHERE id= ? ");
                psQnt.setInt(1,itens.getProdutoId());
                ResultSet resultQnt = psQnt.executeQuery();
                if (resultQnt.next()){
                    int qntItem = resultQnt.getInt("qnt");
                    if(qntItem < itens.getQnt()){
                        connection.rollback();
                        throw new SQLException("Não possue qnt no estoque suficiente");

                    }
                    PreparedStatement psUpdate = connection.prepareStatement("UPDATE produto SET qnt = qnt - ? WHERE id = ?");
                    psUpdate.setInt(1,itens.getQnt());
                    psUpdate.setInt(2,itens.getProdutoId());
                    psUpdate.executeUpdate();

                }

                psItens.setInt(1,idVenda);
                psItens.setInt(2,itens.getProdutoId());
                psItens.setInt(3,itens.getQnt());
                psItens.setBigDecimal(4,itens.getPreco());




                int resultSet1 = psItens.executeUpdate();
            }
            connection.commit();

            return venda;


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

}
