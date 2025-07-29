package com.alvim.service;

import com.alvim.db.DBConnection;
import com.alvim.domain.Produto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class ProdutoService {

    @PostMapping("/product")
    public Produto createProduct(@RequestBody Produto produto){
        try{
            Connection connection = DBConnection.getCurrent().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (nome,qnt,preco) VALUES (?,?,?) RETURNING ID");
            preparedStatement.setString(1,produto.getNome());
            preparedStatement.setInt(2,produto.getQnt());
            preparedStatement.setBigDecimal(3,produto.getPreco());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                produto.setId(resultSet.getInt("id"));

            }
            return produto;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
