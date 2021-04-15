

package ws.billy.CookieGadgets.utils.cosmetics.pets.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface EntitySize {
    float width() default 0.5f;
    
    float height() default 0.5f;
}
