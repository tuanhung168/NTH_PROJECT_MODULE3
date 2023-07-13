package ra.service;

import java.util.List;

public interface IGeneric<T> {
    List<T> fileAll();

    void save(T t);

    T findById(int id);

    void deleteById(int id);
}
