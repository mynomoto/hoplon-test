# Hoplon with tests
This project presents a minimal setup for Hoplon using boot2.

## Dependencies
- Java Development Kit (JDK) version 1.7 or greater (1.8 is better)
- [boot][boot]

## Usage
### Setup
1. Install `npm`.

2. In a terminal run:
    ```bash
    $ npm install
    ```
### Development
1. Start the `dev` task. In a terminal run:
    ```bash
    $ boot dev
    ```
    This will give you a  Hoplon development setup with:
    - auto compilation on file changes
    - audible warning for compilation success or failures
    - auto reload the html page on changes

2. Open the generated file on `resources/public/index.html` on your browser.

3. To run tests in another terminal run to autocompile a test build:
    ```bash
    $ boot dev-test
    ```

    Then in still another terminal run:
    ```bash
    $ karma start
    ```
    This will start a chrome browser where the tests will run.


4. If you edit and save a file, the task will recompile the code and reload the
   browser to show the updated version, and the test build will also be recompiled
   and the tests will run.

### Production
1. Run the `prod` task. In a terminal run:
    ```bash
    $ boot prod
    ```

2. The compiled files will be on the `resources/public/` directory. This will use
   advanced compilation and prerender the html.

[boot]: https://github.com/boot-clj/boot#install
