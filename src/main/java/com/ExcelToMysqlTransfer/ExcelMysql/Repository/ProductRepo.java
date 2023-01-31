package com.ExcelToMysqlTransfer.ExcelMysql.Repository;

import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository <Product,Integer> {

        @Query(value = "SELECT * FROM product order by student_id limit :limit_value offset :offset_value" ,nativeQuery = true)
        public List<Product> productData(Integer limit_value , Integer offset_value);


}
