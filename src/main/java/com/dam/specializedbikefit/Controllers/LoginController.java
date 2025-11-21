package com.dam.specializedbikefit.Controllers;

import com.dam.specializedbikefit.Classes.User;
import com.dam.specializedbikefit.DBConnection.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LoginController {

    @FXML
    private Label loginLabelTitle;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public TextField emailTextField;

    @FXML
    protected void login() {
        String user_email = emailTextField.getText();
        String password = passwordTextField.getText();

        Session session = Connection.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<User> query = session.createQuery("FROM User WHERE user_email = :email AND user_password = :password",User.class);
        query.setParameter("email", user_email);
        query.setParameter("password", password);


        //Esto devuelve un único valor de usuario. Si no es nulo, significa que las credenciales con correctas.
        User user = query.uniqueResult();

        if (user != null) {
            //Falta
        }




    }
}
