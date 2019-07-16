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
package dev.dorcks.lotice.cli

import dev.dorcks.lotice.api.BuildAutomationSystem
import dev.dorcks.lotice.api.dependency.Dependency
import dev.dorcks.lotice.api.dependency.DependencySource

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("No arguments provided")
    }

    // TODO Use cli parser (picocli?) to be instructed which DependencySource implementation to use
    // TODO Search for DependencySource implementations
    val buildAutomationSystem = object : BuildAutomationSystem {
        override fun getDependencies(dependencySource: DependencySource): Set<Dependency> {
            TODO("not implemented")
        }

        override fun getDependencySources() = setOf(object : DependencySource {
            override val name: String = "Mocked DependencySource 1"
        },
                object : DependencySource {
                    override val name: String = "Mocked DependencySource 2"
                })
        override val name = "Mocked BuildAutomationSystem Name"
        override val fileExtension = ".fake"
    }

    println("Build Automation System: ${buildAutomationSystem.name}, dependencySources: " +
            "${buildAutomationSystem.getDependencySources().asSequence().map { it.name }.joinToString(", ", "[", "]")}, " +
            "fileExtension: ${buildAutomationSystem.fileExtension}")
}