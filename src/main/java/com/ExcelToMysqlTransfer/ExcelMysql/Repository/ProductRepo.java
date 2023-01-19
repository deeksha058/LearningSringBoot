package com.ExcelToMysqlTransfer.ExcelMysql.Repository;

import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
