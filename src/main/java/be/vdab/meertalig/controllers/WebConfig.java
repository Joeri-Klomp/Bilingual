package be.vdab.meertalig.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final int ZEVEN_DAGEN = 604_800;
    @Bean(name = "localeResolver")
//    Je typt @Bean voor een method. Spring maakt dan een bean van de returnwaarde van die method.
    LocaleResolver localeResolver() {
        var resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(ZEVEN_DAGEN);
        return resolver;
//        return new SessionLocaleResolver(); //je vraagt de taal en het land aan de gebruiker en de SessionLocaleResolver bean
        //onthoudt de keuze in een HttpSession variabele (Zolang de gebruiker geen taal of land kiest, gebruikt Spring de taal en landkeuze van de browser.)

//        return new FixedLocaleResolver(new Locale("en", "US")); //zo is de taal en het land voor elke gebruiker hetzelfde
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptorRegistry.addInterceptor(interceptor);
//        Je kan bij veel websites je taal kiezen op elke pagina. De browser stuurt die request telkens naar een andere
//        controller. Het is omslachtig de request in elke controller te verwerken. Een interceptor is een class die Spring
//        oproept telkens een browser request binnenkomt en voor Spring die request naar de Controller stuurt.
//        LocaleChangeInterceptor is een interceptor in het Spring framework. Als de request een parameter locale bevat,
//        roept de interceptor de method setLocale van de SessionLocaleResolver bean op en geeft die request parameter mee.
//        De bean onthoudt de taal en het land van de gebruiker als HTTP session variabele.
    }
}
