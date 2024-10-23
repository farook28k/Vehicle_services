package com.vechical.service.bo.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VechicalDTO {
	private Long vechicalId;
	private String vechicalModel;
	private String vechicalService;
	private String vechicalMake;
	private String vechicalPlateNumber;
	private Date cretaedOn;
	private Long cretaedBy;
	private Date updateOn;
	private Long updateBy;

}
