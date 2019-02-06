package com.epietras.currency.exchange.repository.config.hibernate;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import javax.persistence.OptimisticLockException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Log4j2
public class HibernateInterceptor extends EmptyInterceptor {

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        final int index = ArrayUtils.indexOf(propertyNames, "version");
        if (index < 0) return false;

        final Object previous = previousState[index];
        final Object current = currentState[index];

        if (previous instanceof Integer || previous instanceof Short || previous instanceof Long) {
            if (!Objects.equals(previous, current)) {
                throw new OptimisticLockException();
            }
        }
        else if (previous instanceof Timestamp) {
            if (((Timestamp)previous).compareTo((Timestamp)current) != 0) {
                throw new OptimisticLockException();
            }
        }

        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }
}