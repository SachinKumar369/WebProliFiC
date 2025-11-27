# WebProLiFiC 3.0

![Logo Placeholder](https://via.placeholder.com/800x200/007ACC/FFFFFF?text=WebProLiFiC+3.0) <!-- Yahan apna logo add kar de agar hai -->

WebProLiFiC is a robust web automation framework built with **Java, Selenium, TestNG, and Maven** to supercharge interactions with **Web Prol'IFIC** – the premier hospitality ERP solution from Prologic First. Designed for hotels, restaurants, catering services, and multi-property chains, this tool automates back-office tasks like accounting, inventory management, purchasing, and food & beverage costing. Say goodbye to manual data entry and hello to seamless efficiency!

Whether you're managing a single hotel or a global chain, WebProLiFiC streamlines workflows, ensures compliance, and optimizes costs – all while integrating with Web Prol'IFIC's multi-currency and mobile-ready features.

## 🚀 Features

- **Automated Purchase & Inventory Management**: Generate requisitions, track POs, handle receipts, and update stock levels automatically.
- **Costing & Reporting Automation**: Run F&B costing reports, multi-currency reconciliations, and analytics exports to CSV/Excel in one click.
- **Back-Office Accounting Sync**: Auto-post journals, reconcile vendors/banks, and manage petty cash without silos.
- **Multi-Property Consolidation**: Centralize data from multiple sites for chain-wide insights and approvals.
- **Headless & Mobile Workflow Support**: Run scripts in the background or via mobile for on-the-go approvals and alerts.
- **Error-Resilient with AI Insights**: Built-in retries, logging, and anomaly detection (e.g., unusual purchase rates) for reliable ops.
- **Web Prol'IFIC API Integration**: Direct hooks for authentication, submissions, and real-time data pulls.
- **Custom Workflows**: Modular test suites for specific tasks like vendor invoice matching or inter-unit transfers.

## 🛠️ Tech Stack

| Category       | Technologies                          |
|----------------|---------------------------------------|
| **Automation** | Selenium WebDriver 4.x / TestNG 7.x   |
| **Language**   | Java 17+ (JDK)                        |
| **Build Tool** | Maven 3.9+ (for dependencies & builds)|
| **Data Handling** | Apache POI (Excel/CSV), Jackson (JSON)|
| **Testing**    | TestNG (parallel execution, data providers) |
| **CI/CD**      | GitHub Actions / Jenkins              |
| **Environment**| Docker (optional for containerized runs) |

## 📦 Quick Start

### Prerequisites
- Java 17+ (JDK installed)
- Maven 3.9+ (for building and running tests)
- Active Web Prol'IFIC account (login at [prologicfirst.com](https://www.prologicfirst.com))
- Chrome/Firefox browser + corresponding WebDriver (managed via WebDriverManager)
- IDE: IntelliJ IDEA or Eclipse (recommended for Maven integration)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/SachinKumar369/WebProliFiC.git
   cd WebProliFiC