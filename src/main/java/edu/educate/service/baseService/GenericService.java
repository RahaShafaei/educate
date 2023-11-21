package edu.educate.service.baseService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.educate.dto.baseDto.BaseDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<T> extends MainService{
    public List<T> getAllEntities();

    public List<T> getAllEntitiesByIds(List<Integer> EntityIds);
    public Optional<T> getEntityById(Integer EntityId);

    public Page<T> getAllEntities(Pageable pageable);

    public Page<T> getAllEntities(Example<T> example, Pageable pageable);

    public T getEntity(Integer id);

    public BaseDto getEntityByRelatedEntities(Integer id);

    public Boolean deleteEntity(Integer id);

    public T createEntity(T entity);

    public void createEntityByRelatedEntities(BaseDto baseDto);
}
