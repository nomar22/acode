package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
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

	@EntityGraph(value = "Product.detail", type = EntityGraphType.LOAD)
	Set<Product> findAll();

	@Query(value = "SELECT new com.exam.domain.Product(product.id,product.name) FROM Product product")
	Set<Product> findAllProductsWithNoRelationships();

	@Query(value = "select new com.exam.domain.Product(product.id,product.name) from Product product where product.id = :id")
	Product findProductWithNoRelationships(@Param("id") Integer id);

	@EntityGraph(value = "Product.detail", type = EntityGraphType.LOAD)
	Product findById(@Param("id") Integer id);

	@EntityGraph(value = "Product.detail", type = EntityGraphType.LOAD)
	Set<Product> findByParentId(@Param("id") Integer id);
}
