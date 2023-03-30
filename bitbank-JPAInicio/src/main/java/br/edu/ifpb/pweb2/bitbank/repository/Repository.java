package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.List;

public interface Repository<T, ID> {

    public T findById(ID id);

    public List<T> findAll();

    public T save(T object);

    public void delete(T object);

    public void deleteById(ID id);

}
