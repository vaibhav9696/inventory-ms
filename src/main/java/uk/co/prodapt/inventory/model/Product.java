package uk.co.prodapt.inventory.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    @Schema(description = "Indicates if the product is currently in stock")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id != null && id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}