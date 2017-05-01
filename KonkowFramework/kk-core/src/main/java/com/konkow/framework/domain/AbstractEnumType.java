package com.konkow.framework.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * Hibernate's user type for Enum.
 *
 * @param <E>
 *            Type of returned class
 * @param <V>
 *            Type of stored value
 */
public abstract class AbstractEnumType<E, V> implements UserType, Serializable {
	private static final long serialVersionUID = 1L;

	public abstract int[] sqlTypes();

	public abstract Class<E> returnedClass();

	public boolean equals(Object x, Object y) {
		return x == y;
	}

	public Object deepCopy(Object value) {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws SQLException {
		V value = getValue(resultSet, names[0]);
		try {
			return resultSet.wasNull() ? null : createObject(value);
		} catch (Exception e) {
			throw new HibernateException(e);
		}
	}

	/**
	 * Gets stored value from result set.
	 *
	 * @param resultSet
	 *            result set
	 * @param firstColumn
	 *            name of the first column
	 * @return stored value
	 * @throws HibernateException
	 *             error
	 * @throws SQLException
	 *             error
	 */
	protected abstract V getValue(ResultSet resultSet, String firstColumn) throws SQLException;

	/**
	 * Creates instance of object from the given value.
	 *
	 * @param value
	 *            stored value
	 * @return instance of object
	 */
	protected abstract Object createObject(V value);

	public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws SQLException {
		if (value == null) {
			statement.setNull(index, sqlTypes()[0]);
		} else {
			setValue(statement, value, index);
		}
	}

	/**
	 * Sets value to prepared statement.
	 *
	 * @param statement
	 *            prepared statement
	 * @param value
	 *            value
	 * @param index
	 *            index
	 * @throws HibernateException
	 *             error
	 * @throws SQLException
	 *             error
	 */
	protected abstract void setValue(PreparedStatement statement, Object value, int index) throws SQLException;

	public Object assemble(Serializable cached, Object owner) {
		return cached;
	}

	public Serializable disassemble(Object value) {
		return (Serializable) value;
	}

	public int hashCode(Object x) {
		return x.hashCode();
	}

	public Object replace(Object original, Object target, Object owner) {
		return original;
	}
}
