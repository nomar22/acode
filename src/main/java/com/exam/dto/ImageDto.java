package com.exam.dto;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

public class ImageDto extends ResourceSupport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3171497678011189711L;

	private Integer idImage;

	private String description;

	private ProductDto product;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

}
