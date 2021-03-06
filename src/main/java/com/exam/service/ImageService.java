package com.exam.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.domain.Image;
import com.exam.repository.ImageRepository;

/**
 * Service to manage the connection between the Image resource and the Image
 * repository. Executes validations needed before persist data in the database.
 * 
 * @author RafaelRa
 *
 */
@Service
public class ImageService {

	private ImageRepository imageRepository;
	private Logger LOG = LoggerFactory.getLogger(ImageService.class);

	@Autowired
	public void setImageRepository(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	/**
	 * Method to get an specific Image.
	 * 
	 * @param id
	 * @return
	 */
	public Image findById(Integer id) {
		return imageRepository.findOne(id);
	}

	/**
	 * Method tofind all images
	 * 
	 * @param id
	 * @return
	 */
	public List<Image> findAll() {
		return (List<Image>) imageRepository.findAll();
	}

	/**
	 * Method to get all Images of a specifc product.
	 * 
	 * @param product
	 * @return
	 */
	public Set<Image> getAllProductImages(Integer id) {
		return imageRepository.findByProductId(id);
	}

	/**
	 * Method to persist an Image in the database.
	 * 
	 * @param image
	 * @return
	 */
	public Image saveImage(Image image) {
		Image imageToSave;
		try {
			imageToSave = imageRepository.save(image);
			return imageToSave;
		} catch (Exception e) {
			LOG.error("An error occurred during image saving: " + e.getMessage());
		}
		return new Image();
	}

	/**
	 * Method to update a specific Image.
	 * 
	 * @param id
	 * @param imageToUpdate
	 * @return
	 */
	// TODO an update copying properties
	public Image updateImage(Integer id, Image imageToUpdate) {
		Image foundImage = findById(id);
		if (foundImage != null) {
			if (imageToUpdate.getProduct() != null) {
				foundImage.setProduct(imageToUpdate.getProduct());
			}
			if (imageToUpdate.getDescription() != null) {
				foundImage.setDescription(imageToUpdate.getDescription());
			}
			return saveImage(foundImage);
		}
		return foundImage;
	}

	/**
	 * Method to delete an specific Image.
	 * 
	 * @param id
	 * @throws ImageException
	 */
	// TODO tratar excecoes
	public void deleteImage(Integer id) {
		imageRepository.delete(id);
	}

}
