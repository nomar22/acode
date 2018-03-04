package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.domain.Image;
import com.exam.domain.Product;

/**
 * @author RafaelRa
 *
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

	
	//TODO improve this
	@Query(value = "select image FROM Image image LEFT JOIN FETCH image.product product LEFT JOIN FETCH product.images images  LEFT JOIN FETCH product.parent parent "
			+ "LEFT JOIN FETCH parent.images " + "WHERE product = :product")
	Set<Image> findByParentProduct(@Param("product") Product product);
}
