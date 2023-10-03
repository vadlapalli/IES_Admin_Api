package io.ies.service;

import io.ies.bindings.DashboardCard;
import io.ies.bindings.LoginForm;
import io.ies.bindings.UserAccountForm;

public interface UserService {
	
	public String login(LoginForm loginForm);

    public boolean recoverPwd(String email);

    public DashboardCard fetchDashboardInfo();
    
    public UserAccountForm getUserByEmail(String email);

}
