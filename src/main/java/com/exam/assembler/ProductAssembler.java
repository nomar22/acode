package com.exam.assembler;

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
		ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
		ProductDto product = createResourceWithId(entity.getId(), entity);
		ProductDto result = mapper.produtEntityToProduct(entity);
		result.add(product.getLinks());
		ProductDto parent = toResource(entity.getParentProduct());
		result.setParent(parent);

		return result;
	}

}
