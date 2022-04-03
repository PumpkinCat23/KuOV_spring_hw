package spec.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spec.spring.bean.Computer;
import spec.spring.config.AnnotatoinConfig;
import spec.spring.config.JavaConfig;

import java.util.Arrays;

public class AnnotationConfigBasedApp {

    public static void main (String [] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotatoinConfig.class);

        System.out.println("Context Name: " + context.getDisplayName());

        System.out.println("Bean Definitions" + Arrays.toString(context.getBeanDefinitionNames()));

        Computer computer = context.getBean(Computer.class);
        System.out.println(computer);

    }

}
