# Pokédex

**Pokédex** is a Kotlin Multiplatform project built with **Compose multiplatform**.

The project focuses on code sharing and modern development practices, Pokédex is a cross-platform
application leveraging the power of Kotlin Multiplatform. It offers a seamless user experience
across different platforms while maintaining a clean, maintainable codebase.

## Key Features:

- **Kotlin Multiplatform:** Maximum code reusability across platforms.
- **Compose Multiplatform:** Modern UI toolkit for building native-like experiences.
- **Coroutines and Flow:** Asynchronous programming for efficient data handling.
- **Voyager:** Declarative navigation solution.
- **Koin:** Dependency injection for streamlined management.
- **Ktor:** Networking for reliable data fetching.
- **SqlDelight:** Type-safe SQL database for offline data storage.
- **Material 3:** Consistent and modern UI design.
- **Paging 3:** Efficient handling of large datasets with pagination.
- **Multi-Module Architecture:** Clean separation of concerns and improved testability.
- **MVVM+ Pattern:** Architecture for maintainable and scalable codebase.

## Architecture

The project adopts a **multi-module** architecture based on **Clean Architecture** principles. This
approach
promotes modularity, testability, and maintainability.

<div align="center">
  <img src="https://github.com/user-attachments/assets/4d14351d-41e2-485a-82d3-e2d4fcd5d61d" alt="Image of Gradle Module Dependency Graph" width="70%" />
</div>

### Data Layer:

- **Database:** Handles local data storage using SqlDelight.
- **Network:** Manages network requests and responses using Ktor.
- **Data:** Aggregates data from local and remote sources.

### Domain Layer:

- **Domain:** Encapsulates business logic and domain entities.

### Presentation Layer:

- **Shared:** Contains platform-agnostic UI components and business logic.
- **Android/Desktop:** Platform-specific implementations and integrations.

**Note:** The shared module is structured using the API/Impl pattern to minimize dependencies and
promote modularity. This design allows for seamless extraction of the shared module into separate
feature modules if needed.

<br>

## Screenshots

### Android

<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/41c67f54-a591-41f2-ba4e-493e6db4cd60" alt="Home Screen (Light mode)" width="45%">
  <img style="float: left; margin-left: 15px;" src="https://github.com/user-attachments/assets/1c84b5f3-bf75-4d2f-b944-700ab5a71d08" alt="Home Screen (Dark mode)" width="45%">
</div>
<br>
<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/0b5026bf-7b5b-453d-994f-c9b71338e2bb" alt="Home Screen (Light mode" width="45%">
  <img style="float: left; margin-left: 15px;" src="https://github.com/user-attachments/assets/c7f9a473-ee58-425d-9403-7eefb1f6389d" alt="Pokemon Details Screen (Bulbasaur)" width="45%">
</div>
<br>
<div align="center">
  <img style="float: left; margin-right: 15px;" src="https://github.com/user-attachments/assets/6f7276af-3bc9-4319-8f15-4c36d7e506b5" alt="Pokemon Details Screen (Charizard)" width="45%">
</div>

### Desktop

<div align="center">
  <img style="float: left;" src="https://github.com/user-attachments/assets/ba7f9993-eb73-4b92-8038-c12928c73c8f" alt="Home Screen (Light mode)" width="49.5%">
  <img style="float: left;" src="https://github.com/user-attachments/assets/d6847f8a-d974-4c46-9293-9262adf7a800" alt="Home Screen (Dark mode)" width="49.5%">
</div>
<br>
<div align="center">
  <img style="float: left;" src="https://github.com/user-attachments/assets/e82e964c-b0ed-4e1c-8a0e-2811b8e59555" alt="Pokemons Screen" width="80%">
</div>
<br>
<div align="center">
  <img style="float: left;" src="https://github.com/user-attachments/assets/a86be873-eff4-4f6d-83c7-021462a41248" alt="Pokemon Details Screen (Ivysuar)" width="49.5%">
  <img style="float: left;" src="https://github.com/user-attachments/assets/f2f03c91-18fd-4fa1-8845-c83f05bf09a4" alt="Pokemon Details Screen (Charizard)" width="49.5%"> 
</div>

## Application design

Adapted
from [Pokédex Apps design](https://dribbble.com/shots/17332968-Pok-dex-Apps-Design-Exploration)
by [Nur Asmara](https://dribbble.com/nurasmara/).

## Platform support

Currently, the application is functional on Android and desktop platforms. The project is
architected with future iOS compatibility in mind. Thanks to extensive code sharing (99%) and a
modular structure using the Kotlin Multiplatform, the iOS implementation can be easily integrated
once
the necessary development environment is available.
