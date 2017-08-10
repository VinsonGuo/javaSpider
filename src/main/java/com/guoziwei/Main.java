package com.guoziwei;

import com.guoziwei.http.HttpManager;
import com.guoziwei.model.Movie;
import com.guoziwei.model.Person;
import com.guoziwei.model.Subject;
import io.reactivex.Observable;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dell on 2017/8/9.
 */
public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        saveTop250();
        saveInTheaters();
    }

    private static void saveTop250() {
        List<Observable<Subject>> observables = new ArrayList<>();
        // 一共250条
        for (int i = 0; i < 5; i++) {
            observables.add(HttpManager.getService().getTop250(i * 50));
        }
        Observable.zip(observables, subjects -> {
            List<Movie> list = new ArrayList<>(250);
            for (Object o : subjects) {
                Subject s = (Subject) o;
                list.addAll(s.getSubjects());
            }
            list.sort((o1, o2) -> o2.getRating().getAverage() - o1.getRating().getAverage() > 0 ? 1 : -1);
            return list;
        }).subscribe(list -> {
            saveToExcel("top250", list);
        }, throwable -> {
            logger.error("getTop250", throwable);
        });
    }

    private static void saveInTheaters() {
        List<Observable<Subject>> observables = new ArrayList<>();
        // 100条左右
        for (int i = 0; i < 3; i++) {
            observables.add(HttpManager.getService().getInTheaters(i * 50));
        }
        Observable.zip(observables, subjects -> {
            List<Movie> list = new ArrayList<>(100);
            for (Object o : subjects) {
                Subject s = (Subject) o;
                list.addAll(s.getSubjects());
            }
//            list.sort((o1, o2) -> o2.getRating().getAverage() - o1.getRating().getAverage() > 0 ? 1 : -1);
            return list;
        })
                .flatMap(Observable::fromIterable)
                .filter(movie -> {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    // 获取到今年和明年的正在热映
                    return StringUtils.equalsAny(movie.getYear(), year + "", year + 1 + "");
                })
                .sorted((o1, o2) -> o2.getRating().getAverage() - o1.getRating().getAverage() > 0 ? 1 : -1)
                .toList()
                .subscribe(list -> {
                    saveToExcel("热映中", list);
                }, throwable -> {
                    logger.error("InTheaters", throwable);
                });
    }

    private static void saveToExcel(String sheetName, List<Movie> movies) throws Exception {
        FileInputStream in = new FileInputStream("movies.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        // 创建一个Excel文件
//        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("id");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(1);
        headCell.setCellValue("名称");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(2);
        headCell.setCellValue("评分");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("年份");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);
        headCell.setCellValue("类别");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);
        headCell.setCellValue("主演");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(6);
        headCell.setCellValue("导演");
        headCell.setCellStyle(cellStyle);


        headCell = hssfRow.createCell(7);
        headCell.setCellValue("url");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(8);
        headCell.setCellValue("图片");
        headCell.setCellStyle(cellStyle);

        // 添加数据内容
        for (int i = 0; i < movies.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            Movie movie = movies.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(movie.getId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(movie.getTitle());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(movie.getRating().getAverage());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(movie.getYear());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);
            cell.setCellValue(listToString(movie.getGenres()));
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);
            cell.setCellValue(getPersonName(movie.getCasts()));
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(6);
            cell.setCellValue(getPersonName(movie.getDirectors()));
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(7);
            cell.setCellValue(movie.getAlt());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(8);
            cell.setCellValue(movie.getImages().get("large"));
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        OutputStream outputStream = new FileOutputStream("movies.xls");
        workbook.write(outputStream);
        outputStream.close();
        in.close();
    }

    private static String getPersonName(List<Person> list) {
        StringBuilder sb = new StringBuilder();
        for (Person p : list) {
            sb.append(p.getName()).append(" ");
        }
        return sb.toString();
    }


    private static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(" ");
        }
        return sb.toString();
    }
}
