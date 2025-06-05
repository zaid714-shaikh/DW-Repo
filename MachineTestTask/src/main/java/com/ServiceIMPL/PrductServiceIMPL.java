package com.ServiceIMPL;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.DTO.RequestProduct;
import com.DTO.ResponseProduct;
import com.Entity.Category;
import com.Entity.Product;
import com.Exception.CategoryNotFoundException;
import com.Exception.PrdouctAlreadyExist;
import com.Exception.PrductNotFoundException;
import com.Repository.CategoryRepo;
import com.Repository.Productrepo;
import com.Service.productService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PrductServiceIMPL implements productService {

	@Autowired
	private Productrepo pr;

	@Autowired
	private CategoryRepo cr;

	@Override
	public ResponseProduct addProduct(RequestProduct requestproduct) {

		System.out.println(requestproduct);

		Category categ = cr.findByCategoryName(requestproduct.getCategoryName().toUpperCase())
				.orElseThrow(() -> new CategoryNotFoundException("Category is not found with this given Name"));

		if (pr.existsById(requestproduct.getProductID())) {
			throw new PrdouctAlreadyExist("Product Already Exist");
		}

//		Product product = pr.findById(requestproduct.getProductID()).get();

		Product product = new Product();

		product.setProductId(requestproduct.getProductID());
		product.setCategory(categ);
		product.setProductType(requestproduct.getProductType());
		product.setProductName(requestproduct.getProductName());

		Product prod = pr.save(product);

		System.out.println(prod);

		ModelMapper model = new ModelMapper();

		ResponseProduct rp = model.map(prod, ResponseProduct.class);

		System.out.println(rp);

		return rp;
	}

	@Override
	public ResponseProduct getProduct(long id) {
		Product product = pr.findById(id)
				.orElseThrow(() -> new PrductNotFoundException("Product Not Found With This ID"));

		ModelMapper model = new ModelMapper();

		ResponseProduct rp = model.map(product, ResponseProduct.class);

		return rp;
	}

	@Override
	public String deleteProductInService(long id) {
		Product product = pr.findById(id)
				.orElseThrow(() -> new PrductNotFoundException("Product Not Found With This ID"));

		product.setCategory(null);
		pr.delete(product);

		return "Product Deleted";
	}

	@Override
	public ResponseProduct updateProductInService(long id, RequestProduct requestproduct) {
		ModelMapper model = new ModelMapper();
		Product product = pr.findById(id)
				.orElseThrow(() -> new PrductNotFoundException("Product Not Found With This ID"));

		product.setProductName(requestproduct.getProductName());
		product.setProductType(requestproduct.getProductType());

		Category categ = cr.findByCategoryName(requestproduct.getCategoryName().toUpperCase())
				.orElseThrow(() -> new CategoryNotFoundException("Category is not found with this given Name"));

		product.setCategory(categ);

		Product prod = pr.save(product);

		ResponseProduct rp = model.map(prod, ResponseProduct.class);

		return rp;
	}

	@Override
	public List<ResponseProduct> getAllProductsInService(int page, int size) {

		PageRequest of = PageRequest.of(page, size);

		List<Product> listOfProduct = pr.findAll(of).getContent();

		ModelMapper map = new ModelMapper();
		List<ResponseProduct> lip = listOfProduct.stream().map(t -> map.map(t, ResponseProduct.class))
				.collect(Collectors.toList());

		return lip;
	}

}
