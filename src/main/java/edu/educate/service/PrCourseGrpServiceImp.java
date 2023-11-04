package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PrCourseGrpEntity;
import edu.educate.repository.PrCourseGrpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("prCourseGrpService")
public class PrCourseGrpServiceImp implements PrCourseGrpService{

    private static final String PRCOURSEGRP_ID = "PrCourseGrp id: ";
    private final PrCourseGrpRepository prCourseGrpRepository;
    @Override
    public List<PrCourseGrpEntity> getPrCourseGrps() {
        return prCourseGrpRepository.findByDeletedFalse();
    }

    @Override
    public PrCourseGrpEntity getPrCourseGrp(Integer id) {
        Optional<PrCourseGrpEntity> prCourseGrp = prCourseGrpRepository.findByIdAndDeletedFalse(id);

        if (prCourseGrp.isEmpty())
            throw new ItemNotFoundException(PRCOURSEGRP_ID + id);

        return prCourseGrp.get();
    }

    @Override
    public Boolean deletePrCourseGrp(Integer id) {
        Optional<PrCourseGrpEntity> prCourseGrp = prCourseGrpRepository.findByIdAndDeletedFalse(id);

        if (!prCourseGrp.isEmpty()) {
            prCourseGrp.get().setDeleted(true);
            prCourseGrp.get().setDeletedAt(LocalDateTime.now());
            prCourseGrpRepository.save(prCourseGrp.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PrCourseGrpEntity createPrCourseGrp(PrCourseGrpEntity prCourseGrp) {

        PrCourseGrpEntity savedPrCourseGrp = prCourseGrpRepository.save(prCourseGrp);

        return savedPrCourseGrp;
    }
}
