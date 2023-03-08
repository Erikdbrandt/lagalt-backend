package no.lagalt.lagaltbackend.service;

import java.util.Collection;

public interface CrudService<T,ID>{
    Collection<T> findAll();
    T findById(ID id);
    T create(T entity);
    void update(T entity);
    void deleteById(ID id);

}
