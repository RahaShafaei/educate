package edu.educate.service;

import edu.educate.dto.PrCourseDto;
import edu.educate.dto.PrCourseMapper;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.exception.ParametersNotValidException;
import edu.educate.model.PrCourseEntity;
import edu.educate.repository.PrCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service("prCourseService")
public class PrCourseServiceImp implements PrCourseService{

    private static final String PRCOURSE_ID = "PrCourse id: ";
    private final PrCourseRepository prCourseRepository;
    private final PrCourseMapper prCourseMapper;
    @Override
    public List<PrCourseDto> getPrCourses() {
        return prCourseRepository.findAll()
                .stream()
                .map(prCourseMapper::toDto)
                .toList();
    }

    @Override
    public PrCourseDto getPrCourse(Integer id) {
        Optional<PrCourseEntity> prCourse = prCourseRepository.findById(id);

        if (prCourse.isEmpty())
            throw new ItemNotFoundException(PRCOURSE_ID + id);

        return prCourseMapper.toDto(prCourse.get());
    }

    @Override
    public Boolean deletePrCourse(Integer id) {
        Optional<PrCourseEntity> prCourse = prCourseRepository.findById(id);

        if (!prCourse.isEmpty()) {
            prCourseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PrCourseDto createPrCourse(PrCourseEntity prCourse) {
        if (prCourse.getLtTitle() == null || prCourse.getLtTitle().isEmpty())
            throw new ParametersNotValidException("LtTitle of PrCourse should not be empty.");

        if (prCourse.getPrTitle() == null || prCourse.getPrTitle().isEmpty())
            throw new ParametersNotValidException("PrTitle of PrCourse should not be empty.");

        PrCourseEntity savedPrCourse = prCourseRepository.save(prCourse);

        return prCourseMapper.toDto(savedPrCourse);
    }
}
