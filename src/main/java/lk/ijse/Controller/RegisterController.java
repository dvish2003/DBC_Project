package lk.ijse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button btnClick;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserID;

    @FXML
    void btnClickOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String name = txtName.getText();
        String id = txtUserID.getText();
        int age = Integer.parseInt(txtAge.getText());
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();

        try {
            saveCustomer(name, id, age, contact, address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomer(String name, String id, int age, int contact, String address) throws SQLException {
        String sql = "INSERT INTO customers (name, id, age, contact, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sampleProject",
                "root",
                "IJSE@123"
        );
             PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, name);
            pstm.setString(2, id);
            pstm.setInt(3, age);
            pstm.setInt(4, contact);
            pstm.setString(5, address);

            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0)
                System.out.println("Customer saved!");
            else
                System.out.println("Customer not saved!");
        }
    }
}
