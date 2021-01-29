package datastorage;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();
    T get(int id);
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);

}
