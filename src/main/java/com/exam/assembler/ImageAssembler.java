package com.exam.assembler;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.api.ImagesApi;
import com.exam.domain.Image;
import com.exam.dto.ImageDto;
import com.exam.dto.ProductDto;

@Component
public class ImageAssembler extends JaxRsResourceAssemblerSuport<Image, ImageDto> {

	@Autowired
	private ProductAssembler assembler;

	public ImageAssembler() {
		super(ImagesApi.class, ImageDto.class);
	}

	@Override
	public ImageDto toResource(Image entity) {
		ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
		ImageDto resource = createResourceWithId(entity.getId(), entity);
		ImageDto result = mapper.imageEntitytoImage(entity);
		ProductDto prod = assembler.toResource(entity.getProduct());
		result.add(resource.getLinks());
		result.setProduct(prod);

		return result;
	}

}
