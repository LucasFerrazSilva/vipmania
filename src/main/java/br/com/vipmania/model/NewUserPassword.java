package br.com.vipmania.model;

public class NewUserPassword {

	private User user;
	private String actualPassword;
	private String newPassword;
	
	
	public String getMessage() {
		if(!isActualPasswordValid()) {
			return "A senha atual está incorreta";
		}
		else if(newPassword == null) {
			return "A nova senha não pode estar vazia";
		}
		
		return "Senha alterada com sucesso!";
	}


	public boolean isActualPasswordValid() {
		return user.hasSamePassword(actualPassword);
	}
	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getActualPassword() {
		return actualPassword;
	}
	
	public void setActualPassword(String actualPassword) {
		this.actualPassword = actualPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public boolean isValid() {
		return (user.hasSamePassword(actualPassword) && newPassword != null);
	}


	public User getUserWithNewPassword() {
		user.setPasswordAndEncrypt(newPassword);
		
		return user;
	}
	
}