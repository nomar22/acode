package com.exam.api;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.domain.Product;
import com.exam.dto.Message;
import com.exam.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Resource to manage the Product entity operations.
 * 
 * @author RafaelRa
 *
 */
@Api
@Component
@Path("/products")
public class ProductsApi {

	@Autowired
	private ProductService productService;

	/**
	 * Method to manage GET request to get a specific Product with all its specified
	 * relationships.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get a product with its relationships")
	public Response getProductWithFetch(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Product product = productService.getByIdWithFetch(Integer.parseInt(id));
		if (product == null || StringUtils.isEmpty(product.getId())) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(product).type(MediaType.APPLICATION_JSON_VALUE).build();

	}

	/**
	 * Method to manage GET request to get a specific Product without its specified
	 * relationships.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/nochild/{id}")
	@ApiOperation(value = "Get product without its relationships")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getProductExcludingRelationships(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Product product = productService.getByIdExcludingRelationships(Integer.parseInt(id));
		if (product == null || StringUtils.isEmpty(product.getId())) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(product).type(MediaType.APPLICATION_JSON_VALUE).build();

	}

	/**
	 * Method to manage GET request to get all products in the database with all its
	 * specified relationships.
	 * 
	 * @return
	 */
	@GET
	@ApiOperation(value = "Method to manage GET request to get all products in the database with all its relationships")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getAllProducts() {
		Set<Product> products = productService.getAllWithFetch();
		if (products == null || products.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(products).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage GET request to get all products in the database without its
	 * specified relationships.
	 * 
	 * @return
	 */
	@GET
	@Path("/nochild")
	@ApiOperation(value = "Method to manage GET request to get all products in the database without its relationships")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getAllProductsNoChilds() {
		Set<Product> products = productService.getAllExcludingRelationships();
		if (products == null || products.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(products).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage POST request to get all specified relationships of a
	 * specific Product.
	 * 
	 * @param product
	 * @return
	 */
	@GET
	@Path("/child/{id}")
	@ApiOperation(value = "Get all child of products")
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response getProductChilds(@PathParam("id") String id) {
		if ("".equals(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Set<Product> products = productService.getAllProductChild(Integer.valueOf(id));

		return Response.status(Response.Status.OK).entity(products).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage POST request to persist a Product.
	 * 
	 * @param product
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Persist a Product validating it")
	public Response save(@RequestBody Product product) {
		Product productToSave = productService.saveProduct(product);
		if (productToSave == null || StringUtils.isEmpty(productToSave.getId())) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(productToSave).type(MediaType.APPLICATION_JSON_VALUE).build();
	}

	/**
	 * Method to manage PUT request to update a specific Product.
	 * 
	 * @param product
	 * @param id
	 * @return
	 */
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update an existing Product")
	public Response updateProduct(@RequestBody Product product, @PathParam("id") String id) {
		if (product == null || StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		Product productToUpdate = productService.updateProduct(Integer.parseInt(id), product);
		if (productToUpdate == null || StringUtils.isEmpty(productToUpdate.getId())) {
			return Response.status(Response.Status.NOT_FOUND).entity(Message.NOT_FOUND.getDescription()).build();
		}

		return Response.status(Response.Status.OK).entity(productToUpdate).type(MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	/**
	 * Method to manage DELETE request to delete a specific Product.
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		if (StringUtils.isEmpty(id)) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Message.BAD_REQUEST.getDescription()).build();
		}

		try {
			productService.deleteProduct(Integer.parseInt(id));
			return Response.status(Response.Status.OK).entity(Message.SUCCESS.getDescription())
					.type(MediaType.APPLICATION_JSON_VALUE).build();
		} catch (DataIntegrityViolationException ex) {
			return Response.status(Response.Status.FORBIDDEN).entity(Message.ENTITY_INTEGRITY.getDescription()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(Message.INTERNAL_SERVER_ERROR.getDescription()).build();
		}
	}

}
