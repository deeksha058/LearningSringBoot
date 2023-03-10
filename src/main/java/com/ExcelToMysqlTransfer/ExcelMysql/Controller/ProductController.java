package com.ExcelToMysqlTransfer.ExcelMysql.Controller;

import com.ExcelToMysqlTransfer.ExcelMysql.Helper.NoDependencyHelper;
import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import com.ExcelToMysqlTransfer.ExcelMysql.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")  // * means client from any origin can use it.....
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){

        if (NoDependencyHelper.checkExcelFormat(file)){

             this.productService.save(file);

             return ResponseEntity.ok(Map.of("message" , "File is Uploaded and data is successfully save to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This is not a valid request , Please upload excel file only");
    }

    @GetMapping ("/product")
    public List<Product> getAllProduct(
            @RequestParam(value = "pageNumber" ,defaultValue = "0", required = false) Integer PageNumber,
            @RequestParam(value = "pageSize" , defaultValue = "5", required = false)Integer  PageSize
    )  {

        try {

            System.out.println("iam in product controller");
            return this.productService.getAllProduct(PageNumber , PageSize);

        } catch (Exception e) {
            System.out.println("iam in product controller");
            throw new RuntimeException(e);
        }

    @GetMapping ("/product/hit")
    public List<Product> getProduct()  {
        List<Product> products = productService.getdata();
        return products;
    }

//    @GetMapping ("/product")
//    public List<Product> getAllProduct()  {
//           return this.productService.getAllProduct();
//    }

}

//.............................file parsing with dynamic column  ..................................
