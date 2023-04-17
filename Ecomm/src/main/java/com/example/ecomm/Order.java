package com.example.ecomm;

public class Order {

    // now placing the order with the selected product
    public static  boolean placeOrder(Customer customer, Product product){
        try{
            // query
            String placeOrder = " INSERT INTO orders(customer_id,product_id,quantity,status) VALUES(" + customer.getId() + "," + product.getId() + ", 'Ordered')";

            // connection to database and inserting/updating the query
            DatabaseConnection bdConn = new DatabaseConnection();
            return bdConn.insertUpdatesQuery(placeOrder);

        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
