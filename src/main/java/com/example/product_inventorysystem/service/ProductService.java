package com.example.product_inventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_inventorysystem.model.Product;
import com.example.product_inventorysystem.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired ProductRepository productRepository;
	
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	
}
