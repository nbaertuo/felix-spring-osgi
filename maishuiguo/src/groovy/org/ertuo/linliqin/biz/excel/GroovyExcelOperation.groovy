package org.ertuo.linliqin.biz.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GroovyExcelOperation {

	private final static Logger log = LoggerFactory
			.getLogger(GroovyExcelOperation.class);

	/**
	 * 读取Excel文件的内容
	 * 
	 * @param file
	 *            待读取的文件
	 * @return
	 */
	public static String readExcel(File file) {
		
		StringBuffer sb = new StringBuffer();
		Workbook wb = null;
		try {
			// 构造 Workbook（工作薄）对象
			wb = Workbook.getWorkbook(file);
		} catch (Exception e) {
			log.error("读取excel[" + file.getAbsolutePath() + "]文件异常", e);
		}

		if (wb == null) {
			log.error("读取excel[" + file.getAbsolutePath() + "]文件不存在");
			return null;
		}

		WritableWorkbook wwb = null;
		// 获得了Workbook 对象之后，就可以通过它得到Sheet（工作表）对象了
		Sheet[] sheets = wb.getSheets();
		//遍历所有工作区
		(0..<sheets.length).each{
			readSheet(sheets[it])
			wwb.createSheet(sheets[it].name, it)
			//wwb.importSheet(sheets[it].name, it, sheets[it])
		}
		// 最后关闭资源，释放内存
		wb.close();
		return sb.toString();
	}

	private static List readSheet(Sheet sheet){
			//工作区内空白xy坐标
		    def blankCells=new ArrayList()
			//遍历所有工作行
			(0..<sheet.rows).each{
				Cell[] cells = sheet.getRow(it)
				def row=it
				//遍历所有工作格
				(0..<cells.length).each{
					//取得当前cell的内容
					def content=cells[it].contents
					if(content.contains("select")){
						println(content)
					}
					if(StringUtils.isBlank(content)){
						//println("Y="+row+" X="+it)
						def yx=[row,it]
						blankCells.add(yx)
					}
				}
			}
		return blankCells
	}

	/**
	 * 生成一个Excel文件
	 * 
	 * 
	 * 
	 * @param fileName
	 *            要生成的Excel文件名
	 */
	public static void writeExcel(String fileName) {
		WritableWorkbook wwb = null;
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook 的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);

			// 下面开始添加单元格
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					// 这里需要注意的是，在Excel中，第一个参数表示列，第二个表示行
					Label labelC = new Label(j, i, "这是第" + (i + 1) + "行，第"
							+ (j + 1) + "列");
					try {
						// 将生成的单元格添加到工作表中
						ws.addCell(labelC);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}

				}
			}

			try {
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

}
