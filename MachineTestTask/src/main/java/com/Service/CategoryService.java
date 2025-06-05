package com.Service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.DTO.RequestCategory;
import com.DTO.ResponseCategory;

public interface CategoryService {

	ResponseCategory registerCategoryINService(RequestCategory requestCategory);

	List<ResponseCategory> getAllCategory(int page, int size);

	ResponseCategory getSingleCateogry(long id);

	ResponseCategory updateCategoryInService(long id, RequestCategory requestCategory);

	String deleteCategory(long id);

}
