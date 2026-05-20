# Modernization Plan: Java 25 & Spring Boot 4.0.x Upgrade

## Overview
This plan upgrades the project to Java 25 LTS and Spring Boot 4.0.x (latest stable GA). It addresses breaking changes, required file modifications, and provides a detailed task breakdown with estimates, risks, preconditions, and acceptance criteria.

## Target Versions
- **Java:** 25 LTS
- **Spring Boot:** 4.0.x (latest stable GA compatible with Java 25)

## Breaking Changes
- Spring Boot 4.x requires Java 17+; we target Java 25.
- Deprecated/removed APIs in Spring Boot 3.x (e.g., WebSecurityConfigurerAdapter, some Actuator endpoints).
- Jakarta EE namespace migration (javax.* → jakarta.*).
- Gradle plugin and dependency version updates.
- Potential changes in configuration properties and default behaviors.

## Files to Change
- `build.gradle` (Java version, Spring Boot plugin, dependencies)
- `settings.gradle` (plugin repositories)
- `gradle.properties` (if present, for JVM/toolchain settings)
- `.github/workflows/build.yml` (CI: JDK version, build steps)
- `src/main/resources/application.properties` (property compatibility)
- Any other files referencing old Java/Spring versions

## Preconditions
- Existing tests must pass on current codebase.
- No uncommitted changes in the working directory.
- Backup or branch created for upgrade work.

## Acceptance Criteria
- Project builds and tests pass with Java 25 and Spring Boot 4.0.x.
- No deprecated/removed API usage remains.
- CI workflow uses Java 25.
- All breaking changes are addressed.
- Application runs as expected in local and CI environments.

---

# Task Breakdown
See `tasks.json` for detailed tasks, estimates, and risk levels.
