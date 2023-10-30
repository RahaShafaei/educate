package edu.educate.service;

import edu.educate.dto.OrgPostDto;
import edu.educate.dto.PersonDto;
import edu.educate.model.OrgPostEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orgPostService")
public class OrgPostServiceImp implements OrgPostService{
    @Override
    public List<OrgPostDto> getOrgPosts() {
        return null;
    }

    @Override
    public OrgPostDto getOrgPost(Integer id) {
        return null;
    }

    @Override
    public List<PersonDto> getOrgPostPersons(Integer id) {
        return null;
    }

    @Override
    public Boolean deleteOrgPost(Integer id) {
        return null;
    }

    @Override
    public OrgPostDto createOrgPost(OrgPostEntity OrgPost) {
        return null;
    }
}
