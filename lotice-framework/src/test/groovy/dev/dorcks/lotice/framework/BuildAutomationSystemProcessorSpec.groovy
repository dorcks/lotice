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
package dev.dorcks.lotice.framework

import dev.dorcks.lotice.api.BuildAutomationSystem
import dev.dorcks.lotice.api.dependency.Dependency
import dev.dorcks.lotice.api.dependency.DependencySource
import dev.dorcks.lotice.api.license.LicenseReader
import dev.dorcks.lotice.api.notice.NoticeReader
import spock.lang.Specification
import spock.lang.Unroll

class BuildAutomationSystemProcessorSpec extends Specification {

    @Unroll
    def "handles null constructor arguments: #testCase"() {
        when:
        new BuildAutomationSystemProcessor(null, null, null)

        then:
        thrown(IllegalArgumentException)

        where:
        buildAutomationSystem       | licenseReader       | noticeReader       || testCase
        null                        | null                | null               || "BuildAutomationSystem, LicenseReader, and NoticeReader null"
        Mock(BuildAutomationSystem) | null                | null               || "LicenseReader and NoticeReader null"
        null                        | Mock(LicenseReader) | null               || "BuildAutomationSystem and NoticeReader null"
        null                        | null                | Mock(NoticeReader) || "BuildAutomationSystem and LicenseReader null"
        null                        | Mock(LicenseReader) | Mock(NoticeReader) || "BuildAutomationSystem null"
        Mock(BuildAutomationSystem) | null                | Mock(NoticeReader) || "LicenseReader null"
        Mock(BuildAutomationSystem) | Mock(LicenseReader) | null               || "NoticeReader null"
    }

    @Unroll
    def "GetAllDependencies"() {
        given:
        def basDependencySources = dependencySourceNamesSet.collect { dependencyName ->
            Mock(DependencySource) {
                _ * getName() >> dependencyName
            }
        } as Set<DependencySource>
        def basDependencies = [] as Set<Dependency>
        numBasDependencies.times {
            basDependencies << new Dependency("build.automation.system", "dependency-$it", "1.0.0")
        }
        def basDependencySourceDependencies = [] as Set<Dependency>
        numBasDependencySourceDependencies.times {
            basDependencySourceDependencies << new Dependency("build.automation.system.dependency.source", "${basDependencySources[it].name}.${it}", "1.0.0")
        }
        def mockBas = Mock(BuildAutomationSystem.class)
        def buildAutomationSystemProcessor = new BuildAutomationSystemProcessor(mockBas, Mock(LicenseReader), Mock(NoticeReader))

        when:
        def allDependencies = buildAutomationSystemProcessor.getAllDependencies()

        then:
        numBasDependencies * mockBas.getDependencies(_ as BuildAutomationSystem) >> basDependencies
        basDependencySources.size() * mockBas.getDependencies({ DependencySource ds -> basDependencySources.contains(ds) }) >> { DependencySource ds ->
            def foundDependency = basDependencySourceDependencies.find { it.artifact.contains(ds.name) }
            ([] + (foundDependency ?: [])) as Set<Dependency>
        }
        1 * mockBas.getDependencySources() >> basDependencySources
        numBasDependencies + numBasDependencySourceDependencies == allDependencies.size()
        allDependencies.containsAll([basDependencies, basDependencySourceDependencies].flatten())

        where:
        dependencySourceNamesSet                                              | numBasDependencies | numBasDependencySourceDependencies
        ["Dependency Source 1", "Dependency Source 2", "Dependency Source 3"] | 1                  | 1
        ["Dependency Source 1", "Dependency Source 2", "Dependency Source 3"] | 1                  | 3
    }

    def "GetAllLicenses"() {
        // TODO
    }

    def "GetAllNotices"() {
        // TODO
    }
}
