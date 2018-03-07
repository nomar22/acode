package com.exam.startup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.exam.service.ProductService;

/**
 * @author RafaelRa
 *
 */
@Component
public class StartData implements InitializingBean {

	@Autowired
	private ProductService productService;

	@Override
	public void afterPropertiesSet() throws Exception {

		Product p1 = new Product();
		p1.setName("Computer");

		Image i1 = new Image();
		i1.setProduct(p1);

		p1 = productService.saveProduct(p1);

		p1.addImages(new Image("Keyboard"), new Image("Screen"));

		p1 = productService.saveProduct(p1);

		Product p2 = new Product();
		p2.setName("House");
		p2.setParent(p1);

		p2 = productService.saveProduct(p2);

		p2.addImages(new Image("Window"), new Image("Door"), new Image("Floor"));

		p2 = productService.saveProduct(p2);


		p1 = productService.saveProduct(p1);
	}

}
