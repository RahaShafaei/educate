package edu.educate.service.baseService;

import edu.educate.dto.baseDto.BaseDto;
import edu.educate.exception.ItemNotFoundException;
import edu.educate.model.baseModel.BaseEntity;
import edu.educate.repository.baseRepository.GenericRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class GenericServiceImpl<T extends BaseEntity, R extends BaseDto> implements GenericService<T,R> {

    protected final GenericRepository<T> repository;
    protected final String entityName;

    @Override
    public List<T> getAllEntities() {
        return repository.findByDeletedFalseOrderByIdDesc();
    }

    @Override
    public List<T> getAllEntitiesByIds(List<Integer> EntityIds){
        return repository.findByIdIn(EntityIds);
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
    @Transactional
    public Optional<T> getEntityById(Integer EntityId){
        return repository.findById(EntityId);
    }

    @Override
    public Page<T> getAllEntities(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    @Override
    public List<T> findEntities(Example<T> example) {
        return repository.findAll(example);
    }

    @Override
    public Page<T> getAllEntities(Pageable pageable) {
        return repository.findByDeletedFalseOrderByIdDesc(pageable);
    }

    @Override
    public List<T> findEntitiesBySpecificFields(T entity) {
        return null;
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
    public Boolean deleteEntityEntirely(Integer id) {
        Optional<T> entity = repository.findById(id);

        if (!entity.isEmpty()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T createEmptyEntity(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public R createEmptyDto(Class<R> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T createEntity(T entity) {
        if (entity.isNew()){
            entity.setInsertedAt(LocalDateTime.now());
        }
        return repository.save(entity);
    }

    @Override
    public BaseDto getEntityByRelatedEntities(Integer id) {
        return null;
    }

    @Override
    public BaseDto createEntityByRelatedEntities(BaseDto baseDto) {
        return null;
    }

    @Override
    public BaseEntity createEntityByRelatedEntities(BaseEntity baseEntity) {
        return null;
    }

    @Override
    public boolean entityValidation(BaseDto baseDto) {
        return false;
    }
}

