package io.github.julianobrl.discordbots.services;

import java.util.List;

public interface IService<T> {
    public List<T> list();
    public List<T> search();
    public T create(T object);
    public T getById(Long id);
    public T update(Long id, T updated);
    public void delete(Long id);
}
