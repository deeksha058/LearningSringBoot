package com.ExcelToMysqlTransfer.ExcelMysql.Service;

import com.ExcelToMysqlTransfer.ExcelMysql.Helper.NoDependencyHelper;
import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import com.ExcelToMysqlTransfer.ExcelMysql.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
    int count2 = 0;

//    Thread thread = new Thread();
    @Value("${file.name}")
    private File fileNameUrl;

    @Scheduled(fixedRate = 1000)
    public List<Product> getdata()  throws InterruptedException{

        List<Product> Products = productRepo.productData(limit ,offset);
        offset = offset + limit;


        try {

         BufferedWriter bwr = new BufferedWriter(new FileWriter( fileNameUrl,true));

          for(Product it : Products){
                StringBuilder sb = new StringBuilder();
                sb.append(it);
                System.out.println(sb);
                bwr.newLine();
                bwr.append(sb);
            }
            bwr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Products);
        if(count2%3 ==0){
            Thread.sleep(5000);                                           //  agar kisi site main specific time pr bahut jada trafic aat ahai to usko hum iski             // permanenetly stop nhi kr sakte hai
        }


        count2++;
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


