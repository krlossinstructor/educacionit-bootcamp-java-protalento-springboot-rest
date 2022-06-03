package ar.com.educacionit.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenesDto {
	private Long id;
	@NotNull
	private Long socioId;
	@NotNull
    private Long estadoId;
	@NotNull
    private Double montoTotal;

    private Long cuponId;
}
