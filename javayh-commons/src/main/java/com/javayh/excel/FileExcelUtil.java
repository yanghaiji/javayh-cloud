package com.javayh.excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.javayh.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author Dylan Yang
 * @Description: FileExcelUtil
 * @Title: FileExcelUtil
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 20:03
 */
@Slf4j
public class FileExcelUtil {

    /**
     * 导出excel 带有标题
     *
     * @param list
     * @param title
     * @param sheetName
     * @param pojoClass
     * @param fileName
     * @param isCreateHeader
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,
                                   String fileName, boolean isCreateHeader,
                                   HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    /**
     * 导出excel
     *
     * @param list
     * @param title
     * @param sheetName
     * @param pojoClass
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass,
                                   String fileName, HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    /**
     *
     * @param list
     * @param fileName
     * @param response
     */
    public static void exportExcel(List<Map<String, Object>> list, String fileName,
                                   HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    /**
     * 导出excel
     *
     * @param list
     * @param pojoClass
     * @param fileName
     * @param response
     * @param exportParams
     */
    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName,
                                      HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null)
            ;
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 流控制
     * @param fileName
     * @param response
     * @param workbook
     */
    private static void downLoadExcel(String fileName, HttpServletResponse response,
                                      Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 默认风格设置
     *
     * @param list
     * @param fileName
     * @param response
     */
    private static void defaultExport(List<Map<String, Object>> list, String fileName,
                                      HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null)
            ;
        downLoadExcel(fileName, response, workbook);
    }

    /**
     * 导入Excel
     *
     * @param filePath
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
        return list;
    }

    /**
     * 导入Excel
     *
     * @param file
     * @param titleRows
     * @param headerRows
     * @param pojoClass
     * @return
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows,
                                          Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * 保存excel到文件到本地.
     *
     * @param path 本地路径
     * @param list 内容列表
     * @param pojoClass pojo类
     */
    public static void saveExcelToLocal(String path, List<?> list, Class<?> pojoClass){
        ExportParams exportParams = new ExportParams();
        int size = list.size();
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);

        try (OutputStream outputStream = new FileOutputStream(path)) {
            workbook.write(outputStream);
            outputStream.flush();
            log.info("写入数据到excel完成,总共{}条数据", size);
        } catch (Exception e) {
            log.error("写入数据到excel完成异常,path是:{},异常:{}", path, e.getMessage());
        } finally {
            try {
                if(workbook != null){
                    workbook.close();
                }
            } catch (IOException e) {
                log.error("workbook关闭失败,异常:{}", e.getMessage());
            }
        }
    }
}


