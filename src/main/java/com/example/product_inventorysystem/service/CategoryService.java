package com.example.product_inventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_inventorysystem.model.Category;
import com.example.product_inventorysystem.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

	@Autowired CategoryRepository categoryRepository;
	
	public Category getCategory(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
	
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	public boolean checkCatgeoryExist(String name) {
		// TODO Auto-generated method stub
		List<Category> categorylist=categoryRepository.findAll();
		for(Category c:categorylist) {
			if(c.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	

}
