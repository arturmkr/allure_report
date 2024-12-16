# Allure Report Action (Local Version)

This is a modified local version of the [Allure Report Action](https://github.com/simple-elf/allure-report-action), originally created by [simple-elf](https://github.com/simple-elf). The purpose of this action is to generate Allure reports with history directly in GitHub Actions, without using Docker. Instead, it uses native Java setup and shell scripts to execute the required commands.

## Features

- Generates Allure test reports with history.
- Uses the Allure CLI utility.
- Eliminates the need for Docker by relying on native Java and shell scripts.
- Implements caching for the Allure CLI to improve workflow performance.
- Fully compatible with GitHub Actions and the original entrypoint script.

## File Structure

```
.github/actions/allure-report-action
├── action.yml         # The action definition file
├── entrypoint.sh      # The shell script to execute the report generation
```

## Key Changes

1. **No Docker Dependency:**
   - Replaced the Docker-based implementation with a composite GitHub Action.
   - Uses `actions/setup-java` to set up Java.
   - Runs the original `entrypoint.sh` script directly on the GitHub runner.

2. **Caching:**
   - Uses `actions/cache` to cache the Allure CLI files to avoid repeated downloads, improving performance.

3. **Preserves Original Functionality:**
   - The `entrypoint.sh` script remains unchanged from the original implementation.

## Requirements

- **Java 8:** Required for the Allure CLI.
- **GitHub Actions Runners:** Must support bash shell scripts.
- **Allure CLI:** Downloaded and cached during the workflow execution.

## Usage

To use this action in your workflow, reference it as a local action:

```yaml
jobs:
  generate-allure-report:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Generate Allure Report
        uses: ./.github/actions/allure-report-action
        with:
          allure_results: "allure-results"
          allure_report: "allure-report"
          gh_pages: "gh-pages"
          allure_history: "allure-history"
          keep_reports: 20
```
## Advantages

- **Performance:** Caching significantly reduces runtime by avoiding repeated Allure CLI downloads.
- **Simplicity:** No changes to the original `entrypoint.sh`, ensuring compatibility.
- **No Docker:** Simplifies dependencies and reduces runtime environment complexity.

## Credits

- Original project: [simple-elf/allure-report-action](https://github.com/simple-elf/allure-report-action).
- This version adapts the original project to use native Java and shell scripts directly in GitHub Actions.