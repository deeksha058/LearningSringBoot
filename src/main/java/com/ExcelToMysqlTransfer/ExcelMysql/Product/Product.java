package com.ExcelToMysqlTransfer.ExcelMysql.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    private Integer StudentId;
    private String StudentName;
    private String StudentFather;

}
