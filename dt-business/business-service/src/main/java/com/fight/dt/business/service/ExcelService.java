package com.fight.dt.business.service;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by tpx on 2017/7/29.
 */
public interface ExcelService {
    /**
     * 导出excel
     *
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param headList   excel的标题备注名称
     * @param fieldList  excel的标题字段（与数据中map中键值对应）
     * @param dataList   excel数据
     * @throws Exception
     */
    public XSSFWorkbook createExcel(String excel_name, String[] headList,
                                    String[] fieldList, List<Map<String, Object>> dataList)
            throws Exception;

    /**
     * 要求excel版本在2007以上
     *
     * @param fileInputStream 文件信息
     * @return
     * @throws Exception
     */
    List<List<Object>> readExcel(InputStream fileInputStream) throws Exception;

}
