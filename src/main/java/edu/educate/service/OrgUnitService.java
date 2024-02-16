package edu.educate.service;

import edu.educate.dto.OrgUnitDto;
import edu.educate.model.OrgUnitEntity;
import edu.educate.service.baseService.GenericService;

import java.util.List;

public interface OrgUnitService extends GenericService<OrgUnitEntity, OrgUnitDto> {
    List<OrgUnitEntity> findByParentOrgUnitIsNull();
    List<OrgUnitEntity> findByParentOrgUnitId(Integer id);
}
