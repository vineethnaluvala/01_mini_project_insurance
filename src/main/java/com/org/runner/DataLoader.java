package com.org.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.org.entity.CitizenPlan;
import com.org.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	//runner execute only  once when our application is started
	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//Food plan 
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Eric");
		c1.setGender("Male");
		c1.setPlanName("CASH");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(11));
		c1.setBenefitAmt(9000.0);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Thomas");
		c2.setGender("Male");
		c2.setPlanName("CASH");
		c2.setPlanStatus("Denied");
		c2.setPlanStartDate(LocalDate.now());
		c2.setPlanEndDate(LocalDate.now().plusMonths(7));
		c2.setDenialreason("Rental Income");
		
		
		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Lyda");
		c3.setGender("FeMale");
		c3.setPlanName("CASH");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(9));
		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
		c3.setBenefitAmt(9000.0);
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Govt Employee");
		
		
		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Nick");
		c4.setGender("Male");
		c4.setPlanName("CASH");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(3));
		c4.setBenefitAmt(60000.0);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Deepak");
		c5.setGender("Male");
		c5.setPlanName("CASH");
		c5.setPlanStatus("Denied");
		c5.setPlanStartDate(LocalDate.now());
		c5.setPlanEndDate(LocalDate.now().plusMonths(5));
		c5.setDenialreason("Rental Income");
		
		
		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Sandeep ");
		c6.setGender("Male");
		c6.setPlanName("CASH");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(6));
		c6.setPlanEndDate(LocalDate.now().plusMonths(3));
		c6.setBenefitAmt(9000.0);
		c6.setTerminationDate(LocalDate.now());
		c6.setTerminationReason("Govt Employee");
		
		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Tim");
		c7.setGender("Male");
		c7.setPlanName("CASH");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now());
		c7.setPlanEndDate(LocalDate.now().plusMonths(11));
		c7.setBenefitAmt(7000.0);
		
		List<CitizenPlan> list = Arrays.asList(c1,c2,c3,c4,c5,c6,c7);
		repo.saveAll(list);

	}

}
