# Modernization Plan — exampleSpringboot320M3

Project
- Path: L:\\work\\workspace\\exampleSpringboot320M3
- Detected language: java (Gradle / Spring Boot)
- Generated: 2026-05-19T23:28:00Z (from assessment timestamp)

Purpose
- Move the project from a local/milestone development posture to a stable, production-ready cloud-hosted deployment on Azure (containerized + CI/CD), improve security posture (dependency CVE fixes), and ensure supported runtime versions.

Scope (from assessment)
- Use all categories in assessment report (full-scope): runtime & language, framework stability (Spring Boot milestone usage), dependency security, cloud database and secret management, containerization & CI/CD, observability & validation.

Key assessment findings (summary)
- Detected languages: java
- Build files: Gradle (build.gradle, gradlew)
- Uses Spring Boot milestone artifacts and repo.spring.io/milestone -> replace with GA
- Declared java toolchain version: 25 (bleeding-edge) -> prefer LTS (21 or 17)
- AppCAT run failed (tooling missing)
- Recommendation: dependency scan, stabilize Spring Boot & Java versions, containerize, create CI/CD and cloud DB migration plan

Warnings / Blockers (from assessment)
- AppCAT is not installed in the environment (AppCAT run failed). Automated MCP assessment is incomplete.
- Bleeding-edge Java 25 and Spring Boot milestone artifacts may not be supported by Azure or buildpacks.
- Many dependencies are resolved via Spring BOM; explicit versions should be checked during dependency scan.

Success criteria
- Tests and smoke tests pass in staging
- App built and packaged as OCI image and pushed to registry
- Application runs on target environment (Container Apps or App Service containers) and connects to managed DB
- No critical or high-severity unaddressed CVEs blocking production rollout
- Rollback path + runbook documented

Phases & Estimates (high-level)
- Phase 0 — Stabilization & Prechecks (1–3 days)
  - Install AppCAT, run full assessment
  - Run dependency CVE scan (Snyk/OWASP/GitHub Dependabot)
  - Confirm target Java LTS (21 recommended) and Spring Boot GA target
- Phase 1 — Runtime & Framework Stabilization (3–7 days)
  - Pin Spring Boot to GA, align plugin versions, remove milestone repos from prod
  - Upgrade Java toolchain to targeted LTS (21), update Gradle toolchain config
- Phase 2 — Security & Dependencies (2–5 days)
  - Fix high/critical CVEs, upgrade libraries, lock versions where needed
- Phase 3 — Cloud Readiness & Infra (5–10 days)
  - Externalize secrets (Key Vault), plan DB migration (Postgres -> Azure DB)
  - Containerize and create Dockerfile and image building
  - Implement CI/CD pipeline (GH Actions) with build, tests, scans, push to ACR
- Phase 4 — Deploy & Validate (2–4 days)
  - Deploy to staging, run integration/smoke tests, observability (App Insights)
  - Production rollout + runbook
- Phase 5 — Documentation & Handover (1–2 days)
  - Update README, runbooks, and rollback procedures

Task breakdown (detailed)
- See tasks.json (JSON machine-readable tasks) for IDs, types, targets, dependencies and success criteria.
- Below is human-friendly overview of each task (IDs correspond to tasks.json):

1) task-01 — Install & run AppCAT pre-check (tooling)
   - Goal: Install AppCAT on the environment and run a full AppCAT report to capture automated checks.
   - Estimate: 0.5–1 day
   - Dependencies: none
   - Success criteria: AppCAT completes and report saved to .github/modernize/assessment/

2) task-02 — Dependency & CVE scan
   - Goal: Run dependency scanning (OWASP Dependency-Check, Snyk, or GitHub Dependabot) to identify CVEs and versions.
   - Estimate: 1–2 days (scan + triage)
   - Dependencies: task-01 recommended (but can run in parallel)
   - Success criteria: CVE report produced with prioritized remediation list

3) task-03 — Decide and lock target platform (Java + Spring Boot)
   - Goal: Choose target Java LTS (21 recommended) and Spring Boot GA version to target (replace milestone artifacts).
   - Estimate: 0.5 day
   - Dependencies: task-02 (compatibility info) 
   - Success criteria: Target versions documented and approved

4) task-04 — Upgrade Spring Boot to GA / remove milestone repos
   - Goal: Update build.gradle to use Spring Boot GA BOM and remove repo.spring.io/milestone from production builds (keep only for experimental branches if needed).
   - Estimate: 1 day
   - Dependencies: task-03
   - Success criteria: Build successfully resolves dependencies from stable repos

5) task-05 — Upgrade Java toolchain to LTS (21) and validate
   - Goal: Update Gradle toolchain/sourceCompatibility, CI matrices, and local dev instructions. Validate compilation and tests.
   - Estimate: 2–3 days (fix compiler / dependency compatibility)
   - Dependencies: task-03, task-04
   - Success criteria: Project builds and tests run under Java 21 (or chosen LTS)

6) task-06 — Pin & remediate vulnerable dependencies
   - Goal: Apply necessary upgrades/fixes for high/critical CVEs identified in task-02, pin versions where needed.
   - Estimate: 2–4 days (varies with number of fixes)
   - Dependencies: task-02, task-04, task-05
   - Success criteria: No outstanding critical CVEs; regression tests pass

7) task-07 — Externalize configuration & integrate secret store
   - Goal: Move DB and sensitive config to environment variables / properties and integrate Azure Key Vault (or other secret provider).
   - Estimate: 1–2 days
   - Dependencies: task-05, task-06
   - Success criteria: All secrets read from Key Vault in staging

8) task-08 — Database migration plan (Postgres → Azure Database for PostgreSQL)
   - Goal: Produce migration strategy, scripts, connection strings, and cutover steps for production data migration.
   - Estimate: 3–5 days (planning + scripts + dry run)
   - Dependencies: task-07
   - Success criteria: Migration runbook and scripts tested in staging with sample data

9) task-09 — Containerize application (Dockerfile + multi-stage build)
   - Goal: Create production-ready Dockerfile, ensure build reproducibility, small base image, and non-root runtime.
   - Estimate: 1–2 days
   - Dependencies: task-05, task-04, task-06
   - Success criteria: Successful Docker image build and local run

10) task-10 — CI/CD pipeline (GitHub Actions)
    - Goal: Implement CI pipeline with build, test, dependency-scan, container build and publish to ACR, and CD to staging (Container Apps / App Service).
    - Estimate: 2–4 days
    - Dependencies: task-02, task-09
    - Success criteria: Pipeline builds image and deploys to staging on merge

11) task-11 — Observability & logging (App Insights)
    - Goal: Configure Application Insights (or equivalent), health checks, metrics, and alerts.
    - Estimate: 0.5–1 day
    - Dependencies: task-09, task-10
    - Success criteria: Metrics and logs visible in staging environment

12) task-12 — Staging validation & smoke tests
    - Goal: Deploy to staging, run smoke, integration and performance sanity tests.
    - Estimate: 1–2 days
    - Dependencies: task-08, task-10, task-11
    - Success criteria: Pass smoke and critical integration tests

13) task-13 — Production rollout, rollback and runbook
    - Goal: Production deployment runbook (cutover steps, rollback), finalize monitoring and incident flows.
    - Estimate: 0.5–1 day
    - Dependencies: task-12
    - Success criteria: Production deployment completed successfully or safely rolled back per runbook

Language-specific notes (Java / Spring Boot)
- Java: Assessment shows a declared toolchain language version 25. Target Java 21 (LTS) for broader runtime and vendor support. Validate third-party libs for Java 21 compatibility and update build toolchain settings in build.gradle and CI.
- Spring Boot: Assessment indicates milestone artifacts; replace with GA Spring Boot 4.x (GA) or alternatively choose a supported 3.x path if constraints require. Remove repo.spring.io/milestone from production builds.
- Build: Update Gradle toolchain config and test suites for new Java target. Ensure annotation processing (spring-boot-configuration-processor, Lombok) is compatible.

Operational guidance & guardrails
- Keep milestone repositories only on dedicated experimental branches, never in production CI builds.
- Require dependency-scan step in CI (fail on critical CVEs).
- Build images with reproducible multi-stage Dockerfiles, small runtime images (distroless or slim), non-root user and proper health probes.

Next steps & recommended immediate actions
1. Save plan.md and tasks.json to .github/modernize/exampleSpringboot320M3/
2. Run task-01 and task-02 (AppCAT + dependency scan) in parallel — they will surface concrete blockers and inform target version decisions.
3. After task-02, schedule a short review meeting to select the target Java and Spring Boot GA version (task-03) so the upgrade tasks can proceed.

If you want, I can:
- Produce a GitHub Actions pipeline manifest (workflow file) tailored to this plan.
- Produce a starter Dockerfile for the project (multi-stage with Jib or standard Java Dockerfile).
- Produce concrete gradle edits (diff) to move away from repo.spring.io/milestone and set Java toolchain to 21.
