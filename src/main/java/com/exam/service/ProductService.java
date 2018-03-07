package com.exam.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.exam.repository.ProductRepository;

/**
 * Service to manage the connection between the Product resource and the Product
 * repository. Executes validations needed before persist data in the database.
 * 
 * @author RafaelRa
 *
 */
@Service
public class ProductService {

	private ProductRepository productRepository;
	private ImageService imageService;
	private Logger LOG = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Method to get a specific Product with all its specified relationships.
	 * 
	 * @param id
	 * @return
	 */
	public Product getByIdWithFetch(Integer id) {
		return productRepository.findById(id);
	}

	/**
	 * Method to get a specific Product without its specified relationships.
	 * 
	 * @param id
	 * @return
	 */
	public Product getByIdExcludingRelationships(Integer id) {
		return productRepository.findProductWithNoRelationships(id);
	}

	/**
	 * Method to get all Products with all its specified relationships.
	 * 
	 * @return
	 */
	public Set<Product> getAllWithFetch() {
		return productRepository.findAll();
	}

	/**
	 * Method to get all Products without its specified relationships.
	 * 
	 * @return
	 */
	public Set<Product> getAllExcludingRelationships() {
		return productRepository.findAllProductsWithNoRelationships();
	}

	/**
	 * Method to get all child Products of a specific Product.
	 * 
	 * @param product
	 * @return
	 */
	public Set<Product> getAllProductChild(Integer id) {
		return productRepository.findByParentId(id);
	}

	/**
	 * Method to persist a Product.
	 * 
	 * @param product
	 * @return
	 */
	public Product saveProduct(Product product) {
		Product productToSave = product;
		try {
			validateProduct(productToSave);
		} catch (Exception e1) {
			return null;
		}

		try {
			productToSave = productRepository.save(productToSave);
			return getByIdWithFetch(productToSave.getId());
		} catch (Exception e) {
			LOG.error("An error occurred during product saving: " + e.getMessage());
		}
		return new Product();
	}

	/**
	 * Method executed before persist a Product to perform some validations.
	 * 
	 * @param productToSave
	 * @throws Exception
	 * @throws ProductException
	 */
	// TODO improve these validations
	private void validateProduct(Product productToSave) throws Exception {
		if (productToSave.getId() != null) {
			if (productToSave.getParent() != null) {
				if (productToSave.equals(productToSave.getParent())) {
					throw new Exception("A product can't be it's parent");
				}
			}

			if (productToSave.getImages() != null && !productToSave.getImages().isEmpty()) {
				for (Image image : productToSave.getImages()) {
					image.setProduct(productToSave);
				}
			}

			return;
		}

		if (productToSave.getImages() != null && !productToSave.getImages().isEmpty()) {
			Set<Image> imageSet = new HashSet<>(productToSave.getImages());
			productToSave.removeAllImages();
			productToSave = productRepository.save(productToSave);

			for (Image image : imageSet) {
				image.setProduct(productToSave);
				imageService.saveImage(image);
			}
		}

	}

	/**
	 * Method to update a specific Product.
	 * 
	 * @param id
	 * @param updaterProduct
	 * @return
	 */
	// TODO- Copy properties
	public Product updateProduct(Integer id, Product updaterProduct) {
		Product foundProduct = getByIdWithFetch(id);
		if (foundProduct != null) {
			if (!StringUtils.isEmpty(updaterProduct.getName())) {
				foundProduct.setName(updaterProduct.getName());
			}
			if (updaterProduct.getImages() != null && !updaterProduct.getImages().isEmpty()) {
				updateImages(foundProduct, updaterProduct);
			}
			if (updaterProduct.getParent() != null) {
				foundProduct.setParent(updaterProduct.getParent());
			}
			return saveProduct(foundProduct);
		}
		return null;
	}

	private void updateImages(Product foundProduct, Product productToUpdate) {
		foundProduct.removeAllImages();
		for (Image img : productToUpdate.getImages()) {
			foundProduct.addImages(img);
		}
	}

	/**
	 * Method to delete a specific Product.
	 * 
	 * @param id
	 * @throws ProductException
	 */
	// TODO tratamento de excecao.
	public void deleteProduct(Integer id) {
		productRepository.delete(id);
	}

}
