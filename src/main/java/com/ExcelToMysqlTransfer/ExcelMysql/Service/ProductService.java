package com.ExcelToMysqlTransfer.ExcelMysql.Service;

import com.ExcelToMysqlTransfer.ExcelMysql.Helper.NoDependencyHelper;
import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import com.ExcelToMysqlTransfer.ExcelMysql.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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




//    .........................with dependency ..................................

//    public void save (MultipartFile file){
//
//        try {
//            System.out.println("hello this for checking ..................");
//            List<Product> productList = MyExcelHelper.convertExcelToListOfProduct(file.getInputStream());
//            System.out.println("hello this for checking ..................");
//            this.productRepo.saveAll(productList);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    // this is for returning data in json format
    public List<Product> getAllProduct(){

        return this.productRepo.findAll();

    }
}

