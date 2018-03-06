//package com.exam.util;
//
//import java.util.HashSet;
//
//import com.exam.model.Image;
//import com.exam.model.Product;
//
//public class ProductGenerator {
//	
//	public static Product createNewProduct(Integer id, String name, String description, Product parent) {
//		Product product = new Product();
//		product.setId(id);
//		product.setName(name);
//		product.setDescription(description);
//		product.setImageSet(new HashSet<Image>());
//		product.setParentProduct(parent);
//		return product;
//	}
//
//	public static Product createNewProduct(Integer id, String name, String description) {
//		Product product = new Product();
//		product.setId(id);
//		product.setName(name);
//		product.setDescription(description);
//		product.setImageSet(new HashSet<Image>());
//		return product;
//	}
//
//	public static Product createNewProduct(String name, String description, Product parent) {
//		Product product = new Product();
//		product.setName(name);
//		product.setDescription(description);
//		product.setImageSet(new HashSet<Image>());
//		product.setParentProduct(parent);
//		return product;
//	}
//
//	public static Product createNewProduct(String name, String description) {
//		Product product = new Product();
//		product.setName(name);
//		product.setDescription(description);
//		product.setImageSet(new HashSet<Image>());
//		return product;
//	}
//	
//	public static Product createNewProduct() {
//		Product product = new Product();
//		product.setImageSet(new HashSet<Image>());
//		return product;
//	}
//
//	public static Product createNewProduct(Integer id) {
//		Product product = new Product();
//		product.setId(id);
//		product.setImageSet(new HashSet<Image>());
//		return product;
//	}
//	
//}
