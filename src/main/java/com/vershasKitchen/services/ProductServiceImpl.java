package com.vershasKitchen.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vershasKitchen.Helper.ProductHelper;
import com.vershasKitchen.entities.ProductEntity;
import com.vershasKitchen.payload.ProductDetails;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private DataBaseService dbService;

	@Override
	public ProductEntity storeProduct(ProductEntity product) {
		return dbService.save(product);
	}

	@Override
	public Map<String, Map<String, List<ProductDetails>>> getAllProducts() {
		Map<String, Map<String, List<ProductDetails>>> categoryMap = new HashMap<String, Map<String, List<ProductDetails>>>();
		for (String category : ProductHelper.CATEGORYMAP.keySet()) {
			List<ProductEntity> products = dbService.findByCategory(category);
			Map<String, List<ProductDetails>> subCategoryMap = new HashMap<String, List<ProductDetails>>();
			for (String subCategory : ProductHelper.CATEGORYMAP.get(category)) {
				List<ProductDetails> productList = new ArrayList<ProductDetails>();
				for (ProductEntity product : products) {
					if (product.getSubcategory().equalsIgnoreCase(subCategory)) {
						productList.add(new ProductDetails(product.getId(), product.getName(), product.getPrice(),
								ProductHelper.getImageUrl(product.getImagePath())));
					}
				}
				subCategoryMap.put(subCategory, productList);
			}

			categoryMap.put(category, subCategoryMap);
		}

		return categoryMap;
	}

	@Override
	public Map<String, List<ProductDetails>> getAllPopularProducts() {
		Map<String, List<ProductDetails>> categoryMap = new HashMap<String, List<ProductDetails>>();
		for (String category : ProductHelper.CATEGORYMAP.keySet()) {
			List<ProductEntity> products = dbService.findByCategory(category);
			List<ProductDetails> productList = new ArrayList<ProductDetails>();
			for (ProductEntity product : products) {
				if (product.getIsPopular()) {
					productList.add(new ProductDetails(product.getId(), product.getName(), product.getPrice(),
							ProductHelper.getImageUrl(product.getImagePath())));
				}
			}
			categoryMap.put(category, productList);
		}

		return categoryMap;
	}

	@Override
	public ProductEntity updateProduct(ProductEntity product) {
		return dbService.save(product);
	}

	@Override
	public void deleteProduct(String productId) {
		dbService.deleteById(productId);
	}


}