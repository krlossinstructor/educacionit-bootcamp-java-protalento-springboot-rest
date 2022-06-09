package ar.com.educacionit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUsuarioDto {

	private String nombreUsuario;
	private String password;
}
