package com.ExcelToMysqlTransfer.ExcelMysql.Helper;

import com.ExcelToMysqlTransfer.ExcelMysql.Product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NoDependencyHelper {

    public static boolean checkExcelFormat(MultipartFile file){

        String contentType = file.getContentType();

        return contentType.equals("text/csv");
    }

    public static List<Product> convertExcelToList(InputStream is){

        List<Product> list = new ArrayList<>();

        try{

            //create BufferedReader to read csv file

            BufferedReader br = new BufferedReader( new InputStreamReader(is));    // we use InputStreamReader instead of file reader
//            BufferedReader br = new BufferedReader( new FileReader(is.toString()));
            String strLine = "";
            StringTokenizer st = null;
            int lineNumber = 0, tokenNumber = 0;

            //read comma separated file line by line
            while( (strLine = br.readLine()) != null)
            {
                lineNumber++;
                if (lineNumber == 1){
                    continue;
                }

                //break comma separated line using ","
                st = new StringTokenizer(strLine, ",");
                Product p = new Product();

                while(st.hasMoreTokens())
                {
                    //display csv values
                    tokenNumber++;
                    if (tokenNumber == 1){
                        int t=Integer.parseInt(st.nextToken());
                        p.setStudentId(t);
                    } else if (tokenNumber == 2) {
                        p.setStudentName(st.nextToken());
                    } else{
                        p.setStudentFather(st.nextToken());
                    }
//
                }

                //reset token number
                tokenNumber = 0;
                list.add(p);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}

