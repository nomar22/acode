package com.exam.assembler;

import org.hibernate.LazyInitializationException;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.exam.api.ProductsApi;
import com.exam.domain.Product;
import com.exam.dto.ProductDto;

@Component
public class ProductAssembler extends JaxRsResourceAssemblerSuport<Product, ProductDto> {

	public ProductAssembler() {
		super(ProductsApi.class, ProductDto.class);
	}

	@Override
	public ProductDto toResource(Product entity) {
		if (entity == null) {
			return null;
		}
		ProductDto result = null;
		try {
			ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
			ProductDto product = createResourceWithId(entity.getId(), entity);
			result = mapper.produtEntityToProduct(entity);
			result.add(product.getLinks());
			ProductDto parent = toResource(entity.getParent());
			result.setParent(parent);
		} catch (LazyInitializationException ex) {
			result = new ProductDto();
			// will return null, can't load at its level
		}

		return result;
	}

}
