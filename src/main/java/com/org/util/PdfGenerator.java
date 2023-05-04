package com.org.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.org.entity.CitizenPlan;

@Component
public class PdfGenerator {
	// @Autowired
	// private CitizenPlanRepo repo;

	public void generatPdf(HttpServletResponse response, List<CitizenPlan> plans, File f)
			throws Exception, IOException {
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		PdfWriter.getInstance(document, new FileOutputStream(f));
		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("LIST OF USERS", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         //title
        document.add(p);
		// Creating paragraph
		// Paragraph p = new Paragraph("Citizen Plan Info");
		PdfPTable table = new PdfPTable(6);
		table.addCell("ID");
		table.addCell("Citizen Name");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");

		// List<CitizenPlan> plans = repo.findAll();
		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");

		}

		document.add(table);
	
		document.close();

	}
}
