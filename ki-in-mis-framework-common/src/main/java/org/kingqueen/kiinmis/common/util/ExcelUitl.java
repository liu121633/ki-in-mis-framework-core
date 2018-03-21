package org.kingqueen.kiinmis.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


/**
 * @ClassName ExcelUitl
 * @description Excel读写类
 * @author 刘洪君
 * @date 2017年12月3日上午12:32:20
 * @version V1.0
 */
public class ExcelUitl {

	public String readExcel(String filePath) {
		try {
			// 1、构造excel文件输入流对象
			InputStream is = new FileInputStream(filePath);
			// 2、声明工作簿对象
			Workbook rwb = Workbook.getWorkbook(is);
			// 3、获得工作簿的个数,对应于一个excel中的工作表个数
			rwb.getNumberOfSheets();
			// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称
			Sheet oFirstSheet = rwb.getSheet(0);
			
			int rows = oFirstSheet.getRows();// 获取工作表中的总行数

			int columns = oFirstSheet.getColumns();// 获取工作表中的总列数

			// 用于保存数据名称
			Map<Integer, String> columnName = new HashMap<Integer, String>();

			// 模板规则 第一行是属性名称 在xls 是隐藏的
			for (int i = 0; i < columns; i++) {
				Cell oCell = oFirstSheet.getCell(i, 0);
				columnName.put(i, oCell.getContents());
			}

			List date = new ArrayList();

			// i=2 是因为有两行 不是数据行 要排除掉 行从0开始 0+2=2
			for (int i = 2; i < rows; i++) {
				// 创建一个行
				Map<String, String> rowdate = new HashMap<String, String>();
				
				String result=oFirstSheet.getCell(0, i).getContents();
				if(result.equals("")){
					break;
				}
				
				for (int j = 0; j < columns; j++) {
					
					// 需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行
					Cell oCell = oFirstSheet.getCell(j, i);

					// columnName.get(j) 得到对应的列名称
					rowdate.put(columnName.get(j), oCell.getContents());
					
					
					

				}
				// 把这一行数据 加到list里面
				date.add(rowdate);
			}

			return JSON.toJSONString(date);

		} catch (Exception e) {

		}
		return null;
}
	}