#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.impl;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import ${package}.repository.BaseRepository;

/**
 * @author venkataramanan-k
 * @version 1.0.0
 * @since 08-Oct-2012
 * 
 * BaseRepositoryImpl class extends SimpleJpaRepository to initialize the JPA factory object for all domain repository objects,
 * which implements BaseRepository interface.
 * 
 * */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	// Entity Manager attribute
	private EntityManager entityManager;
	
	
	/**
	 * Object Constructor
	 * 
	 * @param Class<T> T denotes the entity class
	 * @param EntityManager JPA entitymanager object.
	 * */
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager){
		// Initializing super class
		super(domainClass, entityManager);
		
		this.entityManager = entityManager;
	}
	
	
}
