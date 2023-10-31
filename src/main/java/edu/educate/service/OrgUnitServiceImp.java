package edu.educate.service;

import edu.educate.dto.OrgUnitDto;
import edu.educate.dto.OrgUnitMapper;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.OrgUnitEntity;
import edu.educate.repository.OrgUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("orgUnitService")
public class OrgUnitServiceImp implements OrgUnitService{

    private static final String ORGUNIT_ID = "OrgUnit id: ";
    private final OrgUnitRepository orgUnitRepository;
    private final OrgUnitMapper orgUnitMapper;
    @Override
    public List<OrgUnitDto> getOrgUnits() {
        return orgUnitRepository.findAll()
                .stream()
                .map(orgUnitMapper::toDto)
                .toList();
    }

    @Override
    public OrgUnitDto getOrgUnit(Integer id) {
        Optional<OrgUnitEntity> orgUnit = orgUnitRepository.findById(id);

        if (orgUnit.isEmpty())
            throw new ItemNotFoundException(ORGUNIT_ID + id);

        return orgUnitMapper.toDto(orgUnit.get());
    }

    @Override
    public Boolean deleteOrgUnit(Integer id) {
        Optional<OrgUnitEntity> orgUnit = orgUnitRepository.findById(id);

        if (!orgUnit.isEmpty()) {
            orgUnitRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrgUnitDto createOrgUnit(OrgUnitEntity orgUnit) {
        if (orgUnit.getTitle() == null || orgUnit.getTitle().isEmpty())
            throw new ParametersNotValidException("Title of OrgUnit should not be empty.");

        OrgUnitEntity savedOrgUnit = orgUnitRepository.save(orgUnit);

        return orgUnitMapper.toDto(savedOrgUnit);
    }
}
