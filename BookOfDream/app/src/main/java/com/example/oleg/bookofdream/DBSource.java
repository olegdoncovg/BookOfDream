package com.example.oleg.bookofdream;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DBSource {
    private Context context;
    private static final String SEPARATOR = System.getProperty("line.separator");
    private List<String> bookNames;

    public DBSource(Context context) {
        this.context = context;
    }

    public List<String> getBookNamesList() {
        if (bookNames == null) {
            bookNames = new ArrayList<>();
            String[] strArr = readFromRile(context, "t0_0.png").split(SEPARATOR);
            for (String str : strArr) {
                bookNames.add(str);
            }

            for (String str : strArr) {
                bookNames.add(str);
            }

            for (String str : strArr) {
                bookNames.add(str);
            }
        }
        return bookNames;
    }

    public String getBook(int bookId, String searchText) {
        int laterID = -1;
        int bookID = bookId+1;
        String rez = null;
        if(searchText == null || searchText.length()<=0){
            laterID = searchText.toLowerCase().charAt(0)-97;
            rez = readFromRile(context, "t" + bookID + "_" + laterID + ".png");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<29;i++){
                String str = readFromRile(context, "t" + bookID + "_" + i + ".png");
                if(i>0 && str!=null && str.length()>0){
                    stringBuilder.append(SEPARATOR);
                }
                stringBuilder.append(str);
            }
        }
        return rez;
    }

    public String readFromRile(Context context, String fileName) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn, "CP1251");
            input = new BufferedReader(isr);
            String line = "";
            boolean first = true;
            while ((line = input.readLine()) != null) {
                if (first) {
                    first = false;
                } else {
                    returnString.append(SEPARATOR);
                }
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

//    public static String pathRoot = "/sdcard/system/temp/";
//    public static String readFromFile(Context contect, String nameFile) {
//        StringBuilder aBuffer = new StringBuilder("");
//        try {
//            File myFile = new File(pathRoot + nameFile);
//            FileInputStream fIn = new FileInputStream(myFile);
//            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
//            String aDataRow = "";
//            while ((aDataRow = myReader.readLine()) != null) {
//                aBuffer.append(aDataRow);
//            }
//            myReader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return aBuffer.toString();
//    }
}
