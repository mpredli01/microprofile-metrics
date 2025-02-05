/*
 **********************************************************************
 * Copyright (c) 2017, 2022 Contributors to the Eclipse Foundation
 *               2012 Ryan W Tenney (ryan@10e.us)
 *
 * See the NOTICES file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 **********************************************************************/
package org.eclipse.microprofile.metrics.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricUnits;

import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;

/**
 * An annotation for marking a method, constructor, or class as counted. The metric will be registered in the
 * application MetricRegistry.
 *
 * <p>
 * Given a method annotated with {@literal @}Counted like this:
 * </p>
 * 
 * <pre>
 * <code>
 *     {@literal @}Counted(name = "fancyName")
 *     public String fancyName(String name) {
 *         return "Sir Captain " + name;
 *     }
 * </code>
 * </pre>
 * 
 * A counter with the fully qualified class name + {@code fancyName} will be created and each time the
 * {@code #fancyName(String)} method is invoked, the counter will be marked. Similarly, the same applies for a
 * constructor annotated with counted.
 *
 * <p>
 * Given a class annotated with {@literal @}Counted like this:
 * </p>
 * 
 * <pre>
 * <code>
 *     {@literal @}Counted
 *     public class CounterBean {
 *         public void countMethod1() {}
 *         public void countMethod2() {}
 *     }
 * </code>
 * </pre>
 * 
 * A counter for the defining class will be created for each of the constructors/methods. Each time the
 * constructor/method is invoked, the respective counter will be marked.
 *
 * The counter value will only monotonically increase.
 *
 * This annotation will throw an IllegalStateException if the constructor/method is invoked, but the metric no longer
 * exists in the MetricRegistry.
 *
 */
@Inherited
@Documented
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Counted {

    /**
     * The name of the counter.
     * 
     * @return The name of the counter.
     */
    @Nonbinding
    String name() default "";

    /**
     * The tags of the counter.
     * 
     * @return The tags of the counter. Each {@code String} tag must be in the form of 'key=value'. If the input is
     *         empty or does not contain a '=' sign, the entry is ignored.
     *
     * @see org.eclipse.microprofile.metrics.Metadata
     */
    @Nonbinding
    String[] tags() default {};

    /**
     * Denotes whether to use the absolute name or use the default given name relative to the annotated class.
     * 
     * @return If {@code true}, use the given name as an absolute name. If {@code false} (default), use the given name
     *         relative to the annotated class. When annotating a class, this must be {@code false}.
     */
    @Nonbinding
    boolean absolute() default false;

    /**
     * The description of the counter.
     * 
     * @return The description of the counter.
     *
     * @see org.eclipse.microprofile.metrics.Metadata
     */
    @Nonbinding
    String description() default "";

    /**
     * The unit of the counter.
     * 
     * @return The unit of the counter. By default, the value is {@link MetricUnits#NONE}.
     *
     * @see org.eclipse.microprofile.metrics.Metadata
     * @see org.eclipse.microprofile.metrics.MetricUnits
     */
    @Nonbinding
    String unit() default MetricUnits.NONE;

    /**
     * The scope that this counter belongs to.
     * 
     * @return The scope this counter belongs to. By default, the value is {@link MetricRegistry#APPLICATION_SCOPE}.
     *
     */
    @Nonbinding
    String scope() default MetricRegistry.APPLICATION_SCOPE;

}
