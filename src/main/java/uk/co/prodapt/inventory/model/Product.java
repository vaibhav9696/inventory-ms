package uk.co.prodapt.inventory.model;

public class Product {
    private Integer id;
    private String name;
    private boolean available;
    private Integer supplierId; // ID of the supplier
    private String supplierName; // Name of the supplier

    public Product(Integer id, String name, boolean available, Integer supplierId, String supplierName) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}