package com.dxc.dxconlinebanking.auth;

import com.dxc.dxconlinebanking.user.BankUser;

public record AuthUserResponse(Long id, String userName, String firstName, String lastName) {

	public static AuthUserResponse from(BankUser bankUser) {
		return new AuthUserResponse(
				bankUser.getId(),
				bankUser.getUserName(),
				bankUser.getFirstName(),
				bankUser.getLastName());
	}
}
