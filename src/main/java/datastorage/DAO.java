package datastorage;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();
    T get(int id);
    boolean save(T t);
    boolean update(int id, T t);
    boolean delete(T t);

}
