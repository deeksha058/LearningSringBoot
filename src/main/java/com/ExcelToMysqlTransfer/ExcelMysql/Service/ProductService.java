package com.ExcelToMysqlTransfer.ExcelMysql.Service;

import com.ExcelToMysqlTransfer.ExcelMysql.Helper.NoDependencyHelper;
import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import com.ExcelToMysqlTransfer.ExcelMysql.Repository.ProductRepo;
import com.ExcelToMysqlTransfer.ExcelMysql.Sechedular.ProductScheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    // below two function work with the help of repository...

    // ............... without any dependency ...........................


    public void save(MultipartFile file){

        try {

            List<Product> productList = NoDependencyHelper.convertExcelToList(file.getInputStream());

            this.productRepo.saveAll(productList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    int offset = 0;
    int limit = 3;

    @Scheduled(fixedRate = 1000)
    public List<Product> getdata() {

        List<Product> Products = productRepo.productData(limit ,offset);
        offset = offset + limit;
        System.out.println(Products);

        // it should be write in sheet ........
        // at specific condition schedular stop......

        return Products;

    }

    // .......................


    // using paging in spring boot using limit and offset .......

    public List<Product> getAllProduct(Integer pagenumber, Integer pagesize){

        Pageable p = PageRequest.of(pagenumber,pagesize );
        Page<Product> productPage = productRepo.findAll(p);
        List<Product> pageContent = productPage.getContent();

        return pageContent;
    }

    int count = 3421;
    Product p = new Product();
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    @Scheduled(fixedRate = 100)
    public void insertData(){

        StringBuilder sb = new StringBuilder(6);
        StringBuilder bs = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {

            int index = (int)(AlphaNumericString.length() * Math.random());
            int index1 = (int)(AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
            bs.append(AlphaNumericString.charAt(index1));
        }

        p.setStudentId(count);
        p.setStudentName(sb.toString());
        p.setStudentFather(bs.toString());

        Product pp = this.productRepo.save(p);

        System.out.println(pp);

        // fill data using rand function
        // 6 word ki string form karni hai where all latter have equal probability

        count++;

    }
}

// update file in the database


