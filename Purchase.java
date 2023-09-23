class Purchase {
    private String item;
    private int quantity;
    private double unitCost;
    private String additionalInformation;

    public Purchase(String item, int quantity, double unitCost, String additionalInformation) {
        this.item = item;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.additionalInformation = additionalInformation;
    }

    // Getters and setters

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}