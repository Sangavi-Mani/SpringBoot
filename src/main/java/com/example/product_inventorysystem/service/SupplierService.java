package com.example.product_inventorysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_inventorysystem.model.Supplier;
import com.example.product_inventorysystem.repository.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

	@Autowired SupplierRepository supplierRepository;
	
	public Supplier getSupplier(Long id) {
		return supplierRepository.findById(id).orElse(null);
	}
	
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	
	public List<Supplier> getAllSuppliers(){
		return supplierRepository.findAll();
	}


}
