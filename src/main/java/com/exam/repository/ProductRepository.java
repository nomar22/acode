package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.domain.Product;

/**
 * @author RafaelRa
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

	@Query(value = "SELECT product FROM Product product "
			+ "LEFT JOIN FETCH product.images images LEFT JOIN FETCH product.parent parent ")
	Set<Product> findAllWithFetch();

	@Query(value = "SELECT new com.exam.domain.Product(product.id,product.name) FROM Product product")
	Set<Product> findAllProductsWithNoRelationships();

	@Query(value = "select new com.exam.domain.Product(product.id,product.name) from Product product where product.id = :id")
	Product findProductWithNoRelationships(@Param("id") Integer id);

	@Query(value = "select product FROM Product product "
			+ "LEFT JOIN FETCH product.images images LEFT JOIN FETCH product.parent parent "
			+ "LEFT JOIN FETCH parent.images parentImageSet LEFT JOIN FETCH parent.parent granpa "
			+ "WHERE product.id = :id")
	Product findProductWithFetch(@Param("id") Integer id);

	@Query(value = "select product FROM Product product "
			+ "LEFT JOIN FETCH product.images images LEFT JOIN FETCH product.parent parent "
			+ "LEFT JOIN FETCH parent.images " + "WHERE parent.id = :id")
	Set<Product> findByParentProductId(@Param("id") Integer id);
}
