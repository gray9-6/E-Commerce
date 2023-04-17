package com.example.ecomm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application  {

    ProductList productList = new ProductList();
    private  final int width = 600, height = 400,headerLine = 50;
    Pane bodyPane;

    Button signInButton = new Button("sign In");
    Label welcomeLabel = new Label("Welcome Customer");

    Customer loggedInCustomer = null;

    /* GridPane Layout pane allows us to add the multiple nodes in multiple rows and columns.
    It is seen as a flexible grid of rows and columns where nodes can be placed in any cell of the grid. */
    private GridPane headerBar(){

        GridPane header = new GridPane();

        TextField searchBar = new TextField();  // created a textField
        Button searchButton = new Button("Search");     // created a button

        // applying action event on search button
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                bodyPane.getChildren().clear();  // removed login page
                bodyPane.getChildren().add(productList.getAllProduct());  // added product list pane i.e table pane
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });


        // add function is method of grid pane which allows us to add the components at desired location(row,col) in Grid pane
        // we can use the addRow method as well,, where we can pass two components which we have to add in a row
        header.setHgap(10);
        header.add(searchBar,0,0);
        header.add(searchButton,1,0);
        header.add(signInButton,2,0);
        header.add(welcomeLabel,3,0);

        return header;
    }

    private void showDialouge(String message){
        Dialog<String> dialog = new Dialog<String>();  // created a dialog
        //setting the title
        dialog.setTitle("Order");
        ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        // Setting the content of the dialog
        dialog.setContentText(message);
        // Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(buttonType);

        dialog.showAndWait();
    }
    private GridPane footerBar(){
        Button buyNowButton = new Button("Buy Now");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // first i have to find that,, what product is selected
                Product product = productList.getSelectedProduct();

                boolean orderStatus = false;
                if(product != null && loggedInCustomer != null){
                    Order.placeOrder(loggedInCustomer,product);
                }
                if(orderStatus == true){
                    showDialouge("Order Successful");
                }
                else{

                }

                DatabaseConnection dbConn = new DatabaseConnection();

            }
        });

        GridPane  footer = new GridPane();
        footer.setTranslateY(headerLine+height +10);
        footer.add(buyNowButton,0,0);

        return footer;
    }

    private  GridPane loginPage(){

        // Labels
        Label userLabel = new Label("User Name");
        Label passLabel = new Label("Password");

        // TextField ANd password
        TextField userName = new TextField();
        userName.setPromptText("Enter User Name");  // prompt text means it will show this text until you enter the text
        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");

        // Button
        Button loginbutton = new Button("Login");
        Label messageLabel = new Label("");

        // applying action event on login button
        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String user = userName.getText();
                String pass = password.getText();

                loggedInCustomer = Login.coustomerLogin(user,pass);
                if(loggedInCustomer != null){
                    messageLabel.setText("login Successfull");
                    welcomeLabel.setText("Welcome " + loggedInCustomer.getName());

                }else{
                    messageLabel.setText("Please Try Again");

                }

            }
        });

        // adding all the above components to grid pane
        GridPane loginPane = new GridPane();
        loginPane.setTranslateY(50);  // moving control to 50 pixel down vertically
        loginPane.setTranslateX(50);  // moving control to 50 pixel down horizontally

        loginPane.setHgap(10);  //setting the horizontal gap
        loginPane.setVgap(10); // setting the vertical gap
        loginPane.add(userLabel,0,0);
        loginPane.add(userName,1,0);
        loginPane.add(passLabel,0,1);
        loginPane.add(password,1,1);
        loginPane.add(loginbutton,0,2);
        loginPane.add(messageLabel,1,2);

        return loginPane;
    }
    private Pane createContent(){
        Pane root = new Pane();

        root.setPrefSize(width,height+2*headerLine);  // setting the size of our pane
//        root.setStyle("-fx-background-color: #ff0000;");

        bodyPane = new Pane();
        bodyPane.setPrefSize(width,height);
        bodyPane.setTranslateY(headerLine);
        bodyPane.setTranslateY(10);

        bodyPane.getChildren().add(loginPage());

        // getChildren method is used to get the children components(such as checkboxes, buttons etc) in a container.
        root.getChildren().addAll(headerBar()
//                ,loginPage()
//                , productList.getAllProduct()
                ,bodyPane
                , footerBar());  // after getting children of root ,, add the headerBar in them

        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Ecommerce.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("ECommerce");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}