package edu.educate.service;

import edu.educate.dto.PersonDto;
import edu.educate.dto.OrgPostDto;
import edu.educate.model.OrgPostEntity;

import java.util.List;

public interface OrgPostService {
    public List<OrgPostDto> getOrgPosts();

    public OrgPostDto getOrgPost(Integer id);

    public Boolean deleteOrgPost(Integer id);

    public OrgPostDto createOrgPost(OrgPostEntity orgPost);
}
