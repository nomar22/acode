package com.exam.api;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

import com.exam.assembler.ImageAssembler;
import com.exam.domain.Image;
import com.exam.dto.Message;
import com.exam.service.ImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Resource to manage the Image entity operations.
 * 
 * @author RafaelRa
 *
 */
@Api
@Component
@Path("/images")
public class ImagesApi {

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageAssembler imageAssembler;

	/**
	 * Method to manage POST requests to get all Images of a specific Product.
	 * 
	 * @param product
	 * @return
	 */
	@GET
	@Path("/productImages/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Requests to get all Images of a specific Product")
	public Response getProductImages(@PathParam("id") String id) {
		if ("".equals(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Set<Image> images = imageService.getAllProductImages(Integer.valueOf(id));

		return Response.ok(imageAssembler.toResources(images)).build();
	}

	/**
	 * Method to manage GET request to get all images in the database with all its
	 * 
	 * @return
	 */
	@GET
	@ApiOperation(value = "Method to manage GET request to get all images in the database")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getAllImages() {
		List<Image> images = imageService.findAll();
		if (images == null || images.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.ok(imageAssembler.toResources(images)).build();
	}

	/**
	 * Method to manage GET request to get all images in the database with all its
	 * 
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a image with its relationships")
	public Response getImage(@PathParam("id") String id) {
		if ("".equals(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Image image = imageService.findById(Integer.valueOf(id));
		if (image == null) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.ok(imageAssembler.toResource(image)).build();
	}

	/**
	 * Method to manage POST requests to persist an Image.
	 * 
	 * @param imageToSave
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Save a new image")
	public Response save(@RequestBody Image imageToSave) {
		if (imageToSave == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}
		Image image = imageService.saveImage(imageToSave);
		if (StringUtils.isEmpty(image.getId())) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(Message.INTERNAL_SERVER_ERROR.getDescription()).build();
		}

		return Response.status(Response.Status.CREATED).entity(imageAssembler.toResource(image))
				.type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage PUT requests to update a specific Image.
	 * 
	 * @param imageToUpdate
	 * @param id
	 * @return
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update an existing image")
	public Response update(@RequestBody Image imageToUpdate, @PathParam("id") String id) {
		if (imageToUpdate == null || StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}
		Image image = imageService.updateImage(Integer.parseInt(id), imageToUpdate);
		if (image == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(Message.INTERNAL_SERVER_ERROR.getDescription()).build();
		}

		return Response.ok(imageAssembler.toResource(image)).build();
	}

	/**
	 * Method to manage DELETE requests to delete specific Image.
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete an existing image")
	public Response delete(@PathParam("id") String id) {
		imageService.deleteImage(Integer.parseInt(id));
		return Response.ok().build();
	}
}
