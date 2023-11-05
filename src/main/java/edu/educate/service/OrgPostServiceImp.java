package edu.educate.service;

import edu.educate.model.OrgPostEntity;
import edu.educate.repository.OrgPostRepository;
import edu.educate.service.baseService.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orgPostService")
public class OrgPostServiceImp extends GenericServiceImpl<OrgPostEntity> implements OrgPostService {

    @Autowired
    public OrgPostServiceImp(OrgPostRepository repository) {
        super(repository, "OrgPostEntity");
    }
}