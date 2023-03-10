package project.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class EmailRequestDto {

	@NotBlank(message = "Email can't be blank!")
	@Email(message = "Invalid Email Format")
	@Length(min = 5, max = 30, message = "Invalid Email length!!!!!!!")
	private String email;

	public EmailRequestDto() {
		super();
	}

	public EmailRequestDto(
			@NotBlank(message = "Email can't be blank!") @Email(message = "Invalid Email Format") @Length(min = 5, max = 30, message = "Invalid Email length!!!!!!!") String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotRequestDto [email=" + email + "]";
	}

}
