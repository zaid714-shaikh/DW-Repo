package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Entity.Product;

@Repository
public interface Productrepo extends JpaRepository<Product, Long> {

	
	
}
