package com.vershasKitchen.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vershasKitchen.entities.ProductEntity;

public interface DataBaseService {

	ResponseEntity<HttpStatus> deleteAll();

	List<ProductEntity> findByCategory(String category);

	ProductEntity save(ProductEntity dbImage);

}
