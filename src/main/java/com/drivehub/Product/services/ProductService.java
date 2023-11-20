package com.drivehub.Product.services;

import com.drivehub.Product.entitys.ProductCriteria;
import com.drivehub.Product.entitys.Products;
import com.drivehub.Product.repositorys.ProductRepository;
import com.drivehub.Product.specification.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductInput{
        private String name;

        private String category;

        private String brand;

        private long price;
    }

    public void createProduct(ProductInput productInput) {
        Products products = new Products(productInput.name,productInput.category, productInput.brand, productInput.price);
        productRepository.save(products);
    }

    public void updateProduct(ProductInput productInput, long id) {
        Optional<Products> productsOpt = productRepository.findById(id);
        if (productsOpt.isEmpty()){
            throw new Error("not found this product");
        }
        Products products = productsOpt.get();

        if (productInput.name != null){
            products.setName(productInput.brand);
        }
        if (productInput.category != null){
            products.setCategory(productInput.category);
        }
        if (productInput.brand != null){
            products.setBrand(productInput.brand);
        }
        if (!Objects.isNull(productInput.price)){
            products.setPrice(productInput.price);
        }
        productRepository.save(products);
    }

    public void deleteProduct(long id) {
        Optional<Products> productsOpt = productRepository.findById(id);
        if (productsOpt.isEmpty()){
            throw new Error("not found this product");
        }
        Products products = productsOpt.get();
        productRepository.delete(products);
    }
    public Page<Products> show(int page ,int size) {
        Page<Products> products = productRepository.findAll(PageRequest.of(page, size));
        return products;
    }

    public List<Products> getByCategory(String category,int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        List<Products> products = productRepository.findByCategory(category);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), products.size());
        List<Products> pageContent = products.subList(start, end);
        return pageContent;
    }

    public List<Products> filter(ProductCriteria productCriteria,int page ,int size){
        Pageable pageRequest = PageRequest.of(page, size);
        Specification<Products> spe = ProductSpecification.toSpecification(productCriteria);
        List<Products> products = productRepository.findBy(spe);
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), products.size());
        List<Products> pageContent = products.subList(start, end);
        return pageContent;
    }
}
