package com.example.product_inventorysystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_inventorysystem.exception.CategoryAlreadyExistException;
import com.example.product_inventorysystem.exception.InvalidContactNumberException;
import com.example.product_inventorysystem.exception.ProductNotFoundException;
import com.example.product_inventorysystem.exception.ProductPriceInvalidException;
import com.example.product_inventorysystem.model.Category;
import com.example.product_inventorysystem.model.Product;
import com.example.product_inventorysystem.model.Supplier;
import com.example.product_inventorysystem.service.CategoryService;
import com.example.product_inventorysystem.service.ProductService;
import com.example.product_inventorysystem.service.SupplierService;

@RestController
public class AppController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SupplierService supplierService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		try {
			List<Product> product=productService.getAllProducts();
			return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id")Long id) throws Exception{
		Product product=productService.getProduct(id);
			if(product==null) {
				throw new ProductNotFoundException("Product in not found with id : "+id);
			}
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws Exception{
		try {
			if(product.getPrice().intValue()<0) {
				throw new ProductPriceInvalidException("Product price is invalid : "+product.getPrice());
			}
		Product productObj=productService.saveProduct(product);
		return new ResponseEntity<Product>(productObj, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id")Long id,@RequestBody Product product){
		Product productObj=productService.getProduct(id);
		productObj.setName(product.getName());
		productObj.setPrice(product.getPrice());
		productObj.setDescription(product.getDescription());
		
		Category category=categoryService.getCategory(product.getCategory().getId());
		category.setName(product.getCategory().getName());
		
		Supplier supplier=supplierService.getSupplier(product.getSupplier().getId());
		supplier.setName(product.getSupplier().getName());
		supplier.setAddress(product.getSupplier().getAddress());
		supplier.setContact(product.getSupplier().getContact());
		
		productObj.setCategory(category);
		productObj.setSupplier(supplier);
		
		productService.saveProduct(productObj);
		
		return new ResponseEntity<Product>(productObj, HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id")Long id){
		
		productService.deleteProduct(id);
		return new ResponseEntity<>("product deleted with id: "+id, HttpStatus.OK);
	}
	
	@GetMapping("products/category/{categoryId}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("categoryId") Long categoryId){
		Category category=categoryService.getCategory(categoryId);
		List<Product> product=category.getProducts();
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
	}
	
	@GetMapping("/products/supplier/{id}")
	public ResponseEntity<List<Product>> getProductBySupplier(@PathVariable("id")Long id){
		Supplier supplier=supplierService.getSupplier(id);
		List<Product> product=supplier.getProducts();
		return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
		
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategory(){
		try {
			List<Category> categories=categoryService.getAllCategories();
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) throws Exception{
		try {
			if(categoryService.checkCatgeoryExist(category.getName())) {
				throw new CategoryAlreadyExistException("Category : "+category.getName()+" is already exist.. ");
			}
		Category categoryObj=categoryService.saveCategory(category);
		return new ResponseEntity<Category>(categoryObj, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@PostMapping("/suppliers")
	public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) throws Exception{
		String s=supplier.getContact();
		if(s.length()!=10) {
			throw new InvalidContactNumberException("Contact number is inavlid: "+supplier.getContact());
		}
		try {
			
		Supplier supplierObj=supplierService.saveSupplier(supplier);
		return new ResponseEntity<Supplier>(supplierObj, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new Exception("Invalid contact number..");
		}
		
	}
	
	
	@GetMapping("/suppliers")
	public ResponseEntity<List<Supplier>> getAllSupplier(){
		try {
			List<Supplier> supplier=supplierService.getAllSuppliers();
			return new ResponseEntity<List<Supplier>>(supplier, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
