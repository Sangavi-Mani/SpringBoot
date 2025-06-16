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

import com.example.product_inventorysystem.model.Supplier;
import com.example.product_inventorysystem.repository.SupplierRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class SupplierRepositoryTest {
	
	@Autowired SupplierRepository supplierRepository;
	
	@Test
	@DisplayName("test save supplier")
	public void testSaveAndFindById() {
		Supplier supplier=new Supplier();
		supplier.setName("vidhya");
		
		Supplier saveSupplier=supplierRepository.save(supplier);
		Optional<Supplier> retrieveSupplier=supplierRepository.findById(saveSupplier.getId());
		
		assertThat(retrieveSupplier).isPresent();
		assertEquals("vidhya", retrieveSupplier.get().getName());
		
	}
	
	@Test
	@DisplayName("test delete supplier")
	public void testDeleteProduct() {
		Supplier supplier=new Supplier();
		supplier.setName("vidhya");
		
		Supplier saveSupplier=supplierRepository.save(supplier);
		
		supplierRepository.deleteById(saveSupplier.getId());
		Optional<Supplier> retrieveSupplier=supplierRepository.findById(saveSupplier.getId());
		
		assertThat(retrieveSupplier).isNotPresent();
		
	}
	
	@Test
	@DisplayName("test find all suppliers")
	public void testFindAllProduct() {
		Supplier supplier=new Supplier();
		supplier.setName("vidhya");
		supplierRepository.save(supplier);

		Supplier supplierone=new Supplier();
		supplierone.setName("dhana");
		supplierRepository.save(supplierone);
		
		Supplier suppliertwo=new Supplier();
		suppliertwo.setName("abhi");
		supplierRepository.save(suppliertwo);
		
		
		List<Supplier> listSupplier=supplierRepository.findAll();
		assertThat(listSupplier.size()).isEqualTo(3);
		
	}
	

}