package com.org.search;

import lombok.Data;
@Data
public class SearchRequest {
	//search scenario
	// form binding class used  to capture form data
	private String planName;
	private String planStatus;
	private String gender;
	private String startDate;
	private String endDate;
	

}
