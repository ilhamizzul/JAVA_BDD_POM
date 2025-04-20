# JAVA_POM_SELENIUM

A Java-based test automation framework utilizing the **Page Object Model (POM)** design pattern for organized and maintainable UI tests. This framework is powered by **TestNG**, **Selenium WebDriver**, and follows a modular approach for scalable testing.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Tests](#running-the-tests)
- [Example Test Scenarios](#example-test-scenarios)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This testing framework is designed for automating web applications, functioning as a reusable and structured test suite with:
- **BDD Style Scenarios**: Test actions and verifications are combined into clear, descriptive test cases.
- **Page Object Implementation**: Centralized page methods for UI interactions, reducing test redundancy and ensuring high reusability.
- **Test Automation for E-Commerce Flow**: Built to validate login functionality, product interactions, and inventory management.

---

## Features

- **Automated Login Tests**:
  - Navigate to the login page and perform user authentication with valid credentials.
- **Inventory Functionality**:
  - Add or remove items from the cart dynamically and validate button states.
- **Product Page Operations**:
  - Verify product details such as name and price.
  - Toggle cart state (e.g., add/remove products in detail view).
- **TestNG for Validation**:
  - Test assertions to ensure UI components behave as expected.
  - Configured preconditions and reusable base methods.

---

## Technologies Used

- **Java**: Programming language.
- **Selenium WebDriver**: Browser automation.
- **TestNG**: Test framework for execution and assertions.
- **Gradle**: Build and dependency management tool.
- **Page Object Model (POM)**: Design pattern to separate test logic and UI interactions.

---

## Project Structure

```plaintext
JAVA_POM_SELENIUM/
├── src/test/java/
│   ├── pages/                  # Page Object files
│   │   ├── LoginPage.java      # Methods for login-related operations
│   │   ├── InventoryPage.java  # Methods to operate on inventory items
│   │   ├── MainPage.java       # (Optional) Main/home page interactions
│   │   ├── ProductPage.java    # Methods to validate product details
│   │
│   ├── tests/                  # Test cases and scenarios
│   │   ├── TestBase.java       # Base test configuration (setup/teardown)
│   │   ├── TS01_Login.java     # Test scenarios for Login functionality
│   │   ├── TS02_Inventory.java # Test scenarios for inventory and product operations
│   │
│   ├── config/
│       ├── WebDriverConfig.java # WebDriver setup (browsers and configs)
│
├── build.gradle                # Gradle build configuration with dependencies
```

---

## Installation

### Prerequisites

Before you clone the project, ensure the following are installed and configured on your local machine:

1. **Java Development Kit (JDK)** (Version 8 or higher)
2. **Gradle** (for dependency and build management)
3. **Browser Drivers**:
   - Download the appropriate WebDriver (e.g., ChromeDriver for Chrome, GeckoDriver for Firefox).
   - Ensure the WebDriver executable is present in your `PATH`.

### Steps to Set Up

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd JAVA_POM_SELENIUM
   ```

2. Install project dependencies:

   ```bash
   gradle clean build
   ```

---

## Running the Tests

### Execute Tests using TestNG

1. Open the project in **IntelliJ IDEA**.
2. Right-click a test class (e.g., `TS02_Inventory.java`) or individual test cases.
3. Select **Run** to initiate execution.

### Execute Using Gradle

Run all test cases using the following command:

```bash
gradle test
```

### Test Results

Upon completion, test results will be available in:

```plaintext
build/reports/tests/test/index.html
```

Open this HTML file in a browser to review test execution insights.

---

## Example Test Scenarios

Below are some key test cases implemented in the project:

1. **TS0201_AddItemToCart**
   - Log in with a valid user.
   - Add a product to the cart via the inventory page.
   - Validate the button state changes to "Remove".

   **Method**:
   ```java
   @Test
   public void TS0201_AddItemToCart() {
       InventoryPage inventoryPage = LoginPrecondition();
       int index = inventoryPage.clickButtonAddToCart();
       Assert.assertTrue(inventoryPage.verifyIsButtonItemInRemoveState(index));
   }
   ```

2. **TS0202_RemoveItemFromCart**
   - Log in as a user.
   - Add multiple items to the cart and remove one.
   - Assert the button returns to the "Add to Cart" state.

3. **TS0203_SeeProductDetail**
   - Add a product on the inventory page.
   - Navigate to the product details page.
   - Assert the product name and price match those on the inventory list.

4. **TS0204_ToggleButtonAddToCartInsideDetailProduct**
   - Access the product detail page.
   - Perform actions like toggling "Add to Cart" and verify state consistency.

---

## Contributing

We welcome contributions to enhance this framework! To contribute:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/new-feature
   ```
3. Commit your changes.
4. Push the branch:
   ```bash
   git push origin feature/new-feature
   ```
5. Submit a pull request with a detailed explanation.

---

## License

This project is licensed under the [MIT License](LICENSE).

Feel free to clone, modify, and redistribute under the terms of the license.

---

**Happy Testing!** 🎉
