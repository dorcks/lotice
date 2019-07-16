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
import dev.dorcks.lotice.api.license.LicenseReader
import dev.dorcks.lotice.api.notice.NoticeReader

class BuildAutomationSystemProcessor(
        private val buildAutomationSystem: BuildAutomationSystem,
        private val licenseReader: LicenseReader,
        private val noticeReader: NoticeReader) {

    fun getAllDependencies(): Set<Dependency> {
        return buildAutomationSystem.getDependencies(buildAutomationSystem) +
                buildAutomationSystem.getDependencySources().flatMap { buildAutomationSystem.getDependencies(it) }
                        .toSet()
    }

    fun getAllLicenses(): List<String> {
        return getAllDependencies().map { licenseReader.readLicense(it) }
    }

    fun getAllNotices(): List<String> {
        return getAllDependencies().map { noticeReader.readNotice(it) }
    }
}