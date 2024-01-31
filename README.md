
# Stub

## Overview

TBD

## Repository Structure

The repository is organized to facilitate easy access and integration of stub files into your projects. The structure is as follows:

```
stubs/
    |── service1/
    │    └── service1_stub/
    |
    └── service2/
         └── service2_stub/
```

- `service1/`, `service2/`: Directories containing stub files specific to a service. These are generated from the `.proto` files in the corresponding Proto repository and are ready for integration into service-specific implementations.

## How to Use

These stub files are designed for straightforward integration into your projects. You can clone this repository to your local environment or directly reference the stub files remotely if your build system supports such configurations.

### Cloning the Repository

To clone the repository, execute:

```bash
git clone https://github.com/apexnova-vc/stub.git
```

This will create a local copy of the stub repository, allowing you to explore and integrate the stub files as needed.

### Using Remote Stub Files

If your build system allows, you can directly reference the stub files in this repository. Here's how you might declare the dependency in a Gradle project:

```groovy
dependencies {
    implementation 'com.apexnova:stubgen:<version>'
}
```

Be sure to replace `<version>` with the actual version number of the stub files you intend to use.

## Generating Stub Files

The stub files in this repository are generated from the `.proto` files in the ApexNova Protocol Buffer Definitions repository. Here's a general outline of the process:

1. Clone the Proto repository.
2. Use the Protocol Buffer compiler (`protoc`) to generate stub files in your preferred language.
3. Integrate the generated stub files into the StubGen repository structure.

Example for generating Java stub files:

```bash
protoc --java_out=java/ --proto_path=proto/ proto/*.proto
```

## License

The stub files and the accompanying documentation in this repository are under the MIT License. The [LICENSE](LICENSE) file contains the full terms.
