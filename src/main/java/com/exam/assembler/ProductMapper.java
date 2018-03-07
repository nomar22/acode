package com.exam.assembler;

import org.mapstruct.Mapper;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.exam.dto.ImageDto;
import com.exam.dto.ProductDto;

/**
 * 
 * @author rafael
 *
 */
@Mapper
public interface ProductMapper {

	ImageDto imageEntitytoImage(Image entity);

	Image imageToImageEntity(ImageDto dto);

	ProductDto produtEntityToProduct(Product entity);

	Product productToProductEntity(ProductDto dto);

}
