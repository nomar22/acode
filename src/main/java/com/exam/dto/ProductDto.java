package com.exam.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class ProductDto extends ResourceSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1947024724051635237L;
	private Integer idProduct;
	private String name;
	private ProductDto parent;

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductDto getParent() {
		return parent;
	}

	public void setParent(ProductDto parent) {
		this.parent = parent;
	}

}
