package spec.spring.bean;

public interface Device {

    //выводит просто имя класса через метод getName
    default String getName() {
        return this.getClass().getSimpleName();
    }

}
