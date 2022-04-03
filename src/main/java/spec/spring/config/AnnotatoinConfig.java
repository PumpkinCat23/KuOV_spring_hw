package spec.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spec.spring.bean.*;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("spec.spring.bean")
public class AnnotatoinConfig {
    //методы для RAM - List
    @Bean
    public List<RAM> ramList () {
        return Arrays.asList(kingstonRam(), sonyRam(), kingstonRam());
    }

    @Bean
    @Scope("prototype")
    public RAM kingstonRam () {
        return new KingstonRAM();
    }

    @Bean
    @Scope("prototype")
    public RAM sonyRam () {
        return new SonyRAM();
    }

    //методы для STORAGE - List
    @Bean
    public List<Storage> storageList () {
        return Arrays.asList(dexpStorage(), hpStorage());
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
