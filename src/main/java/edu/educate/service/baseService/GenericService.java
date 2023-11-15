package edu.educate.service.baseService;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<T> {
    public List<T> getAllEntities();
    public Page<T> getAllEntities(Pageable pageable);

    public T getEntity(Integer id);

    public Boolean deleteEntity(Integer id);

    public T createEntity(T entity);
}
