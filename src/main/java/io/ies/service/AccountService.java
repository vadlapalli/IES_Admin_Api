package io.ies.service;

import java.util.List;

import io.ies.bindings.UnlockAccForm;
import io.ies.bindings.UserAccountForm;

public interface AccountService {
	
	public boolean createAcct(UserAccountForm form) throws Exception;
	
	public List<UserAccountForm> fetchUserAccounts( );

    public UserAccountForm getUserAccById(Integer accId);

    public String changeAcctStatus(Integer accId, String status);

    public String unlockUserAccount(UnlockAccForm unlockAccForm);

}
