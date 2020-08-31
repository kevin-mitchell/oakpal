/*
 * Copyright 2020 Mark Adamcin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.adamcin.oakpal.api;

import org.apache.jackrabbit.vault.packaging.PackageId;
import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ProviderType;

/**
 * Type representing a Sling-installable resource JCR path.
 *
 */
@ProviderType
public interface SlingInstallable {

    /**
     * PackageId of the package that imported the resource.
     *
     * @return the parent package ID
     */
    @NotNull PackageId getParentId();

    /**
     * The JCR path of the resource.
     *
     * @return the JCR path
     */
    @NotNull String getJcrPath();

}
