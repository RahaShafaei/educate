package edu.educate.service;

import edu.educate.dto.OrgPostMapper;
import edu.educate.dto.OrgPostDto;
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
    private final OrgPostMapper orgPostMapper;
    @Override
    public List<OrgPostDto> getOrgPosts() {
        return orgPostRepository.findAll()
                .stream()
                .map(orgPostMapper::toDto)
                .toList();
    }

    @Override
    public OrgPostDto getOrgPost(Integer id) {
        Optional<OrgPostEntity> orgPost = orgPostRepository.findById(id);

        if (orgPost.isEmpty())
            throw new ItemNotFoundException(ORGPOST_ID + id);

        return orgPostMapper.toDto(orgPost.get());
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
    public OrgPostDto createOrgPost(OrgPostEntity orgPost) {
        if (orgPost.getTitle() == null || orgPost.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of OrgPost should not be empty.");

        OrgPostEntity savedOrgPost = orgPostRepository.save(orgPost);

        return orgPostMapper.toDto(savedOrgPost);
    }
}
