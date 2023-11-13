package edu.educate.service.baseService;

import edu.educate.exception.ItemNotFoundException;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {

    private final GenericRepository<T> repository;
    private final String entityName;

    @Override
    public List<T> getAllEntities() {
        return repository.findByDeletedFalse();
    }

    @Override
    public T getEntity(Integer id) {
        Optional<T> entity = repository.findByIdAndDeletedFalse(id);

        if (entity.isEmpty()) {
            throw new ItemNotFoundException(entityName + " id: " + id);
        }

        return entity.get();
    }

    @Override
    public Boolean deleteEntity(Integer id) {
        Optional<T> entity = repository.findByIdAndDeletedFalse(id);

        if (entity.isPresent()) {
            entity.get().setDeleted(true);
            entity.get().setDeletedAt(LocalDateTime.now());
            repository.save(entity.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T createEntity(T entity) {
        return repository.save(entity);
    }
}
