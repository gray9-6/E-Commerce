package com.example.ecomm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductList {

    public TableView<Product> productTable;

    public Pane getAllProduct(){
        // making columns
        TableColumn productId = new TableColumn("ID");
        productId.setCellValueFactory(new PropertyValueFactory<>("id"));  // setValueFactory ,it helps us to set the value of that column,,

        TableColumn productName = new TableColumn("Product_Name");
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn productprice = new TableColumn("Price");
        productprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<Product> data = FXCollections.observableArrayList();
        data.addAll(new Product(123,"Laptop",800.0),   // i have created the data
                new Product(128,"Phone",340.0)
                );

        ObservableList<Product> productList = Product.getAllProducts();
        // so now add the data to the table
        productTable = new TableView<>();  // product table banaya
     /*   productTable.setItems(data);   ---> dummy data  */  // fir uss table mein data set kar diya
        productTable.setItems(productList);
        productTable.getColumns().addAll(productId,productName,productprice);  // adding all the columns


        // adding it to Pane
        Pane tablePane = new Pane();
//        tablePane.setTranslateX(300);
        tablePane.setTranslateY(50);
        tablePane.getChildren().addAll(productTable);

        return  tablePane;

    }

    public Product getSelectedProduct(){
        // getting the selected item from product list
        return productTable.getSelectionModel().getSelectedItem();

    }
}
