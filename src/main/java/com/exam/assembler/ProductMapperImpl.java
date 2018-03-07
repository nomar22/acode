package com.exam.assembler;

import org.springframework.stereotype.Component;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.exam.dto.ImageDto;
import com.exam.dto.ProductDto;

/**
 * 
 * @author rafael
 *
 */
@Component
public class ProductMapperImpl implements ProductMapper {

	@Override
	public ImageDto imageEntitytoImage(Image entity) {
		ImageDto result = new ImageDto();
		result.setDescription(entity.getDescription());
		result.setIdImage(entity.getId());

		return result;
	}

	@Override
	public Image imageToImageEntity(ImageDto dto) {
		Image result = new Image();
		result.setDescription(dto.getDescription());
		result.setId(dto.getIdImage());
		return result;
	}

	@Override
	public ProductDto produtEntityToProduct(Product entity) {
		ProductDto result = new ProductDto();
		result.setIdProduct(entity.getId());
		result.setName(entity.getName());
		return result;
	}

	@Override
	public Product productToProductEntity(ProductDto dto) {
		Product result = new Product();
		result.setId(dto.getIdProduct());
		result.setName(dto.getName());
		return result;
	}

}
