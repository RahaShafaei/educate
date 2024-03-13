package edu.educate.service;

import edu.educate.dto.ProcessDto;
import edu.educate.model.ProcessEntity;
import edu.educate.repository.ProcessRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProcessService")
public class ProcessServiceImp extends GenericServiceImpl<ProcessEntity, ProcessDto> implements ProcessService {

    @Autowired
    public ProcessServiceImp(ProcessRepository repository) {
        super(repository, "ProcessEntity");
    }
}
