/*
 * Copyright 2019 dev.dorcks Lotice
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.dorcks.lotice.api.dependency

interface DependencyScanner {

    /**
     * Queries the [Set] of [Dependency]s of the [DependencySource] and must contain zero null elements.
     *
     * @param dependencySource The [DependencySource] from which the [Dependency]s are queried.
     * @return A [Set] of [Dependency]s containing zero null elements
     */
    fun getDependencies(dependencySource: DependencySource): Set<Dependency>
}