package edu.educate.service;

import edu.educate.dto.PlansDto;
import edu.educate.model.ElementEntity;
import edu.educate.model.PlansEntity;
import edu.educate.service.baseService.GenericService;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlansService extends GenericService<PlansEntity, PlansDto> {
    public PlansEntity createEntityByRelatedFiles(PlansEntity entity, MultipartFile[] files);
}
