package com.epietras.currency.exchange.repository.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private final JpaEntityInformation<T, ?> entityInformation;
    private final String databaseDialect;


    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
        final EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        final Map<String, Object> properties = emf.getProperties();
        databaseDialect = (String) properties.get(AvailableSettings.DIALECT);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
        final EntityManagerFactory emf = entityManager.getEntityManagerFactory();
        final Map<String, Object> properties = emf.getProperties();
        databaseDialect = (String) properties.get(AvailableSettings.DIALECT);
    }

    @Override
    public void refresh(T entity) {
        entityManager.refresh(entity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public T findByIdAndLock(ID id, LockModeType lockModeType, int lockSecondsTimeout) {
        if (StringUtils.equalsIgnoreCase(databaseDialect, "org.hibernate.dialect.PostgreSQLDialect")) {
            entityManager
                    .createNativeQuery("SET LOCAL lock_timeout TO '" + lockSecondsTimeout + "s';")
                    .executeUpdate();
        }

        final Map<String, Object> properties = Collections
                .singletonMap("javax.persistence.lock.timeout", lockSecondsTimeout * 1000);

        return entityManager.find(this.getDomainClass(), id, lockModeType, properties);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void lockAndRefresh(T entity, LockModeType lockModeType, int lockSecondsTimeout) {
        if (StringUtils.equalsIgnoreCase(databaseDialect, "org.hibernate.dialect.PostgreSQLDialect")) {
            entityManager
                    .createNativeQuery("SET LOCAL lock_timeout TO '" + lockSecondsTimeout + "s';")
                    .executeUpdate();
        }

        final Map<String, Object> properties = Collections
                .singletonMap("javax.persistence.lock.timeout", lockSecondsTimeout * 1000);

        entityManager.refresh(entity, lockModeType, properties);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void lock(T entity, LockModeType lockModeType, int lockSecondsTimeout) {
        if (StringUtils.equalsIgnoreCase(databaseDialect, "org.hibernate.dialect.PostgreSQLDialect")) {
            entityManager
                    .createNativeQuery("SET LOCAL lock_timeout TO '" + lockSecondsTimeout + "s';")
                    .executeUpdate();
        }

        final Map<String, Object> properties = Collections
                .singletonMap("javax.persistence.lock.timeout", lockSecondsTimeout * 1000);

        entityManager.lock(entity, lockModeType, properties);
    }

}