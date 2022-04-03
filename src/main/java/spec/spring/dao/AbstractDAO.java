package spec.spring.dao;

import java.util.List;

public interface AbstractDAO <T>{
    //Date Access Object
    //CRUD - Create Read Update Delete

    //создание
    void create (T data);

    //вывод всего
    List<T> getAll();

    //поиск по ID
    T getByID(long id);

    //обновление
    void update (T data);

    //удаление по id
    void delete (long id);


}
