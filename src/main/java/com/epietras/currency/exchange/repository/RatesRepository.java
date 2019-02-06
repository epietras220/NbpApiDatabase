package com.epietras.currency.exchange.repository;

import com.epietras.currency.exchange.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rate, Integer> {

}
