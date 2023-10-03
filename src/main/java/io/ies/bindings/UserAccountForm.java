package io.ies.bindings;


import java.time.LocalDate;

import lombok.Data;;

@Data
public class UserAccountForm {
	
	private String fullName;
	private String email;
	private Long phNum;
	private String gender;
	private Long ssn;
	private Integer rollId;    
	private LocalDate dob;
	private String activeSw;
	

}
