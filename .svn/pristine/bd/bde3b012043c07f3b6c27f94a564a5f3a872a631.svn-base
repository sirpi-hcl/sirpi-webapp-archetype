#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.web.vo;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;

import ${package}.entity.BaseEntity;

public abstract class BaseVO<E extends Object> implements Serializable {

	private Class<E> entity;

	protected BaseVO(Class<E> entity) {
		this.entity = entity;

		// Setting a default converter for date.
		DateConverter dateConverter = new DateConverter(null);
		dateConverter.setPattern("MM/dd/yyyy");
		
		ConvertUtils.register(dateConverter, Date.class);
	}

	public E toEntity() {
		E entity = null;

		try {
			entity = this.entity.newInstance();

			copyValueToEntityProperties(this, entity);

		} catch (Exception ie) {
			ie.printStackTrace();
		}

		return entity;
	}

	public Object toDTO(E entity) {

		try {
			copyEntityToValueObjectProperties(entity, this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return this;

	}

	
	/**
	 * Copies the properties from Value object to Entity object.
	 * 
	 * */
	public void copyValueToEntityProperties(Object valueObject,
			Object entityObject) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			InstantiationException {

		// Get the list of fields from source objects
		Field[] valueObjectFields = valueObject.getClass().getDeclaredFields();

		Class<?> voType = BaseVO.class;

		for (Field valueObjectField : valueObjectFields) {
			if (!voType.isAssignableFrom(valueObjectField.getType())
					&& !Modifier.isFinal(valueObjectField.getModifiers())
					&& !Modifier.isStatic(valueObjectField.getModifiers())) {
				
				BeanUtils.setProperty(entityObject,	valueObjectField.getName(),	BeanUtils.getProperty(valueObject, valueObjectField.getName()));
			}
		}
	}
	
	
	/**
	 * It evaluates and iterates the collection parameter and transform the object 
	 * item to entity object. 
	 * 
	 * @return Collection<? extends BaseEntity>-Any object that extends BaseEntity interface.
	 * 		
	 * */
	public Collection<? extends BaseEntity> collToEntity(Collection<? extends BaseVO> objColl){
		// Output collection with entities
		Collection<BaseEntity> entityColl = null;
		//
		// If the collection parameter is not null and empty
		//
		if(objColl != null && !objColl.isEmpty()){
			// Check for instance of Set or List class
			if(objColl instanceof Set){
				entityColl = new HashSet<BaseEntity>();
			}
			else if(objColl instanceof List){
				entityColl = new ArrayList<BaseEntity>();
			}
			
			Iterator<? extends BaseVO> iter = objColl.iterator();
			
			BaseVO valueObj; 
			
			BaseEntity entityObj;
			
			while(iter.hasNext()){
				valueObj = (BaseVO) iter.next();
				// Transform the DTO object to entity
				entityObj = (BaseEntity) valueObj.toEntity();
				
				entityColl.add(entityObj);
			}
						
		}
				
		return entityColl;
	}
	
	
	/**
	 * Copies the properties from Entity object to Value object.
	 * 
	 * */
	public void copyEntityToValueObjectProperties(Object entityObject,
			Object valueObject) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			InstantiationException, SecurityException, NoSuchFieldException {

		// Get the list of fields from source objects
		Field[] entityObjectFields = entityObject.getClass()
				.getDeclaredFields();

		// Reference of BaseEntity class type
		Class<?> entityType = BaseEntity.class;

		// Iteration of entity properties
		for (Field entityField : entityObjectFields) {
			//
			// Checks whether the entity field is an object and is of type
			// BaseEntity class.
			//
			if (!entityType.isAssignableFrom(entityField.getType())
					&& !Modifier.isFinal(entityField.getModifiers())
					&& !Modifier.isStatic(entityField.getModifiers())) {
			
				BeanUtils.setProperty(valueObject,	entityField.getName(),	BeanUtils.getProperty(entityObject,	entityField.getName()));
			}
		}
	}

	
	/**
	 * Checks the associated object for bi-directional relationship object.
	 * Returns true if it finds.
	 * 
	 * @return true-if the associated field contains mapped by, false otherwise.
	 * */
	protected boolean isReverselyAssociated(Field associatedField) {

		if (associatedField.getAnnotation(OneToOne.class) != null) {
			if (!StringUtils.isEmpty(associatedField.getAnnotation(
					OneToOne.class).mappedBy()))
				return true;

		} else if (associatedField.getAnnotation(OneToMany.class) != null) {
			if (!StringUtils.isEmpty(associatedField.getAnnotation(
					OneToMany.class).mappedBy()))
				return true;
		} else if (associatedField.getAnnotation(ManyToMany.class) != null) {
			if (!StringUtils.isEmpty(associatedField.getAnnotation(
					ManyToMany.class).mappedBy()))
				return true;
		}

		return false;
	}


}

