package no.lagalt.lagaltbackend.service.services;

import java.util.Collection;

public interface CrudService<T,ID>{
    Collection<T> findAll();
    T findById(ID id);
    T create(T entity);
    T update(ID id, T entity);
    void deleteById(ID id);

}
