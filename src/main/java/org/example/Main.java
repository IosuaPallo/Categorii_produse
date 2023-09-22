package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook= new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Produse");
        Document document = Jsoup.connect("https://www.emag.ro").get();
        Elements content = document.select(".megamenu-list");
        Elements products = content.select("li[data-id]");
        int rowNumber=0;
      for(Element element:products){
            Row row = sheet.createRow(rowNumber++);
            Cell cell = row.createCell(0);
            cell.setCellValue((String)element.text());
        }
        try{
            String argument = args[0];
            FileOutputStream fout = new FileOutputStream(new File(argument));
            workbook.write(fout);
            fout.close();
            System.out.println("Fisierul a fost scris cu succes!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}