package io.ies.bindings;

import lombok.Data;

@Data
public class UnlockAccForm {
	
	private String email;
    private String tempPwd;
    private String newPwd;
    private String confirmPwd;

}
