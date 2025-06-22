package uk.co.prodapt.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.prodapt.inventory.model.Product;
import uk.co.prodapt.inventory.model.Supplier;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private final SupplierService supplierService;

    @Autowired
    public ProductService(SupplierService supplierService) {
        this.supplierService = supplierService;

        // Initialize some sample products
        products.add(new Product(1, "Product A", true, 1, null));
        products.add(new Product(2, "Product B", false, 2, null));
        products.add(new Product(3, "Product C", true, 3, null));
    }

    public List<Product> getAll() {
        return enrichWithSupplierInfo(new ArrayList<>(products));
    }

    public Optional<Product> getById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .map(this::enrichWithSupplierInfo);
    }

    public Product create(Product product) {
        products.add(product);
        return enrichWithSupplierInfo(product);
    }

    public Product update(Integer id, Product updatedProduct) {
        Product existingProduct = getById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setAvailable(updatedProduct.isAvailable());
        existingProduct.setSupplierId(updatedProduct.getSupplierId());
        return enrichWithSupplierInfo(existingProduct);
    }

    public void delete(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    private List<Product> enrichWithSupplierInfo(List<Product> products) {
        for (Product product : products) {
            enrichWithSupplierInfo(product);
        }
        return products;
    }

    private Product enrichWithSupplierInfo(Product product) {
        if (product.getSupplierId() != null) {
            try {
                Supplier supplier = supplierService.getSupplierById(product.getSupplierId());
                product.setSupplierName(supplier.getName());
            } catch (RuntimeException e) {
                product.setSupplierName("Unknown Supplier");
            }
        }
        return product;
    }

    public List<Product> getAllDedupedAndFiltered(Boolean showOnlyAvailable) {
        List<Product> products = getAll();

        // Deduplicate by product id
        List<Product> deduped = new ArrayList<>(products.stream()
                .filter(p -> p != null && p.getId() != null)
                .collect(Collectors.toMap(
                        Product::getId, // Use product ID as key
                        p -> p, // Use the product itself as value
                        (existing, replacement) -> existing, // Keep the first occurrence
                        java.util.LinkedHashMap::new // LinkedHashMap to maintain insertion order
                ))
                .values()); // Collect values back to a list

        if (Boolean.TRUE.equals(showOnlyAvailable)) { // Filter to show only available products
            return deduped.stream()
                    .filter(Product::isAvailable)
                    .collect(Collectors.toList());
        }
        return deduped;
    }
}