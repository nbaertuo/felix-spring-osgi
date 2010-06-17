package org.ertuo.linliqin.biz.excel;

import java.io.*;
import java.util.List;

import java.util.logging.Logger;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;

/**
 * 封装Excel模板和报表的操作
 * 
 * @author zgw
 * 
 */
public class ExcelTemplateOperation {

	
	private final static Logger log = Logger
	.getLogger(ExcelTemplateOperation.class);
	
	private WritableWorkbook wwb;
	private WritableSheet currentSheet;
	private WritableCellFormat wcfNumber;
	private WritableCellFormat wcfLabel;
	private WritableCellFormat wcfTitle;

	/**
	 * 构造函数，读取模板创建Excel报表,currentSheet默认指向sheet0
	 * 
	 * @param template
	 *            Excel模版文档
	 * @param out
	 *            Excel报表文件名
	 * @
	 */
	public ExcelTemplateOperation(String template, String out)  {
		InputStream ins;
		Workbook sourcebook;
		try {
			ins = new FileInputStream(template);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			sourcebook = Workbook.getWorkbook(ins);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File outFile = new File(out);
		try {
			wwb = Workbook.createWorkbook(outFile, sourcebook);
			NumberFormat nf = new NumberFormat("#0.00");
			wcfNumber = new WritableCellFormat(nf);
			wcfNumber.setBorder(Border.ALL, BorderLineStyle.THIN);
			// 把水平对齐方式指定为居中
			wcfNumber.setAlignment(jxl.format.Alignment.CENTRE);
			// 把垂直对齐方式指定为居中
			wcfNumber.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 设置自适应大小
			wcfNumber.setShrinkToFit(true);

			wcfLabel = new WritableCellFormat();
			wcfLabel.setBorder(Border.ALL, BorderLineStyle.THIN);

			wcfLabel.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfLabel.setShrinkToFit(true);

			WritableFont wf = new WritableFont(WritableFont.ARIAL, 15,
					WritableFont.BOLD, false);
			wcfTitle = new WritableCellFormat(wf);
			;
			wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
			wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		currentSheet = this.wwb.getSheet(0);
	}

	/**
	 * 在报表中插入一个数字,格式不变
	 * 
	 * @param col
	 *            列 从0开始
	 * @param row
	 *            行 从0开始
	 * @param value
	 *            要插入的值
	 * @
	 */
	public void insert(int col, int row, double value)  {
		Number num = new Number(col, row, value, wcfNumber);
		try {
			this.currentSheet.addCell(num);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在报表中插入一个数字,格式不变
	 * 
	 * @param col
	 *            列 从0开始
	 * @param row
	 *            行 从0开始
	 * @param value
	 *            要插入的值
	 * @
	 */
	public void insert(int sheetNum,int col, int row, double value)  {
		Number num = new Number(col, row, value, wcfNumber);
		try {
			this.wwb.getSheet(sheetNum).addCell(num);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在报表中插入一个字符串,格式不变
	 * 
	 * @param col
	 *            列 从0开始
	 * @param row
	 *            行 从0开始
	 * @param value
	 *            要插入的值
	 * @
	 */
	public void insert(int col, int row, String value)  {
		Label label = new Label(col, row, value, wcfLabel);
		try {
			this.currentSheet.addCell(label);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 在报表中插入一个字符串,格式不变
	 * 
	 * @param col
	 *            列 从0开始
	 * @param row
	 *            行 从0开始
	 * @param value
	 *            要插入的值
	 * @
	 */
	public void insert(int sheetNum,int col, int row, String value)  {
		Label label = new Label(col, row, value, wcfLabel);
		try {
			this.wwb.getSheet(sheetNum).addCell(label);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void insertTitle(int col, int row, String title)
			 {
		Label label = new Label(col, row, title, wcfTitle);
		try {
			this.currentSheet.addCell(label);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回单元格的值
	 * 
	 * @param col
	 * @param row
	 * @return
	 */
	public String getCellContent(int col, int row) {
		return this.currentSheet.getCell(col, row).getContents();
	}

	/**
	 * @param col1
	 * @param row1
	 * @param col2
	 * @param row2
	 */
	public void merge(int col1, int row1, int col2, int row2)
			 {

		try {
			this.currentSheet.mergeCells(col1, row1, col2, row2);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入并关闭报表 1.修改表报后必须关闭，否则不能能写入文件中 2.报表关闭后不能再操作
	 * 
	 * @
	 */
	public void close()  {
		try {
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 合并col列上相同的单元格
	 * 
	 * @param col
	 *            从0开始的列号
	 * @
	 */
	public void mergeSameOnColumn(int col, int endrow) {
		try {
			Cell[] cells = this.currentSheet.getColumn(col);
			int i = 0;
			while (i < cells.length && i < endrow) {
				int j = i;
				String value = cells[i].getContents().trim();
				while (j < cells.length
						&& cells[j].getContents().trim().equals(value)) {
					j++;
				}
				if (i != j - 1) {
					this.currentSheet.mergeCells(col, i, col, j - 1);
					this.insert(col, i, value);
					i = j;
				} else {
					i++;
				}

			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置当前sheet
	 * 
	 * @param sheet
	 */
	public void setCurrentSheet(int sheet) {
		this.currentSheet = this.wwb.getSheet(sheet);
	}

	/**
	 * 读取Excel文件的内容
	 * 
	 * @param file
	 *            待读取的文件
	 * @return
	 */
	public Map readExcel(File file) {
		
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
		def rs=readSheet(sheets)
		 
		// 最后关闭资源，释放内存
		wb.close();
		return rs;
	}

	private  Map readSheet(Sheet[] sheets){
            def rs=new HashMap()
		    (0..<sheets.length).each{
			//工作区内空白xy坐标
		    def blankCells=new ArrayList()
		    def sheet=sheets[it]
			//遍历所有工作行
			(0..<sheet.rows).each{
				Cell[] cells = sheet.getRow(it)
				def row=it
				//遍历所有工作格
				(0..<cells.length).each{
					//取得当前cell的内容
					def content=cells[it].contents
					if(content.contains("\$")){
						def yx=[row,it]
						blankCells.add(yx)
					}
					if(StringUtils.isBlank(content)){
						
					}
				}
			}
		    rs.put it, blankCells
		    }
		    return rs 
	}

	/**
	 * 测试
	 * 
	 */
	public static void main(args) {
		try {
			String inFile = "D:/develop/sources/groovy/j-pg02155-source/Book1.xls";
			String outFile = "D:/develop/sources/groovy/j-pg02155-source/Book12.xls";
			ExcelTemplateOperation xls = new ExcelTemplateOperation(inFile, outFile);
			int i = 5;
			while (i++ < 10) {
				xls.insert(1, i, "fuck"+i);
			}
			xls.mergeSameOnColumn(0, 10);
			xls.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
