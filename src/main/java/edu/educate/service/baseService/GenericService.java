package edu.educate.service.baseService;

import java.util.List;

public interface GenericService<T> {
    public List<T> getAllEntities();

    public T getEntity(Integer id);

    public Boolean deleteEntity(Integer id);

    public T createEntity(T entity);
}
