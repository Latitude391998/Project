package project.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class LoginRequestDto {
	
	@NotBlank(message = "Email can't be blank!")
	@Email(message = "Invalid Email Format")
	@Length(min = 5, max = 30, message = "Invalid Email length!!!!!!!")
	private String email;
    //@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid Password!!!!")
	private String password;

	public LoginRequestDto() {
		super();
	}

	public LoginRequestDto(
			@NotBlank(message = "Email can't be blank!") @Email(message = "Invalid Email Format") @Length(min = 5, max = 20, message = "Invalid Email length!!!!!!!") String email,
  			//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})", message = "Blank or Invalid Password!!!!") 
			String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequestDto [email=" + email + ", password=" + password + "]";
	}
}