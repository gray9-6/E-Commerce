package com.example.ecomm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Product {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId(){
        return  id.get();
    }

    public String getName(){
        return  name.get();
    }

    public Double getPrice(){
        return  price.get();
    }

    public  Product(int id, String name, Double price){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public static ObservableList<Product> getAllProducts(){
        String allProductList = "SELECT * FROM products";  // writing a  query
        return  getProducts(allProductList);              // and then passing query to the function to get the product
    }
    public static ObservableList<Product> getProducts(String query){    // this method is to get the product details from data base
        DatabaseConnection dbConn = new DatabaseConnection();
        ResultSet resultSet = dbConn.getQuery(query);
        ObservableList<Product> result = FXCollections.observableArrayList();
        try{
            if(resultSet != null){
                while (resultSet.next()){
                    // taking out the values of result set and adding it to the data
                    result.add(new Product(
                            resultSet.getInt("pid"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price")
                            )
                    );
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return  result;
    }

}
