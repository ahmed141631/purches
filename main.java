import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ClientRegistrationForm extends JPanel {
    private JTextField nameField;
    private JTextField contactField;
    private JTextField addressField;
    private software software;

    public software getSoftware() {
        return software;
    }

    public void setSoftware(software software) {
        this.software = software;
    }

    public ClientRegistrationForm(software software) {
        this.software = software;

        setLayout(new GridLayout(4, 2));
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);
        add(new JLabel("Contact Information:"));
        contactField = new JTextField();
        add(contactField);
        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                software.registerUser(name, contact, address);

                JOptionPane.showMessageDialog(null, "Client registered successfully!");
                clearFields();
            }
        });
        add(registerButton);
    }

    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
        addressField.setText("");
    }
}

class PurchaseForm extends JPanel {
    private JTextField itemField;
    private JTextField quantityField;
    private JTextField unitCostField;
    private JTextField additionalInfoField;
    private JComboBox<String> clientComboBox;
    private software software;

    public software getSoftware() {
        return software;
    }

    public void setSoftware(software software) {
        this.software = software;
    }

    public PurchaseForm(software software) {
        this.software = software;

        setLayout(new GridLayout(6, 2));
        add(new JLabel("Item:"));
        itemField = new JTextField();
        add(itemField);
        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);
        add(new JLabel("Unit Cost:"));
        unitCostField = new JTextField();
        add(unitCostField);
        add(new JLabel("Additional Information:"));
        additionalInfoField = new JTextField();
        add(additionalInfoField);
        add(new JLabel("Client:"));
        clientComboBox = new JComboBox<>();
        add(clientComboBox);

        for (Client client : software.getClients()) {
            clientComboBox.addItem(client.getName());
        }

        JButton recordButton = new JButton("Record");
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = itemField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double unitCost = Double.parseDouble(unitCostField.getText());
                String additionalInfo = additionalInfoField.getText();
                String selectedClient = (String) clientComboBox.getSelectedItem();

                Client client = software.searchClients(selectedClient).get(0);
                software.recordPurchase(item, quantity, unitCost, additionalInfo, client);

                JOptionPane.showMessageDialog(null, "Purchase recorded successfully!");
                clearFields();
            }
        });
        add(recordButton);
    }

    private void clearFields() {
        itemField.setText("");
        quantityField.setText("");
        unitCostField.setText("");
        additionalInfoField.setText("");
    }
}



public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                software software = new software();

                JFrame frame = new JFrame("Application");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 250);
                frame.setLayout(new CardLayout());

                ClientRegistrationForm registrationForm = new ClientRegistrationForm(software);
                PurchaseForm purchaseForm = new PurchaseForm(software);

                frame.add(registrationForm, "registration");
                frame.add(purchaseForm, "purchase");

                JButton switchButton = new JButton("Switch to Purchase");
                switchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
                        cardLayout.show(frame.getContentPane(), "purchase");
                    }
                });
                registrationForm.add(switchButton);

                frame.setVisible(true);
            }
        });
    }
}