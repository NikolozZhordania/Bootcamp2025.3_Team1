# Task 5 – AI-Assisted Quality Engineering
## Analysis: Evaluation of AI Output — MERGED
### Tool used: Claude (Anthropic) — Claude Sonnet 4.6 + ChatGPT (GPT-4o) + Google Gemini

---

# SECTION 1 — AI Code Review + Scenario Generation + Validation

## Summary of usage
Claude was used across 8 distinct prompts covering:
- Task distribution and project planning
- Code review comments (UI, API, Bug Reporting, Test Case Design, Performance)
- CI/CD configuration (pom.xml optimisation)
- Technical Q&A (database impact on CI/CD, Git commands)

---

## What the AI did well

**Consistency across review comments**
All 5 review comments (UI, API, Bug, Test Case, Performance) followed
the same structure: rule explanation → correct example → wrong example →
checklist. This consistency was maintained without being explicitly requested
after the first prompt — the AI inferred the pattern from context.

**Domain-specific adaptation**
Each review comment was adapted to the correct technology:
- UI review used Playwright Java locator syntax and @Step annotations
- API review introduced the client/steps/tests layering specific to Rest Assured
- Performance review added K6-specific patterns (Trend metrics, SharedArray)
- The pom.xml was optimised specifically for the project's existing stack

**Scope extension**
When asked to "add best practices", the AI added rules not in the original
list that are genuinely common failure points (e.g. no Thread.sleep,
assertions only in test classes, one SLF4J binding on classpath).

**Practical CI/CD advice**
The database/CI question received a tiered answer with 4 concrete options
ranked by complexity, with a specific recommendation for the bootcamp
project context (export to JSON, commit to repo, use SharedArray).

---

## What needed correction or was missing

**Language specificity**
Some examples used generic feature names (e.g. "Login") rather than
TBC Bank-specific features. The Currency Exchange examples were accurate,
but other feature examples required manual adaptation to the actual feature.

**Tool version assumptions**
The pom.xml used dependency versions from the existing file without
independently verifying whether newer stable versions exist. Version
numbers should be verified against Maven Central before use in production.

**No actual code execution**
AI-generated code examples (K6 scripts, Java step classes, pom.xml)
were not compiled or run. All outputs required manual validation before
committing to the repository.

**Zephyr Scale specifics**
The test case design review was written for generic test management tool
conventions. Some field names and status values may differ in the specific
version of Zephyr Scale used in the project.

---

## Conclusion

AI assistance significantly reduced the time required to produce
consistent, detailed review standards across 5 different testing domains.
Estimated time saving: ~4-6 hours of manual documentation writing.

The outputs were usable as first drafts in all cases, but required
human review for: technology-specific accuracy, version verification,
and adaptation to the actual TBC Bank feature set.

AI is most effective in this workflow as a documentation accelerator
and standards enforcer — not as a replacement for domain knowledge.

Recommended use in future sprints:
- Generate review comment templates at sprint start
- Use for pom.xml and CI/CD config as a starting point
- Always validate generated code against the actual running environment

---
---

# SECTION 2 — AI-Generated E2E Test Scenarios

## 1. Overview

This section evaluates the quality of AI-generated outputs across three phases:
- Phase 1: Edge case scenario ideation
- Phase 2: Full Zephyr Scale test case writing
- Phase 3: Automation code generation

---

## 2. Phase 1 — Edge Case Scenario Generation

### What was good
- AI correctly identified the most impactful boundary conditions for a location
  filter input field (empty, whitespace, oversized, invalid, special characters).
- Scenarios covered both UI stability (crash/error resilience) and functional
  correctness (empty results, fallback states).
- Scenarios were scoped appropriately to the page — no off-topic suggestions.
- The list followed a positive-to-negative ordering, consistent with the
  code review guidelines provided.

### What was missing or weak
- Scenario 4 (all filters deselected) and Scenario 6 (rapid toggling) are
  difficult to automate reliably with Playwright due to timing sensitivity —
  AI did not flag this risk.
- Scenario 8 (geolocation permission denied) requires browser context-level
  configuration that was not discussed. AI could have proactively noted this
  requires a separate BaseTest context setup.
- No API-level edge cases were suggested (e.g. geocoding API timeout,
  malformed API response), even though LocationSteps.java contains
  geocoding validation logic.

### Score: 7.5 / 10

---

## 3. Phase 2 — Zephyr Format Test Case Writing

### What was good
- AI correctly followed all major rules from the code review guidelines:
  - Noun-form step titles (e.g. "Entry of Whitespace-Only Value")
  - Test data separated from descriptions
  - Mandatory expected result on the final step
  - Objective written as a verification goal, not a user story
  - Preconditions listed separately, not as Step 1
- Steps were appropriately decomposed — no combining of multiple actions
  into a single step.
- Both test cases shared identical Steps 1–4 (homepage → nav → locations →
  ATM filter), which is correct and consistent with the existing test suite.
- The format correction (adding --- separators between fields) was applied
  correctly and immediately when requested.

### What was weak
- Initial version of Test Case 1 (whitespace) had an incorrect expected
  result: it stated the ATM list should return zero results, when in
  reality whitespace input should be ignored and the list should remain
  populated. This was a logical error that required user correction.
- Test data for Step 1 repeated in both test cases verbatim — a note about
  extracting shared steps into a reusable Zephyr component test case was
  not suggested.
- "Expected URL" in Step 3 test data showed inconsistency between
  https://www.tbcbank.ge/ka/atms&branches and https://tbcbank.ge/ka/atms&branches
  across iterations.

### Score: 7 / 10

---

## 4. Phase 3 — Automation Code Generation

### What was good
- Generated code correctly followed the existing project patterns:
  - Fluent method chaining with return this
  - Allure annotations (@Epic, @Feature, @Story, @Severity, @Description)
  - dependsOnMethods chain for ordered execution
  - NeedsCookieHandling interface applied correctly
  - FakerHelper used for dynamic test data, consistent with existing usage
- Step method selection was accurate — AI used existing methods from
  LocationSteps.java rather than inventing new ones.
- The bug fix (verifyAtmListCount → verifyATMListIsNotEmpty for whitespace
  scenario) was applied correctly and immediately when flagged.
- Both classes followed the same structural pattern as FilterLocations.java.
- AI proactively identified the need for a new FakerHelper.randomString(int)
  method and provided the implementation.
- testng.xml additions were correctly noted.

### What was weak
- The whitespace string "   " is hardcoded in the test method. Per the
  project's constants rule, this value should live in a constants class
  (e.g. TestData.WHITESPACE_INPUT). AI did not flag this violation.
- oversizedInput is declared as an instance field with a FakerHelper call.
  This means the value is generated at object construction time, not at
  test runtime. AI did not comment on this distinction.
- No @DisplayName annotation was added to test methods, which is listed
  as a requirement in the code review guidelines checklist.

### Score: 8 / 10

---

## 5. Cross-Cutting Observations

| Criterion                          | Result   |
|------------------------------------|----------|
| Followed provided style guide      | Yes      |
| Correctly used existing code       | Yes      |
| Self-corrected after feedback      | Yes      |
| Caught own logical errors          | No       |
| Flagged automation risk areas      | No       |
| Consistent URL formatting          | Partial  |
| Constants rule compliance          | No       |
| @DisplayName compliance            | No       |

---

## 6. Summary

The AI performed well at pattern matching and template following. The main weakness
was in domain reasoning: the whitespace scenario expected result was logically incorrect,
and the AI did not catch this independently. This type of reasoning about application
behaviour remains an area where AI output needs human review before acceptance.

**Overall Score: 7.5 / 10**

---
---

# SECTION 3 — AI Bug Analysis (Claude as Senior QA Reviewer)

## 1. Overview

Claude was assigned the role of a senior QA automation engineer and tasked with
analyzing real Jira bug reports written during manual testing of TBC Bank's Locations
page. The AI provided structured feedback on clarity, completeness, and severity
accuracy for each report.

---

## 2. What the AI Was Used For

| # | Use Case | Prompt Type |
|---|---|---|
| 1 | Bug report quality analysis | Structured review |
| 2 | Severity & impact assessment | Risk evaluation |
| 3 | Rewriting actual result sections | Content improvement |
| 4 | DevTools / console log tutorial | Knowledge question |
| 5 | Interpreting "no console errors" finding | Diagnostic reasoning |
| 6 | Detecting a copy-paste error in a report | Error detection |
| 7 | Identifying a duplicate bug report | Duplicate detection |
| 8 | Cross-report pattern recognition | Trend analysis |
| 9 | Generating deliverable files | Documentation |

---

## 3. Evaluation of AI Output Quality

### Bug #1 — Online Chat Button: No Action — ★★★★☆ Very Good

The AI provided solid structural improvements and correctly flagged the need for
DevTools console and network observations, third-party widget context, and screen
recording for absence-of-behavior bugs. The severity challenge (Major → borderline)
was reasonable. Limitation: AI slightly over-flagged missing attachments, not knowing
screenshots were already attached in Jira.

### Bug #2 — Phone Number: No Action on Desktop — ★★★★★ Excellent

This was the AI's strongest output. It identified a critical flaw the tester had not
considered: tel: links do not work on desktop by design without a configured calling
app. This fundamentally changes the validity of the bug. The AI explained the
platform-specific behavior, provided three severity scenarios, and recommended
cross-device validation before filing — preventing a potentially invalid bug report
from being filed as Major.

### Bug #3 — Visit a Branch Button (First Submission) — ★★★★★ Excellent — Critical Error Caught

The AI immediately detected that the bug report contained a copy-paste error: the
title said "Visit a Branch" but the steps described the Online Chat button. This
would have made the report completely unusable by any developer. This is one of the
most valuable outputs of the session — catching a human error before it reaches the
development team.

### Duplicate Detection — Online Chat (Second Submission) — ★★★★★ Excellent

When the user resubmitted a report that turned out to be a duplicate of Bug #1, the AI
recognized the overlap, asked a clarifying question, confirmed it was a duplicate,
recommended the correct Jira workflow (update existing ticket, use Linked Issues),
and explained why duplicates are harmful to backlog management.

### Bug #3 — Visit a Branch Button (Corrected Submission) — ★★★★☆ Very Good

The AI correctly identified "Swipe" terminology being ambiguous on desktop, missing
carousel position detail, and overstated severity (Major → Medium). The severity
downgrade recommendation was well-argued.

---

## 4. Strengths of AI Output

| Strength | Evidence |
|---|---|
| Consistent structure | Every report got the same analytical framework |
| Severity calibration | Challenged Major severity in all 3 reports with reasoning |
| Platform awareness | Caught desktop vs. mobile tel: link behavior difference |
| Error detection | Identified copy-paste error before submission |
| Duplicate prevention | Detected duplicate report and guided correct Jira workflow |
| Diagnostic guidance | Taught tester how to use DevTools and interpret findings |
| Pattern recognition | Identified common gaps across all 3 reports |
| Actionable rewrites | Provided ready-to-use rewrites of actual result sections |

---

## 5. Weaknesses / Limitations

| Weakness | Detail |
|---|---|
| Cannot access live URL | AI could not verify bugs independently |
| Repeated attachment flag | Flagged missing screenshots without knowing Jira attachments existed |
| No memory of past reports initially | Each report was analyzed somewhat in isolation |
| Cannot inspect HTML directly | Had to guide the tester to do element inspection manually |
| Suggested DevTools steps verbosely | More detailed than needed for an experienced tester |

---

## 6. Efficiency Gains

| Task | Without AI | With AI |
|---|---|---|
| Bug report review | Manual peer review (delayed) | Instant structured feedback |
| Severity validation | Senior QA judgment required | Calibrated assessment |
| Copy-paste error detection | Might reach developer unfixed | Caught immediately |
| Duplicate detection | Manual backlog search | Flagged in real-time |
| Actual result rewrites | Manual rewriting | AI-generated drafts ready to copy |

Estimated time saved: ~1.5–2 hours of senior QA review time across 3 bug reports.

---

## 7. Conclusion

The AI performed effectively as a QA review assistant. Its most valuable contributions:
1. Catching a critical copy-paste error that would have made a report invalid
2. Identifying a platform behavior misunderstanding (tel: links on desktop)
3. Preventing a duplicate ticket from being filed
4. Challenging overstated severity with structured reasoning
5. Providing real-time diagnostic guidance

The AI did not replace QA judgment — it augmented it.

**Recommended for:** Bug report review, severity calibration, duplicate detection,
and onboarding junior QA engineers to report-writing best practices.

---
---

# SECTION 4 — AI Bug Analysis: K6 Performance (ChatGPT vs Gemini Cross-Examination)

## Overview

Two AI models — ChatGPT (GPT-4o) and Google Gemini — were consulted using identical
initial context. The models reached conflicting conclusions, prompting a structured
cross-examination where each AI's arguments were presented to the other.

---

## Initial Positions

| Dimension | ChatGPT | Gemini |
|---|---|---|
| Root cause | Performance saturation / bottleneck | Server actively blocking / rate limiting |
| Key evidence cited | Throughput plateau, low error rate, payload math | "connection reset by peer" error semantics |
| Tone | Analytical, structured, evidence-based | Formal bug-report format, symptom-focused |
| Confidence | High — supported with multiple data points | High — but based on incomplete reasoning |

---

## Evaluation of Gemini's Initial Output

**Strengths:** Well-structured professional bug report. Correctly identified that
thresholds were breached by 1,300% and flagged it as Critical. Accurately captured
all error types and affected regions.

**Weaknesses:** Misinterpreted "connection reset by peer" as deliberate blocking.
Did not account for payload volume (~425 KB per response) at scale. Did not analyze
the throughput plateau pattern. The 0.14% error rate is inconsistent with rate limiting.

**Assessment:** Structurally strong but analytically incomplete. Conclusion was drawn
from a single ambiguous error string without considering broader system behavior.

---

## Evaluation of ChatGPT's Initial Output

**Strengths:** Correctly identified throughput plateau as the defining symptom of
saturation vs. blocking. Cited five independent data points. Recognized that low
error rate and gradual degradation profile rule out deliberate rejection.

**Weaknesses:** Did not produce a formal bug report structure. Could have emphasized
payload math earlier.

**Assessment:** Analytically superior. Reasoning grounded in multiple corroborating
indicators and correctly identified the root cause.

---

## Evaluation of the Cross-Examination Process

Presenting each AI's arguments to the other proved effective as a validation technique.

- **Gemini under pressure:** Initially defended its position, then acknowledged validity
  of throughput and error rate evidence. After ChatGPT's second rebuttal, Gemini fully
  conceded and revised its conclusion.
- **ChatGPT under pressure:** Held its position and strengthened it with additional
  quantitative reasoning. Did not waver.

This back-and-forth is a useful quality engineering technique: **using AI disagreement
as a stress test for analytical conclusions.**

---

## Final Verdict

**ChatGPT's analysis was correct.** Root cause is network I/O and payload volume
saturation, not server-side blocking. Validated by:

1. ~425 KB response payload creating ~6 MB/s bandwidth ceiling under concurrent load
2. Throughput plateau (not collapse) consistent with saturation, not rejection
3. 0.14–0.28% error rate inconsistent with rate-limiting patterns
4. Clean smoke test baseline ruling out IP filtering or early throttling
5. Linear, gradual response time growth matching resource exhaustion, not a policy threshold

---

## Lessons Learned from AI-Assisted Bug Analysis

| Lesson | Detail |
|---|---|
| AI context quality matters | Gemini's incomplete context led to a misdiagnosis |
| Use AI disagreement productively | Use one AI's arguments as a prompt to the other |
| Cross-validate with data, not symptoms | Gemini focused on one error string; ChatGPT analyzed system-wide behavior |
| AI is not always right on first pass | Both models needed multiple rounds to converge |
| AI output is a starting point | The human made the final call after evaluating both models |

---

## Conclusion

ChatGPT demonstrated stronger analytical reasoning; Gemini demonstrated stronger
formatting and documentation structure. A combined approach — ChatGPT's analysis
with Gemini's bug report format — represents the optimal output for professional QA reporting.

---
---

# Overall Summary Across All Sections

| Section | Task Type | AI Tool | Time Saved | Score |
|---|---|---|---|---|
| Code Review + Validation | Documentation | Claude | ~4–6 hours | — |
| E2E Scenario Generation | Test Design | Claude | Significant | 7.5 / 10 |
| Bug Analysis (3 reports) | QA Review | Claude | ~1.5–2 hours | Avg 4.5 / 5 |
| K6 Performance Analysis | Diagnostic | ChatGPT + Gemini | Hours of manual analysis | ChatGPT wins |

**Key takeaway across all sections:**
AI is most effective as a first-draft accelerator, standards enforcer, and error-catcher.
It consistently required human review for domain-specific correctness, logical edge cases,
and final decision-making. It did not replace QA judgment — it amplified it.
