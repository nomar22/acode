package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.domain.Image;

/**
 * @author RafaelRa
 *
 */
@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

	Set<Image> findByProductId(@Param("id") Integer id);

	@EntityGraph(value = "Product.detail", type = EntityGraphType.LOAD)
	Image findById(@Param("id") Integer id);
}
