package org.lamisplus;
import com.foreach.across.config.AcrossApplication;
import com.foreach.across.modules.hibernate.jpa.AcrossHibernateJpaModule;
import com.foreach.across.modules.web.AcrossWebModule;
import lombok.extern.slf4j.Slf4j;
import org.lamisplus.modules.base.BaseModule;
import org.lamisplus.modules.ehr.EHRModule;
import org.lamisplus.modules.security.LamisSecurityModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import java.util.Collections;

@AcrossApplication(
    modules = {
        AcrossWebModule.NAME,
        BaseModule.NAME,
        AcrossHibernateJpaModule.NAME,
        LamisSecurityModule.NAME,
        EHRModule.NAME
    })
@Slf4j
public class LamisApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LamisApplication.class);
        springApplication.setDefaultProperties(Collections.singletonMap(ConfigFileApplicationListener.CONFIG_ADDITIONAL_LOCATION_PROPERTY, "${user.home}/dev-configs/lamis-application.yml"));
        springApplication.run(args);
    }
}
