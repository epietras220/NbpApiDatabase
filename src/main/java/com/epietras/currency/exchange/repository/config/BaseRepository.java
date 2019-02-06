package com.epietras.currency.exchange.repository.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.LockModeType;
import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void refresh(T entity);

    T findByIdAndLock(ID id, LockModeType lockModeType, int lockSecondsTimeout);

    void lockAndRefresh(T entity, LockModeType lockModeType, int lockSecondsTimeout);

    void lock(T entity, LockModeType lockModeType, int lockSecondsTimeout);
}