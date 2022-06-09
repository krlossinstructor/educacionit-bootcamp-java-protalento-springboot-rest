package ar.com.educacionit.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import ar.com.educacionit.enums.EstadoOrdenEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//O.R.M > JPA > hibernate
//modelo realacionl > mysql 
//
@Entity
@Table(name="ordenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Ordenes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name="socios_id", referencedColumnName = "id")
	private Socios socio;
	
	@ManyToOne
	@JoinColumn(name="estados_ordenes_id", referencedColumnName = "id")
	private EstadosOrdenes estado;
	
	@Column(name="monto_total", nullable = false)
	private Double montoTotal;
	
	@OneToOne
	@JoinColumn(name="cupones_id", referencedColumnName = "id")
	private Cupones cupon;
	
	public boolean isEstadoFinal() {
		return this.getEstado().getEstadoFinal().equals(EstadoOrdenEnum.FINAL.getEstado());
	}
}
