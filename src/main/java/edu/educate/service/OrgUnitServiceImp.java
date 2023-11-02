package edu.educate.service;

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
    @Override
    public List<OrgUnitEntity> getOrgUnits() {
        return orgUnitRepository.findAll();
    }

    @Override
    public OrgUnitEntity getOrgUnit(Integer id) {
        Optional<OrgUnitEntity> orgUnit = orgUnitRepository.findById(id);

        if (orgUnit.isEmpty())
            throw new ItemNotFoundException(ORGUNIT_ID + id);

        return orgUnit.get();
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
    public OrgUnitEntity createOrgUnit(OrgUnitEntity orgUnit) {

        OrgUnitEntity savedOrgUnit = orgUnitRepository.save(orgUnit);

        return savedOrgUnit;
    }
}
