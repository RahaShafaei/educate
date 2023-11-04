package edu.educate.service;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PrCourseEntity;
import edu.educate.repository.PrCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("prCourseService")
public class PrCourseServiceImp implements PrCourseService{

    private static final String PRCOURSE_ID = "PrCourse id: ";
    private final PrCourseRepository prCourseRepository;
    @Override
    public List<PrCourseEntity> getPrCourses() {
        return prCourseRepository.findByDeletedFalse();
    }

    @Override
    public PrCourseEntity getPrCourse(Integer id) {
        Optional<PrCourseEntity> prCourse = prCourseRepository.findByIdAndDeletedFalse(id);

        if (prCourse.isEmpty())
            throw new ItemNotFoundException(PRCOURSE_ID + id);

        return prCourse.get();
    }

    @Override
    public Boolean deletePrCourse(Integer id) {
        Optional<PrCourseEntity> prCourse = prCourseRepository.findByIdAndDeletedFalse(id);

        if (!prCourse.isEmpty()) {
            prCourse.get().setDeleted(true);
            prCourse.get().setDeletedAt(LocalDateTime.now());
            prCourseRepository.save(prCourse.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PrCourseEntity createPrCourse(PrCourseEntity prCourse) {

        PrCourseEntity savedPrCourse = prCourseRepository.save(prCourse);

        return savedPrCourse;
    }
}
