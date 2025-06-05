package com.Service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.DTO.RequestProduct;
import com.DTO.ResponseProduct;

public interface productService {

	ResponseProduct addProduct(RequestProduct requestproduct);

	ResponseProduct getProduct(long id);

	String deleteProductInService(long id);

	ResponseProduct updateProductInService(long id, RequestProduct requestproduct);

	List<ResponseProduct> getAllProductsInService(int page, int size);

}
