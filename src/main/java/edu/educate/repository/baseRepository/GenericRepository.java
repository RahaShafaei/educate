package edu.educate.repository.baseRepository;

import edu.educate.model.baseModel.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
    List<T> findByDeletedFalse();
    Page<T> findByDeletedFalse(Pageable pageable);
    Optional<T> findByIdAndDeletedFalse(Integer id);
}
