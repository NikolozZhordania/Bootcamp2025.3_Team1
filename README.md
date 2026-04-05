<a id="readme-top"></a>

<!-- PROJECT SHIELDS -->
<div align="center">

[![Contributors][contributors-shield]][contributors-url]
[![CI/CD][cicd-shield]][cicd-url]

</div>

<!-- PROJECT HEADER -->
<br />
<div align="center">
  <h1>🏦 TBC Bank — End-to-End Test Automation Suite</h1>
  <p align="center">
    A full-cycle QA automation project covering UI, API, Visual Regression & Performance testing for <strong>tbcbank.ge</strong>
    <br />
    Built during <strong>TBC Bootcamp 2025.3</strong> · Team 1 · Agile workflow · 15,000+ lines of code
    <br /><br />
    <a href="https://github.com/NikolozZhordania/Bootcamp2025.3_Team1"><strong>📂 Explore the Repo »</strong></a>
    &nbsp;·&nbsp;
    <a href="https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/issues">🐛 Report Bug</a>
    &nbsp;·&nbsp;
    <a href="https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/issues">✨ Request Feature</a>
  </p>
</div>

---

<!-- TABLE OF CONTENTS -->
<details>
  <summary>📋 Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#team">Team</a></li>
    <li><a href="#feature-coverage">Feature Coverage</a></li>
    <li><a href="#tech-stack">Tech Stack</a></li>
    <li><a href="#repository-structure">Repository Structure</a></li>
    <li><a href="#test-scenarios">Test Scenarios</a></li>
    <li><a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#running-tests">Running Tests</a></li>
      </ul>
    </li>
    <li><a href="#cicd">CI/CD</a></li>
    <li><a href="#ai-assisted-quality-engineering">AI-Assisted Quality Engineering</a></li>
    <li><a href="#bug-report">Bug Report</a></li>
    <li><a href="#contributing">Contributing</a></li>
  </ol>
</details>

---

## About The Project

This project is the **final deliverable** of TBC Bootcamp 2025.3 — a complete, production-grade automation workflow targeting the **Locations & ATMs** feature of [tbcbank.ge](https://tbcbank.ge).

The team operated in a **real Agile environment**: daily standups on Discord, sprint planning on Jira, feature branches with merge requests into `main`, and automated GitLab CI pipelines ensuring every merge was validated automatically.

**What makes this project stand out:**

- 🧪 **15,000+ lines** of clean, maintainable test automation code
- 🔄 Runs successfully in **headed, headless, and CI/CD** modes
- 🤖 Incorporates **AI-assisted quality engineering** as part of the workflow
- 📊 Full **Allure reporting** with screenshots, logs, and parallel execution
- 🌐 Covers **UI + API + Visual Regression + Performance** — true end-to-end QA ownership

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Team

<div align="center">
<table>
  <tr>
    <td align="center" width="160">
      <img src="https://github.com/NikolozZhordania.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Nikoloz Zhordania</b><br/>
      <a href="https://github.com/NikolozZhordania">@NikolozZhordania</a>
    </td>
    <td align="center" width="160">
      <img src="https://github.com/NikaButbaia.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Nikoloz Butbaia</b><br/>
      <a href="https://github.com/LexoMeshveliani">@NikaButbaia</a>
    </td>
    <td align="center" width="160">
      <img src="https://github.com/LexoMeshveliani.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Lekso Meshveliani</b><br/>
      <a href="https://github.com/NikaButbaia">@LexoMeshveliani</a>
    </td>
  </tr>
  <tr>
    <td align="center" width="160">
      <img src="https://github.com/mariamarsenidze.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Mariam Arsenidze</b><br/>
      <a href="https://github.com/mariamarsenidze">@mariamarsenidze</a>
    </td>
    <td align="center" width="160">
      <img src="https://github.com/jakubardiaa-creator.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Jimusi Akubardia</b><br/>
      <a href="https://github.com/jakubardiaa-creator">@jakubardiaa-creator</a>
    </td>
    <td align="center" width="160">
      <img src="https://github.com/Namie30.png" width="80" style="border-radius:50%"/><br/><br/>
      <b>Nikoloz Chixladze</b><br/>
      <a href="https://github.com/Namie30">@Namie30</a>
    </td>
  </tr>
</table>
</div>

<br/>

<div align="center">

| 💬 Discord | 📋 Jira | 🌿 GitHub |
|:---:|:---:|:---:|
| Daily standups & pair programming calls | Sprint planning, issue tracking, task distribution | Feature branches → Merge Requests → `main` |

</div>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Feature Coverage

This team was assigned **Team 1 – Locations & ATMs** on [tbcbank.ge](https://tbcbank.ge).

<div align="center">
<table>
  <tr>
    <td align="center" width="200">
      <h3>🖥️ UI</h3>
      <sub>
        Homepage & nav menu<br/>
        Locations page<br/>
        Tbc Cards page(QR Code)<br/>
        Branch & ATM filters<br/>
        Working hours calendar<br/>
        Currency exchange flow<br/>
        Edge case inputs
      </sub>
    </td>
    <td align="center" width="200">
      <h3>🔌 API</h3>
      <sub>
        Offers date integrity<br/>
        Exchange rate consistency<br/>
        TBC ID / OAuth / CIBA<br/>
        Financial report metadata<br/>
        Countdown validation
      </sub>
    </td>
    <td align="center" width="200">
      <h3>⚡ Performance</h3>
      <sub>
        Smoke baseline (ka-GE)<br/>
        Stress / breaking point<br/>
        Load under 150 VUs<br/>
        ATM & Branch API coverage
      </sub>
    </td>
    <td align="center" width="200">
      <h3>👁️ Visual Regression</h3>
      <sub>
        Navigation menu stability<br/>
        Locations page consistency<br/>
        TBC Cards & QR code flow<br/>
        Cross-run diff detection
      </sub>
    </td>
  </tr>
</table>
</div>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Tech Stack

<div align="center">

[![Java][java-shield]][java-url]
[![Maven][maven-shield]][maven-url]
[![Playwright][playwright-shield]][playwright-url]
[![TestNG][testng-shield]][testng-url]
[![RestAssured][restassured-shield]][restassured-url]
[![K6][k6-shield]][k6-url]
[![Allure][allure-shield]][allure-url]
[![GitLab CI][gitlab-shield]][gitlab-url]
[![Jira][jira-shield]][jira-url]
[![MyBatis][mybatis-shield]][mybatis-url]
[![Xerial SQLite][xerial-shield]][xerial-url]
[![ZXing][zxing-shield]][zxing-url]
[![Hamcrest][hamcrest-shield]][hamcrest-url]
[![Groovy][groovy-shield]][groovy-url]
[![Lombok][lombok-shield]][lombok-url]
[![Faker][faker-shield]][faker-url]
[![Reflections][reflections-shield]][reflections-url]

</div>

<br/>

<div align="center">
<table>
  <tr>
    <th>🧩 Layer</th>
    <th>🔧 Tool</th>
    <th>📝 Purpose</th>
  </tr>
  <tr>
    <td rowspan="2"><b>Testing</b></td>
    <td>Playwright (Java)</td>
    <td>UI automation — cross-browser, headed & headless</td>
  </tr>
  <tr>
    <td>TestNG</td>
    <td>Test runner — parallel execution, dependency chains</td>
  </tr>
  <tr>
    <td><b>API</b></td>
    <td>Rest Assured</td>
    <td>API testing — request/response validation</td>
  </tr>
  <tr>
    <td><b>Performance</b></td>
    <td>K6</td>
    <td>Load, smoke & stress testing scenarios</td>
  </tr>
  <tr>
    <td><b>Visual Regression</b></td>
    <td>Playwright Snapshots</td>
    <td>Screenshot comparison across runs</td>
  </tr>
  <tr>
    <td><b>Reporting</b></td>
    <td>Allure</td>
    <td>Screenshots, logs, history trends</td>
  </tr>
  <tr>
    <td><b>CI/CD</b></td>
    <td>GitLab CI</td>
    <td>Automated test runs on every push / MR</td>
  </tr>
  <tr>
    <td><b>Project Mgmt</b></td>
    <td>Jira + Zephyr Scale</td>
    <td>Issues, sprints, test scenario management</td>
  </tr>
  <tr>
    <td><b>Build</b></td>
    <td>Maven</td>
    <td>Dependency management & build lifecycle</td>
  </tr>
</table>
</div>

<br/>

### 🏗️ Architecture Overview

```mermaid
flowchart TD
    A[👨‍💻 Developer Push / MR] --> B[⚙️ GitLab CI Pipeline]

    subgraph test["🧪 Test Stage (Parallel)"]
        C[🖥️ UI Tests\nPlaywright + TestNG]
        D[🔌 API Tests\nRest Assured]
        E[⚡ Performance Tests\nK6 · Manual]
        F[👁️ Visual Regression\nPlaywright Snapshots]
    end

    B --> C
    B --> D
    B --> E
    B --> F

    subgraph report["📊 Report Stage"]
        G[📊 Allure Report\nmvn allure:report]
    end

    C --> G
    D --> G
    E --> G
    F --> G

    subgraph deploy["🚀 Deploy Stage"]
        H[📁 Artifact Upload]
        I[🌐 GitLab Pages\nAllure Published]
    end

    G --> H
    G --> I

    I --> J{All Pass?}
    J -- ✅ Yes --> K[Pipeline Green]
    J -- ❌ No --> L[Notify Team]

    subgraph "🧱 Framework Layers"
        direction LR
        O([base/\nBaseTest]) -->|extends| T([tests/])
        N([constants/\nTest Data]) -->|injected into| T
        P([pages/\nLocators only]) -->|used by| S([steps/\nFluent API])
        S -->|called by| T
        T --> PW([Playwright\nPage])
    end

    subgraph "🛠️ Dev Workflow"
        direction LR
        Q([📋 Jira Issue]) --> R([🌿 Feature Branch])
        R --> MR([🔁 Merge Request])
        MR --> Main([🔀 main])
    end
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Repository Structure

```
Bootcamp2025.3_Team1/
│
├── 📁 src/
│   │
│   ├── 📁 main/
│   │   ├── java/ge/tbc/testautomation/tbcbankapp/
│   │   │   ├── 📂 api/
│   │   │   │   ├── client/       # HTTP client setup
│   │   │   │   ├── data/         # API constants & test data
│   │   │   │   ├── steps/        # API step classes (fluent)
│   │   │   │   └── utils/        # API helpers
│   │   │   │
│   │   │   ├── 📂 db/
│   │   │   │   ├── mappers/      # MyBatis mappers
│   │   │   │   ├── models/       # DB model classes
│   │   │   │   └── utils/        # DB utilities
│   │   │   │
│   │   │   ├── 📂 performance/
│   │   │   │   ├── data/         # Performance test data
│   │   │   │   ├── servies/      # K6 service configs
│   │   │   │   ├── steps/        # Performance step classes
│   │   │   │   └── utils/        # Performance utilities
│   │   │   │
│   │   │   └── 📂 ui/
│   │   │       ├── data/         # UI constants & test data
│   │   │       ├── pages/        # Page Object Model classes
│   │   │       ├── steps/        # UI step classes (fluent)
│   │   │       └── utils/        # UI helpers
│   │   │
│   │   └── resources/
│   │       ├── performance/      # K6 scripts
│   │       └── mybatis-config.xml
│   │
│   └── 📁 test/
│       └── java/ge/tbc/testautomation/tbcbankapp/
│           ├── 📂 api/
│           │   ├── base/         # API BaseTest
│           │   └── tests/        # API test classes
│           │
│           ├── 📂 base/          # Shared BaseTest
│           │
│           ├── 📂 performance/
│           │   ├── base/         # Performance BaseTest
│           │   └── tests/        # Performance test classes
│           │
│           └── 📂 ui/
│               ├── base/         # UI BaseTest
│               └── tests/        # UI test classes
│
├── 📁 visual-regression-tests/   # Snapshot comparison tests
│
├── 📁 ai/
│   ├── prompt.txt                # AI prompt used for code review
│   ├── result.txt                # Raw AI-generated output
│   └── analysis.md               # Team evaluation of AI quality
│
├── 📁 bug-report/                # Real bugs found on tbcbank.ge
├── 📁 zephyr/                    # Zephyr Scale scenario exports
├── 📁 allure-results/            # Generated Allure report data
├── README.md
└── pom.xml
```

### 📦 Package Dependency Flow

```mermaid
flowchart LR
    B([🏗️ base/\nBaseTest]) -->|extends| T([🧪 tests/])

    C([📦 constants/\nTest Data]) -->|injected into| T

    L([📄 pages/\nLocators only]) -->|used by| S([⚙️ steps/\nFluent API])
    S -->|called by| T

    T --> PW([🎭 Playwright\nPage])

    style B fill:#4a4e69,color:#fff
    style T fill:#2d6a4f,color:#fff
    style S fill:#1d3557,color:#fff
    style L fill:#457b9d,color:#fff
    style C fill:#6d6875,color:#fff
    style PW fill:#222,color:#fff
```

> **Convention:** No locators in steps or tests · No business logic in pages · All values sourced from `data/`

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Test Scenarios

All **21 scenarios** were designed in **Zephyr Scale** covering UI, API, Performance and Visual Regression layers — every single one automated and passing in CI.

<div align="center">

| ID | Scenario | Layer | Status |
|----|----------|-------|--------|
| SCRUM-T1 | Locations — Nearest ATM Selection and Location Validation via Geocoding API | 🖥️ UI | ✅ Automated |
| SCRUM-T2 | ATM & Branch API — Load Test under 150 Virtual Users (Production-like) | ⚡ Performance | ✅ Done |
| SCRUM-T3 | Locations — Branch Selection with District Search and 24/7 Availability Validation | 🖥️ UI | ✅ Automated |
| SCRUM-T4 | ATM & Branch API — Smoke Test · Baseline Availability (ka-GE) | ⚡ Performance | ✅ Done |
| SCRUM-T5 | ATM & Branch API — Stress Test · Breaking Point Analysis | ⚡ Performance | ✅ Done |
| SCRUM-T6 | Locations — ATM Search with Invalid Address and Zero Results Validation | 🖥️ UI | ✅ Automated |
| SCRUM-T7 | Locations — ATM List Filtering by City Dropdown and Validation via Geocoding API | 🖥️ UI | ✅ Automated |
| SCRUM-T8 | ATM Search — Non-Empty Result Set Returned for Whitespace-Only Location Input | 🖥️ UI | ✅ Automated |
| SCRUM-T9 | ATM Search — UI Remains Stable When Location Input Exceeds Maximum Length | 🖥️ UI | ✅ Automated |
| SCRUM-T10 | Exchange Rates API — Commercial vs Official Conversion Consistency For Dynamically Selected Currency | 🔌 API | ✅ Automated |
| SCRUM-T11 | Locations — Branch & Money Input Calendar Validation | 🖥️ UI | ✅ Automated |
| SCRUM-T12 | Abroad Money Transfer — Currency Exchange Validation | 🖥️ UI | ✅ Automated |
| SCRUM-T13 | QR Code Navigation — TBC Cards QR Verification | 🖥️ UI | ✅ Automated |
| SCRUM-T14 | Randomized Location Search — 10 Closest ATMs, Branches and Money Input Devices | 🖥️ UI | ✅ Automated |
| SCRUM-T15 | Locations — Branch Search and Selection | 🖥️ UI | ✅ Automated |
| SCRUM-T16 | Offers API — Active TBC Card Offers Date Integrity and Countdown Validation | 🔌 API | ✅ Automated |
| SCRUM-T17 | Offers — Countdown Integrity and Time Travel Validation | 🔌 API | ✅ Automated |
| SCRUM-T18 | TBC ID API — OpenID, JWKS, OAuth, CIBA & Userinfo Validation | 🔌 API | ✅ Automated |
| SCRUM-T19 | Visual Regression — Navigation Menu & Locations Page UI Consistency | 👁️ Visual | ✅ Automated |
| SCRUM-T20 | Visual Regression — TBC Cards Flow, QR Code & Redirect UI Validation | 👁️ Visual | ✅ Automated |
| SCRUM-T21 | Financial Reports API — File Integrity and Metadata Validation For All Downloadable Reports | 🔌 API | ✅ Automated |

</div>

> Full scenario export available in `/zephyr/`

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Node.js (for K6 performance tests)
- Allure CLI (for report generation)

```sh
# Install Allure CLI (macOS)
brew install allure

# Install K6 (macOS)
brew install k6

# Install Playwright browsers
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

### Installation

1. Clone the repository:

```sh
git clone https://github.com/NikolozZhordania/Bootcamp2025.3_Team1.git
cd Bootcamp2025.3_Team1
```

2. Install Maven dependencies:

```sh
mvn clean install -DskipTests
```

### Running Tests

**Run all UI tests (headed):**
```sh
mvn test -Dtest="**/ui/tests/**"
```

**Run all UI tests (headless):**
```sh
mvn test -Dheadless=true -Dtest="**/ui/tests/**"
```

**Run API tests:**
```sh
mvn test -Dtest="**/api/tests/**"
```

**Run performance tests:**
```sh
mvn test -Dtest="**/performance/tests/**"
```

**Run full suite in parallel:**
```sh
mvn test -Dthreads=4
```

**Generate Allure report:**
```sh
allure serve allure-results
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## CI/CD

All tests are integrated with **GitLab CI** and run automatically on every push and merge request to `main`.

**Pipeline includes:**

- ✅ Headless UI test execution
- ✅ API test execution
- ✅ Performance test execution
- ✅ Visual regression checks
- ✅ Allure report generation & artifact upload
- ✅ Parallel test execution
- ✅ Failure notifications

```mermaid
flowchart LR
    A([git push / MR]) --> B([GitLab CI Triggered])
    B --> C([Install Dependencies])
    C --> D([Run Tests in Parallel])
    D --> E([Generate Allure Report])
    E --> F([Upload Artifacts])
    F --> G{All Pass?}
    G -- ✅ Yes --> H([Pipeline Green])
    G -- ❌ No --> I([Notify Team])
```

Pipeline configuration: `.gitlab-ci.yml`

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## AI-Assisted Quality Engineering

The team leveraged AI as part of the quality engineering workflow, focusing on **AI code review** — feeding Playwright test code to an LLM and critically evaluating the output.

**Prompt used (from `/ai/prompt.txt`):**
```
You are a senior test automation engineer. Review this Playwright test:
- Find flakiness risks
- Suggest better locators
- Improve assertions
- Suggest overall improvements
```

| File | Description |
|------|-------------|
| `ai/prompt.txt` | Full prompt submitted to the AI model |
| `ai/result.txt` | Raw AI-generated output |
| `ai/analysis.md` | Team's critical evaluation of AI output quality |

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Bug Report

The team identified and documented a real bug found on tbcbank.ge during exploratory testing.

**Bug Title:** Calculation proceeds with empty input field

| Field | Detail |
|-------|--------|
| **Steps** | 1. Open the page · 2. Leave amount field empty · 3. Click Calculate |
| **Expected** | Validation message is displayed |
| **Actual** | Calculation proceeds without validation |
| **Severity** | Medium |
| **Evidence** | Screenshots available in `/bug-report/` |

> Full bug reports with screenshots are in the `/bug-report/` directory.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

## Contributing

This project was built collaboratively using a **feature branch workflow**:

1. Pick up an issue from the Jira board
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit with clear messages: `git commit -m 'Add: describe your change'`
4. Push: `git push origin feature/your-feature-name`
5. Open a **Pull Request** targeting `main` — the team reviews before merging

[![Contributors](https://img.shields.io/badge/Contributors-6-blue?style=for-the-badge)](https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/graphs/contributors)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

---

<!-- MARKDOWN LINKS & BADGES -->
[contributors-shield]: https://img.shields.io/badge/Contributors-6-blue?style=for-the-badge
[contributors-url]: https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/graphs/contributors
[forks-shield]: https://img.shields.io/badge/Forks-0-blue?style=for-the-badge
[forks-url]: https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/network/members
[stars-shield]: https://img.shields.io/badge/Stars-0-blue?style=for-the-badge
[stars-url]: https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/stargazers
[issues-shield]: https://img.shields.io/badge/Issues-0-blue?style=for-the-badge
[issues-url]: https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/issues
[license-shield]: https://img.shields.io/badge/License-MIT-green?style=for-the-badge
[license-url]: https://github.com/NikolozZhordania/Bootcamp2025.3_Team1/blob/main/LICENSE
[cicd-shield]: https://img.shields.io/badge/CI%2FCD-GitLab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white
[cicd-url]: https://gitlab.com

[java-shield]: https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]: https://openjdk.org/
[maven-shield]: https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white
[maven-url]: https://maven.apache.org/
[playwright-shield]: https://img.shields.io/badge/Playwright-45ba4b?style=for-the-badge&logo=playwright&logoColor=white
[playwright-url]: https://playwright.dev/
[testng-shield]: https://img.shields.io/badge/TestNG-FF7300?style=for-the-badge&logo=testng&logoColor=white
[testng-url]: https://testng.org/
[restassured-shield]: https://img.shields.io/badge/Rest%20Assured-4DB33D?style=for-the-badge&logo=java&logoColor=white
[restassured-url]: https://rest-assured.io/
[k6-shield]: https://img.shields.io/badge/K6-7D64FF?style=for-the-badge&logo=k6&logoColor=white
[k6-url]: https://k6.io/
[allure-shield]: https://img.shields.io/badge/Allure-FF6B35?style=for-the-badge&logo=qase&logoColor=white
[allure-url]: https://allurereport.org/
[gitlab-shield]: https://img.shields.io/badge/GitLab%20CI-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white
[gitlab-url]: https://gitlab.com
[jira-shield]: https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=jira&logoColor=white
[jira-url]: https://www.atlassian.com/software/jira
[mybatis-shield]: https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=mybatis&logoColor=white
[mybatis-url]: https://mybatis.org/mybatis-3/
[xerial-shield]: https://img.shields.io/badge/Xerial%20SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white
[xerial-url]: https://github.com/xerial/sqlite-jdbc
[zxing-shield]: https://img.shields.io/badge/ZXing-4285F4?style=for-the-badge&logo=google&logoColor=white
[zxing-url]: https://github.com/zxing/zxing
[hamcrest-shield]: https://img.shields.io/badge/Hamcrest-6DB33F?style=for-the-badge&logo=java&logoColor=white
[hamcrest-url]: https://hamcrest.org/
[groovy-shield]: https://img.shields.io/badge/Groovy-4298B8?style=for-the-badge&logo=apachegroovy&logoColor=white
[groovy-url]: https://groovy-lang.org/
[lombok-shield]: https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white
[lombok-url]: https://projectlombok.org/
[faker-shield]: https://img.shields.io/badge/JavaFaker-FF6B6B?style=for-the-badge&logo=java&logoColor=white
[faker-url]: https://github.com/DiUS/java-faker
[reflections-shield]: https://img.shields.io/badge/Reflections-6D6D6D?style=for-the-badge&logo=java&logoColor=white
[reflections-url]: https://github.com/ronmamo/reflections
