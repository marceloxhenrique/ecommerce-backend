package com.api.ecommerce.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.ecommerce.models.SizeStock;

@Repository
public interface SizeStockRepository extends JpaRepository<SizeStock, UUID>{

}
