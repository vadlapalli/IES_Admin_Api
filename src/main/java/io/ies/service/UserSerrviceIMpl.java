package io.ies.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import io.ies.bindings.DashboardCard;
import io.ies.bindings.LoginForm;
import io.ies.bindings.UserAccountForm;
import io.ies.entities.EligEntity;
import io.ies.entities.UsersEntity;
import io.ies.repositories.EligRepo;
import io.ies.repositories.PlansRepo;
import io.ies.repositories.UserRepo;
import io.ies.utils.EmailUtils;

public class UserSerrviceIMpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtils utils;
	
	@Autowired
	private PlansRepo planRepo;
	
	@Autowired
	private EligRepo eligRepo;

	@Override
	public String login(LoginForm loginForm) {
		UsersEntity entity = userRepo.findByEmailAndPzzwd(loginForm.getEmail(), loginForm.getPzzwd());
		if(entity == null) {
			return "invalid credentials";
		}
		if("Y".equals(entity.getActiveSw()) && "Unlocked".equals(entity.getAcctStatus())) {
			return "Success";
		}else {
			return "Account Locked/In-Active";
		}
	}

	@Override
	public boolean recoverPwd(String email) {
		UsersEntity userEntity = userRepo.findByEmail(email);
		if(null == userEntity) {
			return false;
		}else {
			String subject="";
			String body= "";
		return utils.sendEmail(email, subject, body);
		}
	}

	@Override
	public DashboardCard fetchDashboardInfo() {
		long plansCount = planRepo.count();
		List<EligEntity> eligList = eligRepo.findAll();
		Long apprvdCnt = eligList.stream().filter(ed->ed.getPlanStatus().equals("AP")).count();
		long dndCnt = eligList.stream().filter(ed->ed.getPlanStatus().equals("Denied")).count();
		Double benefitAmt = eligList.stream().mapToDouble(ed->ed.getBenefitAmt()).sum();
		
		DashboardCard card= new DashboardCard();
		card.setPlanCnt(plansCount);
		card.setApprovedCnt(apprvdCnt);
		card.setBeniftAmtGiven(benefitAmt);
		card.setDeniedCnt(dndCnt);
		return card;
	}

	@Override
	public UserAccountForm getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
