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

import com.example.product_inventorysystem.model.Category;
import com.example.product_inventorysystem.repository.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CategoryRepositoryTest {
	
	@Autowired CategoryRepository categoryRepository;
	
	@Test
	@DisplayName("test save product")
	public void testSaveAndFindById() {
		Category category=new Category();
		category.setName("electronic");
		
		Category saveCategory=categoryRepository.save(category);
		Optional<Category> retrieveCategory=categoryRepository.findById(saveCategory.getId());
		
		assertThat(retrieveCategory).isPresent();
		assertEquals("electronic", retrieveCategory.get().getName());
		
	}
	
	@Test
	@DisplayName("test delete category")
	public void testDeleteCategory() {
		Category category=new Category();
		category.setName("electronic");
		
		Category saveCategory=categoryRepository.save(category);
		
		categoryRepository.deleteById(saveCategory.getId());
		Optional<Category> retrieveCategory=categoryRepository.findById(saveCategory.getId());
		
		assertThat(retrieveCategory).isNotPresent();
		
	}
	
	@Test
	@DisplayName("test find all category")
	public void testFindAllCategory() {
		Category category=new Category();
		category.setName("electronic");
		categoryRepository.save(category);

		Category categorytwo=new Category();
		categorytwo.setName("metal");
		categoryRepository.save(categorytwo);
		
		
		List<Category> listCategory=categoryRepository.findAll();
		assertThat(listCategory.size()).isEqualTo(2);
		
	}
	

}
