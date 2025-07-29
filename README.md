
# RESTFULL E-commercy

Simple project using http as RESTFULL application 
to enhance my skills with Database(Postgresql) and Java.

- Create product using http;
- Sell product too;

Use that query to create the DATABASE SQL and to insert products too.



## How to use:

- Clone this project!
```bash
clone git@github.com:Alvimz/restEcommercy.git
```

- Open this project on your IDE!
- Create the database and insert values using the section down bellow!
- Start the Main file (RestEcommercyApplication.java)
- Send the requests using POSTMAN!
## Paths POSTMAN
- POST
```bash
http://localhost:8080/product
```

``` bash
{
    "nome":"Faca de prástico",
    "qnt":35,
    "preco": 39.99
}
```
Creates a product!

- POST

```bash
http://localhost:8080/venda
```
``` bash
{
  "metodoPagamento": "pix",
  "itens": [
    { "produtoId": 1, "qnt": 2, "preco": 299.90 }
    ]
}
```
Creates a new sale.




## UML - SQL


![App Screenshot](https://i.imgur.com/gn0rKVG.png)


## SQL QUERIE

```bash
CREATE DATABASE ecommercy;
--DROP DATABASE ecommercy; --apaga o DB

CREATE TABLE IF NOT EXISTS produto (
  id serial PRIMARY KEY,
  nome varchar(155) NOT NULL,
  qnt smallint NOT NULL,
  preco numeric(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS venda (
  id serial PRIMARY KEY,
  metodo_pagamento varchar(30) NOT NULL,
  comprado_em timestamp DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS venda_itens (
  id serial PRIMARY KEY,
  venda_id int REFERENCES venda(id),
  produto_id int REFERENCES produto(id) NOT NULL,
  qnt smallint NOT NULL CHECK (qnt > 0),
  preco numeric(10,2) NOT NULL
);

INSERT INTO produto (nome, qnt, preco) VALUES
  ('Teclado Mecânico',       15, 249.90),
  ('Mouse Sem Fio',          30,  89.90),
  ('Monitor 24\" LED',       10, 799.00),
  ('Cadeira Gamer',           5, 699.50),
  ('Webcam HD 1080p',        20, 199.99),
  ('Headset USB',            25, 129.90),
  ('Pen Drive 32GB',         50,  49.90),
  ('SSD 1TB',                12, 499.00),
  ('Gabinete ATX',           10, 299.90),
  ('Microfone Condensador',   8, 399.00);
```
