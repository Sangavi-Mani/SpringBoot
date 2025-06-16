package com.example.product_inventorysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.product_inventorysystem.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
