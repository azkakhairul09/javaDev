package com.school.administration.app.io.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.school.administration.app.ui.io.entity.ProductsEntity;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<ProductsEntity, Long>{
	List<ProductsEntity> findAll();
	ProductsEntity findProductByProductId(String productId);
	List<ProductsEntity> findProductByExpiredDate(String expiredDate);
}
