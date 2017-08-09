package com.guoziwei;

import com.guoziwei.http.HttpManager;
import com.guoziwei.model.Movie;
import com.guoziwei.model.Subject;
import io.reactivex.Observable;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/8/9.
 */
public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);

    private static int start = 0;

    public static void main(String[] args) {

        List<Observable<Subject>> observables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            observables.add(HttpManager.getService().getTop250(i * 50));
        }
        Observable.zip(observables, subjects -> {
            List<Movie> list = new ArrayList<>(500);
            for (Object o : subjects) {
                Subject s = (Subject) o;
                list.addAll(s.getSubjects());
            }
            list.sort((o1, o2) -> o2.getRating().getAverage() - o1.getRating().getAverage() > 0 ? 1 : -1);
            saveToExcel(list);
            return list;
        }).subscribe(s -> {
            logger.debug("{}", s);
        }, throwable -> {
            logger.error("getInTheaters", throwable);
        });

      /*  HttpManager.getService().getInTheaters(start)
                .map(subject -> {
                    List<Movie> subjects = subject.getSubjects();
                    saveToExcel(subjects);
                    return subject;
                })
                .subscribe(s -> {
                    logger.debug("{}", s);
                }, throwable -> {
                    logger.error("getInTheaters", throwable);
                });*/

        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }

    private static void saveToExcel(List<Movie> movies) throws Exception {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("最近热映");
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
        }

        // 保存Excel文件
        OutputStream outputStream = new FileOutputStream("movies.xls");
        workbook.write(outputStream);
        outputStream.close();
    }


}
