//package com.exam.util;
//
//import com.exam.model.Image;
//import com.exam.model.Product;
//
//public class ImageGenerator {
//
//	public static Product createImageAssociateProduct(Product product, Integer id, String type) {
//		Image im = new Image();
//		im.setId(id);
//		im.setType(type);
//		product.getImageSet().add(im);
//		return product;
//	}
//
//	public static Product createImageAssociateProduct(Product product, String type) {
//		Image im = new Image();
//		im.setType(type);
//		product.getImageSet().add(im);
//		return product;
//	}
//	
//	public static Image createImage() {
//		return new Image();
//	}
//}
