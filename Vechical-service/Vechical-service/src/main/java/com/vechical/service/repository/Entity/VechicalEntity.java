 package com.vechical.service.repository.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
@Data
@Entity
@Table(name ="Vechical_tl")
@Builder
public class VechicalEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="VECHICAL_ID")
	private Long vechicalId;
	@Column(name = "VECHICAL_MODEAL", nullable = false, length = 50)
	private String vechicalModel;
	@Column(name = "VECHICAL_SERVICE", nullable = false, length = 50)
	private String vechicalService;
	@Column(name = "VECHICAL_MAKE", nullable = false, length = 50)
	private String vechicalMake;
	@Column(name = "VECHICAL_PLATENUMBER", nullable = false, length = 50)
	private String vechicalPlateNumber;
	@Column(name = "CRETAED_ON", nullable = false)
	private Date cretaedOn;
	@Column(name = "CERATED_BY")
	private Long createdBy;
	@Column(name = "UPDATE_ON", nullable = false)
	private Date updateOn;
	@Column(name = "UPDATE_BY")
	private Long updateBy;
	

}
