package com.exam.api;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.domain.Image;
import com.exam.domain.Product;
import com.exam.dto.Message;
import com.exam.service.ImageService;

/**
 * Resource to manage the Image entity operations.
 * @author RafaelRa
 *
 */
@Component
@Path("/images")
public class ImagesApi {

	private ImageService imageService;

	@Autowired
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Method to manage POST requests to get all Images of a specific Product.
	 * @param product
	 * @return
	 */
	@POST
	@Path("/productImages")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getProductImages(@RequestBody Product product) {
		if (product == null || StringUtils.isEmpty(product.getId())) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Set<Image> images = imageService.getAllProductImages(product);
		if (images == null || images.isEmpty()) {
			return Response.status(Response.Status.NO_CONTENT).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(images).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage POST requests to persist an Image.
	 * @param imageToSave
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response saveImage(@RequestBody Image imageToSave) {
		if (imageToSave == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription())
					.build();
		}
		Image image = imageService.saveImage(imageToSave);
		if (StringUtils.isEmpty(image.getId())) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Message.INTERNAL_SERVER_ERROR.getDescription())
					.build();
		}

		return Response.status(Response.Status.CREATED).entity(image).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage PUT requests to update a specific Image.
	 * @param imageToUpdate
	 * @param id
	 * @return
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response updateImage(@RequestBody Image imageToUpdate, @PathParam("id") String id) {
		if (imageToUpdate == null || StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}
		Image image = imageService.updateImage(Integer.parseInt(id), imageToUpdate);
		if (image == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Message.INTERNAL_SERVER_ERROR.getDescription())
					.build();
		}

		return Response.status(Response.Status.OK).entity(image).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage DELETE requests to delete specific Image. 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	//TODO tratamento excecao.
	public Response deleteImage(@PathParam("id") String id) {
			imageService.deleteImage(Integer.parseInt(id));
			return Response.status(Response.Status.OK).entity(Message.SUCCESS.getDescription())
					.type(MediaType.APPLICATION_JSON_VALUE).build();
	}
}