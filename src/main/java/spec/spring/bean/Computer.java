package spec.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Computer {

    private CPU cpu;
    private List<RAM> ramList;
    private Screen screen;
    private List<Storage> storageList;


    public Computer() {
    }

    public Computer (CPU cpu) {
        this.cpu = cpu;
    }

    public Computer (@Qualifier("myCpu") CPU cpu,
                     @Qualifier("ramList") List<RAM> ramList,
                     List<Storage> storageList,
                     @Qualifier("myScreen") Screen screen) {
        this.cpu = cpu;
        this.ramList = ramList;
        this.screen = screen;
        this.storageList = storageList;
    }

    public CPU getCpu() {
        return cpu;
    }
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public List<RAM> getRamList() {return ramList;}
    public void setRamList(List<RAM> ramList) {this.ramList = ramList;}

    public Screen getScreen() {return screen;}
    public void setScreen(Screen screen) {this.screen = screen;}

    public List<Storage> getStorageList() {return storageList;}
    public void setStorageList(List<Storage> storageList) {this.storageList = storageList;}

    @Override
    public String toString() {
        return "Computer { \n" +
                "   cpu = " + getCpu().getName() + "\n" +
                "   RAM list = " + getRamList().stream().map(Device::getName).collect(Collectors.joining(", ")) + "\n" +
                "   Screen = " + getScreen().getName() + "\n" +
                "   Storage list = " + getStorageList().stream().map(Device::getName).collect(Collectors.joining(", ")) + "\n" +
                " }";
    }

}
