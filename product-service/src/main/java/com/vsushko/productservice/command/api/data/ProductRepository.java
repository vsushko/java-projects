package com.vsushko.productservice.command.api.data;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author vsushko
 */
public interface ProductRepository extends JpaRepository<Product, String> {
}
