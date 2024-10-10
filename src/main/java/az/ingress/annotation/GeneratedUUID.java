package az.ingress.annotation;

import org.hibernate.annotations.GenericGenerator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public @interface GeneratedUUID {}