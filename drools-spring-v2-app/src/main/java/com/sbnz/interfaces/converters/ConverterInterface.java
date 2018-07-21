/**
 * 
 */
package com.sbnz.interfaces.converters;

/**
 * @author Nikola
 *
 */
public interface ConverterInterface<D, E> {

	public D entityToDto(E entity);

	public E DtoToEntity(D dto);

}
