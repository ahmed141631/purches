import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




 public class software {
    private List<Client> clients;
    private List<Purchase> purchases;
    private Map<Purchase, Client> purchaseClientMap;

    public software() {
        clients = new ArrayList<>();
        purchases = new ArrayList<>();
        purchaseClientMap = new HashMap<>();
    }

    public void registerUser(String name, String contactInformation, String address) {
        Client client = new Client(name, contactInformation, address);
        clients.add(client);
    }

    public void recordPurchase(String itemName, int quantity, double unitCost, String additionalInformation, Client client) {
        Purchase purchase = new Purchase(itemName, quantity, unitCost, additionalInformation);
        purchases.add(purchase);
        purchaseClientMap.put(purchase, client);
    }

    public double calculateTotalCost(Purchase purchase) {
        return purchase.getQuantity() * purchase.getUnitCost();
    }

    public void generateSummaryReport() {
        for (Client client : clients) {
            double totalCost = 0;
            System.out.println("Client: " + client.getName());
            System.out.println("Contact Information: " + client.getContactInformation());
            System.out.println("Address: " + client.getAddress());
            System.out.println("Purchases:");

            for (Purchase purchase : purchases) {
                Client purchaseClient = purchaseClientMap.get(purchase);
                if (purchaseClient.equals(client)) {
                    double purchaseCost = calculateTotalCost(purchase);
                    totalCost += purchaseCost;

                    System.out.println("Item: " + purchase.getItem());
                    System.out.println("Quantity: " + purchase.getQuantity());
                    System.out.println("Unit Cost: " + purchase.getUnitCost());
                    System.out.println("Additional Information: " + purchase.getAdditionalInformation());
                    System.out.println("Purchase Cost: " + purchaseCost);
                    System.out.println("------------------------");
                }
            }

            System.out.println("Total Cost for " + client.getName() + ": " + totalCost);
            System.out.println("========================");
        }
    }

    public List<Client> searchClients(String keyword) {
        List<Client> matchingClients = new ArrayList<>();

        for (Client client : clients) {
            if (client.getName().contains(keyword) || client.getContactInformation().contains(keyword)) {
                matchingClients.add(client);
            }
        }

        return matchingClients;
    }
    public ArrayList<Client> getClients() {
        return (ArrayList<Client>) clients;
    }

    public List<Purchase> searchPurchases(String keyword) {
        List<Purchase> matchingPurchases = new ArrayList<>();

        for (Purchase purchase : purchases) {
            if (purchase.getItem().contains(keyword) || purchase.getAdditionalInformation().contains(keyword)) {
                matchingPurchases.add(purchase);
            }
        }

        return matchingPurchases;
    }

    // Other methods for data backup and recovery can be added here
}