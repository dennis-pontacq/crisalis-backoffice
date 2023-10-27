package edu.bootcamp.backoffice.model.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class LoginDto {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	
	public LoginDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}




}
