package ru.mashinis.db.interaction.ex05_repositories.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Setter {
    String value();
}
