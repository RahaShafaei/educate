package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.OrgPostEntity;
import edu.educate.model.OrgPostEntity;
import edu.educate.repository.OrgPostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("orgPostService")
public class OrgPostServiceImp implements OrgPostService{

    private static final String ORGPOST_ID = "OrgPost id: ";
    private final OrgPostRepository orgPostRepository;
    @Override
    public List<OrgPostEntity> getOrgPosts() {
        return orgPostRepository.findAll();
    }

    @Override
    public OrgPostEntity getOrgPost(Integer id) {
        Optional<OrgPostEntity> orgPost = orgPostRepository.findById(id);

        if (orgPost.isEmpty())
            throw new ItemNotFoundException(ORGPOST_ID + id);

        return orgPost.get();
    }

    @Override
    public Boolean deleteOrgPost(Integer id) {
        Optional<OrgPostEntity> orgPost = orgPostRepository.findById(id);

        if (!orgPost.isEmpty()) {
            orgPostRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrgPostEntity createOrgPost(OrgPostEntity orgPost) {
        if (orgPost.getTitle() == null || orgPost.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of OrgPost should not be empty.");

        OrgPostEntity savedOrgPost = orgPostRepository.save(orgPost);

        return savedOrgPost;
    }
}
