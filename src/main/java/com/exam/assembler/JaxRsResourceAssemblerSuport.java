package com.exam.assembler;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import jersey.repackaged.com.google.common.base.Preconditions;
 
/**
 * from https://geeks-mexico.com/2017/07/24/spring-boot-rest-jersey-adding-spring-hateoas-and-mapstruct-part-3/
 * @author raidentrance
 *
 */
public abstract class JaxRsResourceAssemblerSuport<T, D extends ResourceSupport>
        extends ResourceAssemblerSupport<T, D> {
    private final Class<?> controllerClass;
 
    public JaxRsResourceAssemblerSuport(Class<?> controllerClass, Class<D> resourceType) {
 
        super(controllerClass, resourceType);
        this.controllerClass = controllerClass;
    }
 
    @Override
    protected D createResourceWithId(Object id, T entity, Object... parameters) {
        Preconditions.checkNotNull(entity);
        Preconditions.checkNotNull(id);
        D instance = instantiateResource(entity);
        instance.add(JaxRsLinkBuilder.linkTo(controllerClass).slash(id).withSelfRel());
        return instance;
    }
}