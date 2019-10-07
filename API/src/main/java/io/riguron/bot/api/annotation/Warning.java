package io.riguron.bot.api.annotation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Warning {

    public static final String AUTOWIRING = "SpringJavaInjectionPointsAutowiringInspection";
    public static final String COMPONENT_SCAN = "SpringComponentScan";
}
