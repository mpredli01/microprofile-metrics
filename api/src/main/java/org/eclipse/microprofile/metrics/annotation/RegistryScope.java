/*
 **********************************************************************
 * Copyright (c) 2017, 2022 Contributors to the Eclipse Foundation
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
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.metrics.MetricRegistry;

/**
 * Specifies the scope of Metric Registry to inject.
 * <p>
 * This can be used to obtain the respective scoped {@link MetricRegistry}:
 * </p>
 * 
 * <pre>
 * <code>
 *      {@literal @}Inject
 *      {@literal @}RegistryScope(scope=MetricRegistry.APPLICATION_SCOPE)
 *      MetricRegistry appRegistry;
 * 
 *      {@literal @}Inject
 *      {@literal @}RegistryScope(scope="customScope")
 *      MetricRegistry customRegistry;
 * 
 * </code>
 * </pre>
 *
 * see {@link MetricRegistry#APPLICATION_SCOPE}, {@link MetricRegistry#BASE_SCOPE} and
 * {@link MetricRegistry#VENDOR_SCOPE}
 *
 * @author Raymond Lam
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface RegistryScope {
    /**
     * The scope of the MetricRegistry.
     * 
     * @return Indicates the scope of the MetricRegistry to be injected. The MicroProfile runtimes provides
     *         {@code application}, {@code base} and {@code vendor} scopes automatically and creates user-defined scopes
     *         as needed.
     * 
     *         see {@link MetricRegistry#APPLICATION_SCOPE}, {@link MetricRegistry#BASE_SCOPE} and
     *         {@link MetricRegistry#VENDOR_SCOPE}
     */
    String scope() default MetricRegistry.APPLICATION_SCOPE;
}
