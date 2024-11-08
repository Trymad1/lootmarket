package com.trymad.lootmarket.repository.game.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.trymad.lootmarket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}