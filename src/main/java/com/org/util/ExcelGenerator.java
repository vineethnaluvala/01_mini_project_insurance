package com.org.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.entity.CitizenPlan;
import com.org.repo.CitizenPlanRepo;

@Component
public class ExcelGenerator {
	@Autowired
	private CitizenPlanRepo repo;

	public void generateExcel(HttpServletResponse response,	List<CitizenPlan> records,File file) throws Exception {
		Workbook workbook = new HSSFWorkbook();

		Sheet sheet = workbook.createSheet("Plans-data");
		// create row index starts 0
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("CitizeName");
		headerRow.createCell(2).setCellValue("PlanName");

		headerRow.createCell(3).setCellValue("PlanStatus");
		headerRow.createCell(4).setCellValue("PlanStart Date");
		headerRow.createCell(5).setCellValue("Plane End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");
		// retrrive all the records from the table

		//List<CitizenPlan> records = repo.findAll();
		// foreach loop to retrieve all records
		int dataRowIndex = 1;
		for (CitizenPlan plan : records) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			if (null != plan.getPlanStartDate()) {
				dataRow.createCell(4).setCellValue(plan.getPlanStartDate() + "");

			} else {
				dataRow.createCell(4).setCellValue("N/A");
			}
			if (null != plan.getPlanEndDate()) {
				dataRow.createCell(5).setCellValue(plan.getPlanEndDate() + "");

			} else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if (null != plan.getBenefitAmt()) {
				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			} else {
				dataRow.createCell(6).setCellValue("N/A");

			}
			dataRowIndex++;

		}
		//FileOutputStream send to the mail
		FileOutputStream fos=new FileOutputStream(file);
		workbook.write(fos);
		fos.close();
		//ServletOutputStream send to the broser
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);

		workbook.close();

	}
}