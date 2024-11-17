package com.trymad.lootmarket.repository.deal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.DealStatus;
import com.trymad.lootmarket.model.DealStatusEntity;
import java.util.Optional;

public interface DealStatusRepository extends JpaRepository<DealStatusEntity, Long> {

    Optional<DealStatusEntity> findByName(DealStatus name);

}
