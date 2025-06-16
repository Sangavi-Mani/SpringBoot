package com.example.product_inventorysystem.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.product_inventorysystem.model.Product;
import com.example.product_inventorysystem.repository.ProductRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductRepositoryTest {
	
	@Autowired ProductRepository productRepository;
	
	@Test
	@DisplayName("test save product")
	public void testSaveAndFindById() {
		Product product=new Product();
		product.setName("table");
		
		Product saveProduct=productRepository.save(product);
		Optional<Product> retrieveProduct=productRepository.findById(saveProduct.getId());
		
		assertThat(retrieveProduct).isPresent();
		assertEquals("table", retrieveProduct.get().getName());
		
	}
	
	@Test
	@DisplayName("test delete product")
	public void testDeleteProduct() {
		Product product=new Product();
		product.setName("table");
		
		Product saveProduct=productRepository.save(product);
		
		productRepository.deleteById(saveProduct.getId());
		Optional<Product> retrieveProduct=productRepository.findById(saveProduct.getId());
		
		assertThat(retrieveProduct).isNotPresent();
		
	}
	
	@Test
	@DisplayName("test find all products")
	public void testFindAllProduct() {
		Product product=new Product();
		product.setName("table");
		productRepository.save(product);

		Product productone=new Product();
		product.setName("pencil");
		productRepository.save(productone);
		
		
		List<Product> listProduct=productRepository.findAll();
		assertThat(listProduct.size()).isEqualTo(2);
		
	}
	

}
