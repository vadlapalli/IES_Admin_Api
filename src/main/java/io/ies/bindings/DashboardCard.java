package io.ies.bindings;

import lombok.Data;

@Data
public class DashboardCard {
	
	private Long planCnt;
	private Long approvedCnt;
	private Long deniedCnt;
	private Double beniftAmtGiven;

}
