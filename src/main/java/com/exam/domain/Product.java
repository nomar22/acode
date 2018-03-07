package com.exam.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Image entity class.
 * 
 * @author RafaelRa
 *
 */
@Entity
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedEntityGraph(name = "Product.detail", attributeNodes = { @NamedAttributeNode("parent"),
		@NamedAttributeNode("images") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Set<Image> images;

	@ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "parentId")
	private Product parent;

	public Product(Integer id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public Product(ProductBuilder builder) {
		this();
		this.id = builder.getId();
		this.name = builder.getName();
		this.images = builder.getImages();
		this.parent = builder.getParent();
	}

	public void addImages(Image... object) {
		if (this.images == null) {
			this.images = new HashSet<>();
		}
		for (Image img : object) {
			img.setProduct(this);
			this.images.add(img);
		}
	}

	public void removeImage(Image image) {
		if (images != null) {
			images.remove(image);
		}
	}

	public void removeAllImages() {
		if (images != null) {
			images.clear();
		}
	}

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getParent() {
		return parent;
	}

	public void setParent(Product parent) {
		this.parent = parent;
	}

	public Set<Image> getImages() {
		return images != null ? new HashSet<Image>(images) : null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static class ProductBuilder {
		private Integer id;
		private String name;

		private Set<Image> images;

		private Product parent;

		public ProductBuilder(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public ProductBuilder setImages(Set<Image> images) {
			this.images = images;
			return this;
		}

		public ProductBuilder setParent(Product parent) {
			this.parent = parent;
			return this;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Image> getImages() {
			return images;
		}

		public Product getParent() {
			return parent;
		}

		public Product build() {
			return new Product(this);
		}
	}

}
