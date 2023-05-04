
package com.org.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.org.entity.CitizenPlan;
import com.org.repo.CitizenPlanRepo;
import com.org.search.SearchRequest;
import com.org.util.EmailUtils;
import com.org.util.ExcelGenerator;
import com.org.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo repo;
	@Autowired
	private ExcelGenerator excelGenerator;
	@Autowired
//injecting 
	private PdfGenerator generatePdf;
	@Autowired
	private EmailUtils email;
	

	@Override
	public List<String> getPlanName() {
// Retrieve all CitizenPlan and add their names to the list
		List<String> plans = repo.getPlanName();
		return plans;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> plans = repo.getPlanStatus();
		return plans;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String localdate
			LocalDate localDate = LocalDate.parse(startDate, formatter);
			entity.setPlanStartDate(localDate);

		}
		if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
			String endDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// convert String localdate
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);

		}
		return repo.findAll(Example.of(entity));

	}

	@Override
	public boolean expertExcel(HttpServletResponse response) throws Exception {
		File f=new File("Plans.xls");
		List<CitizenPlan> list = repo.findAll();
		excelGenerator.generateExcel(response, list,f);
		String subject="Test mail subject";
		String body="<h1>Test mail body</h1>";
		String to="xyz@gmail.com";

	
		email.sendEmail(subject, body, to, f);
		//doenload and send broser then it download
		f.delete();
		return true;
	}

	@Override
	public boolean expertPdf(HttpServletResponse response) throws Exception {
		File f= new File("Plans.pdf");
		List<CitizenPlan> list = repo.findAll();
		//it will create file in local system
		generatePdf.generatPdf(response, list,f);
		String subject="Sending PDF ....";
		String body="<h1>Pdf sending  Attachment</h1>";
		String to="xyz@gmail.com";

	//sending fpf attachment
		email.sendEmail(subject, body, to, f);
		//doenload and send broser then it download
		f.delete();

		 
		return true;
	}
}
