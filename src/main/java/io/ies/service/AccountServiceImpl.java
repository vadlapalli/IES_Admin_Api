package io.ies.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ies.bindings.UnlockAccForm;
import io.ies.bindings.UserAccountForm;
import io.ies.entities.UsersEntity;
import io.ies.repositories.UserRepo;
import io.ies.utils.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createAcct(UserAccountForm form) throws Exception {
			UsersEntity email = userRepo.findByEmail(form.getEmail());
		
			if(email!=null) {
				return false;
			}
			
			UsersEntity user=new UsersEntity();
			BeanUtils.copyProperties(form, user);
			
			user.setAcctStatus("Locked");
			user.setActiveSw("Y");
			user.setPzzwd(generatePwd());
			userRepo.save(user);
			
			System.out.println(form.getEmail());
			
			// send email
			
			String subject="";
			String body="";
	        return emailUtils.sendEmail(subject, body, form.getEmail()); 
	}

	

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		List<UsersEntity> findAll = userRepo.findAll();  
		List<UserAccountForm> users= new ArrayList<UserAccountForm>();
		for(UsersEntity userEntity : findAll) {
			
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, user);
			users.add(user);
		}
		return users;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {
		Optional<UsersEntity> findById = userRepo.findById(accId);
		if(findById.isPresent()) {
			UsersEntity usersEntity = findById.get();
			UserAccountForm form=new UserAccountForm();
			BeanUtils.copyProperties(usersEntity, form);
			return form;
		}
		return null;
	}

	@Override
	public String changeAcctStatus(Integer accId, String status) {	
		int cnt = userRepo.updateAcctStatus(accId, status);

		if (cnt > 0) {
			return "Status Changed";
		}

		return "Failed To Change";
	}

	@Override
	public String unlockUserAccount(UnlockAccForm unlockAccForm) {
		
		UsersEntity findByEmail = userRepo.findByEmail(unlockAccForm.getEmail());
		
		findByEmail.setPzzwd(unlockAccForm.getNewPwd());
		findByEmail.setAcctStatus("UNLOCKED");

		userRepo.save(findByEmail);

		return "Account Unlocked";
	}
	
	private String generatePwd() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		// combine all strings
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		// create random string builder
		StringBuilder sb = new StringBuilder();

		// create an object of Random class
		Random random = new Random();

		// specify length of random string
		int length = 6;

		for (int i = 0; i < length; i++) {

			// generate random index number
			int index = random.nextInt(alphaNumeric.length());

			// get character specified by index
			// from the string
			char randomChar = alphaNumeric.charAt(index);

			// append the character to string builder
			sb.append(randomChar);
		}

		return sb.toString();
	}

}
