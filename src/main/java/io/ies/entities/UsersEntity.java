package io.ies.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="IES_USERS")
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fullName;
	private String email;
	private String pzzwd;
	private String gender;
	private Long phNum;
	private LocalDate dob;
	private Long ssn;
	private String activeSw; 
	private String acctStatus;
	private Integer rollId;
	
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private java.util.List<PlansEntity> plans;

	
	
	
}
