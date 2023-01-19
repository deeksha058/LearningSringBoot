package com.ExcelToMysqlTransfer.ExcelMysql.Helper;

import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyExcelHelper {


    // Check that file is of excel type or not .............
    public static boolean checkExcelFormat(MultipartFile file){

         String contentType = file.getContentType();

         return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    // convert excel to list of products .................

    public static List<Product> convertExcelToListOfProduct(InputStream is){

        List<Product> list = new ArrayList<>();

        try{

            // now we read apache poi wali library ........

            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber = 0;

            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()){

                Row row = iterator.next();

                if (rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid = 0;

                Product p = new Product();

                while (cells.hasNext()){

                    Cell cell = cells.next();

                    switch (cid){

                        case 0:
                            p.setStudentId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setStudentName(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setStudentFather(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cid++;

                }
                list.add(p);

            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

// ........... without library .....................
