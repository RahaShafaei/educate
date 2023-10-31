package edu.educate.service;

import edu.educate.dto.PrCourseGrpDto;
import edu.educate.dto.PrCourseGrpMapper;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PrCourseGrpEntity;
import edu.educate.repository.PrCourseGrpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("prCourseGrpService")
public class PrCourseGrpServiceImp implements PrCourseGrpService{

    private static final String PRCOURSEGRP_ID = "PrCourseGrp id: ";
    private final PrCourseGrpRepository prCourseGrpRepository;
    private final PrCourseGrpMapper prCourseGrpMapper;
    @Override
    public List<PrCourseGrpDto> getPrCourseGrps() {
        return prCourseGrpRepository.findAll()
                .stream()
                .map(prCourseGrpMapper::toDto)
                .toList();
    }

    @Override
    public PrCourseGrpDto getPrCourseGrp(Integer id) {
        Optional<PrCourseGrpEntity> prCourseGrp = prCourseGrpRepository.findById(id);

        if (prCourseGrp.isEmpty())
            throw new ItemNotFoundException(PRCOURSEGRP_ID + id);

        return prCourseGrpMapper.toDto(prCourseGrp.get());
    }

    @Override
    public Boolean deletePrCourseGrp(Integer id) {
        Optional<PrCourseGrpEntity> prCourseGrp = prCourseGrpRepository.findById(id);

        if (!prCourseGrp.isEmpty()) {
            prCourseGrpRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PrCourseGrpDto createPrCourseGrp(PrCourseGrpEntity prCourseGrp) {
        if (prCourseGrp.getLtTitle() == null || prCourseGrp.getLtTitle().isEmpty())
            throw new ParametersNotValidException("LtTitle of PrCourseGrp should not be empty.");

        if (prCourseGrp.getPrTitle() == null || prCourseGrp.getPrTitle().isEmpty())
            throw new ParametersNotValidException("PrTitle of PrCourseGrp should not be empty.");

        PrCourseGrpEntity savedPrCourseGrp = prCourseGrpRepository.save(prCourseGrp);

        return prCourseGrpMapper.toDto(savedPrCourseGrp);
    }
}
