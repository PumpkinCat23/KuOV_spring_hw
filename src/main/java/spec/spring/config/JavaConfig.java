package spec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spec.spring.bean.*;

import java.util.Arrays;
import java.util.List;

@Configuration
public class JavaConfig {

    @Bean("myComputer")
    public Computer computer() {
        Computer computer = new Computer();
        computer.setCpu(intelCpu());
        computer.setRamList(Arrays.asList(sonyRam(), kingstonRam(), sonyRam()));

        computer.setScreen(asusScreen());
        computer.setStorageList(Arrays.asList(dexpStorage(),hpStorage()));
        return computer;
    }

    @Bean
    public CPU amdCpu() {
        return new AmdCPU();
    }

    @Bean
    public CPU intelCpu() {
        return new IntelCPU();
    }

    @Bean
    @Scope("prototype")
    public RAM kingstonRam() {
        return new KingstonRAM();
    }

    @Bean
    @Scope("prototype")
    public RAM sonyRam () {
        return new SonyRAM();
    }


    @Bean
    public Screen asusScreen () {
        return new AsusSCREEN();
    }

    @Bean
    public Screen dellScreen () {
        return new DellSCREEN();
    }

    @Bean
    @Scope("prototype")
    public Storage dexpStorage () {
        return new DexpSTORAGE();
    }

    @Bean
    @Scope("prototype")
    public Storage hpStorage () {
        return new HpSTORAGE();
    }

}
