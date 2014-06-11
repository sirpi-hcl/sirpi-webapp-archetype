#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import ${package}.repository.BaseRepository;

/**
 * @author venkataramanan-k
 * @version 1.0.0
 * 
 * Signature : FactoryBean<Repository, DomainClass, ID>
 * 
 * */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID>  {
	
	/**
	 * Protected factory support object. It creates repository factor object
	 * 
	 * @param EntityManager JPA entity manager object.
	 * */
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager){
		
		return new BaseRepositoryFactory(entityManager);
	}
	
	
	/**
	 * Static class for repository factory 
	 * 
	 * */
	private static class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory{
		
		private EntityManager entityManager;
		
		public BaseRepositoryFactory(EntityManager entityManager){
			super(entityManager);
			
			this.entityManager = entityManager;
		}
		
		/**
		 * Get Target Repository
		 * 
		 * */
		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new BaseRepositoryImpl<T, Serializable>((Class<T>) metadata.getDomainType(), entityManager);
		}
		
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			// TODO Auto-generated method stub
			return BaseRepository.class;
		}
		
	}
	
}
