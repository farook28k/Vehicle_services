package com.vechical.service.bo.mapper;

import com.vechical.service.bo.dto.VechicalDTO;
import com.vechical.service.repository.Entity.VechicalEntity;
import com.vechical.service.util.DateUtil;

public class VechicalMapper {

	private VechicalMapper() {

	}

	public static VechicalEntity toEntity(VechicalDTO vechicalDTO) {
		return VechicalEntity.builder().vechicalModel(vechicalDTO.getVechicalModel())
				.vechicalService(vechicalDTO.getVechicalService()).vechicalMake(vechicalDTO.getVechicalMake())
				.vechicalPlateNumber(vechicalDTO.getVechicalPlateNumber())
				.cretaedOn(DateUtil.convertUtilToSQL(vechicalDTO.getCretaedOn())).createdBy(vechicalDTO.getCretaedBy())
				.updateOn(DateUtil.convertUtilToSQL(vechicalDTO.getUpdateOn()))

				.updateBy(vechicalDTO.getUpdateBy()).build();
	}

	public static VechicalDTO toDTO(VechicalEntity vechicalEntity) {
		return VechicalDTO.builder().vechicalModel(vechicalEntity.getVechicalModel())
				.vechicalService(vechicalEntity.getVechicalService()).vechicalMake(vechicalEntity.getVechicalMake())
				.vechicalPlateNumber(vechicalEntity.getVechicalPlateNumber())
				.cretaedOn(DateUtil.convertUtilToSQL(vechicalEntity.getCretaedOn()))

				.cretaedBy(vechicalEntity.getCreatedBy())
				.updateOn(DateUtil.convertUtilToSQL(vechicalEntity.getUpdateOn()))

				.updateBy(vechicalEntity.getUpdateBy()).build();
	}
}
