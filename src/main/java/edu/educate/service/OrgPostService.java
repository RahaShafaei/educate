package edu.educate.service;

import edu.educate.model.OrgPostEntity;

import java.util.List;

public interface OrgPostService {
    public List<OrgPostEntity> getOrgPosts();

    public OrgPostEntity getOrgPost(Integer id);

    public Boolean deleteOrgPost(Integer id);

    public OrgPostEntity createOrgPost(OrgPostEntity orgPost);
}
