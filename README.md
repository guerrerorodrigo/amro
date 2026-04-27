# Amro Movie App

<p align="center">
  Trending movies app for Android.
</p>

---

## Features

- Get the 100 trending movies of the week
- Explore the trending movies details

---

## Screenshots

| Home | Film Detail |
|------|------------|
| <img src="./screenshots/main-screen-screenshot.png" alt="Home screen" width="280"/> | <img src="./screenshots/details-screen-screenshot.png" alt="Movie details screen" width="280"/> |

## Architecture Overview

The app follows a **multi-module** project structure. Each layer has a defined responsibility and communicates only with the layer immediately below it. The modules in the domain and data layers consist of two modules: API and Implementation. API modules define the public facing models and interfaces, while the implementation contains implementation details. Other modules should only import the API module.

```
┌─────────────────────────────────────────────────┐
│              UI  (feature/)                     │
│   Compose UI • ViewModel • MVI                  │
├─────────────────────────────────────────────────┤
│                 Domain  (domain/)               │
│   Interactors • Models                          │
├─────────────────────────────────────────────────┤
│                  Data  (data/)                  │
│   Repository • Data sources                     │
├─────────────────────────────────────────────────┤
```

**Patterns used:**

| Pattern | Usage |
|---|---|
| Layered Architecture | Layer separation (UI → Domain → Data) |
| Multi-module | Each feature or layer is a separate Gradle module, with domain and data having API and Impl modules |
| MVI | Custom MVI framework with State, Actions, Reducer and Middlewares |
| Repository | Can abstract multiple datasources for the same feature |
| Dependency Injection | Dependendy injection implemented with Hilt |
| Mapper | DTOs to Domain models to UI models |

## Module Structure

```
amro/
├── app/                    # Application entry point with root navigation
├── data/
│   ├── repository/
│       ├── movies          # Repository for the trending movies and movie details
│   ├── source/
│       ├── tmdb            # TMBD Api and impl data source
├── domain/
│   ├── common/             # Common classes, like formatters, URL builders and converters
│   ├── details/            # Business logic and interactor for movie details. Contains API and impl modules
│   └── home/               # Business logic and interactor for home feature. Contains API and impl modules
├── ui/
│   ├── common/             # Shared feature logic (ViewModels, etc.)
│       ├── mvi/            # Custom MVI framework
│       ├── theme/          # Compose theme and common composables
│   ├── feature/            
│       ├── details/        # UI for the details feature
│       └── home/           # UI for the home feature
```

## Tech Stack

| Category | Library / Tool |
|----------|---------------|
| Language | Kotlin |
| UI | Jetpack Compose with Material 3 |
| DI | Hilt |
| Navigation | Navigation 2 (Compose) |
| Networking | Ktor |
| Serialization | Kotlinx Serialization |
| Image loading | Coil |
| Code quality | Detekt |
| Screenshot testing | Compose Preview |
| Testing | Junit, Mockk, Turbine and Espresso |
| Dates | Kotlinx Datetime |
---

## Getting Started

### Setup

1. Clone the repository:

```bash
git clone https://github.com/guerrerorodrigo/amro.git
```

2. Add your TMDB API key.  
Create (or edit) `local.properties` in the project root:

```properties
TMDB_API_KEY=your_api_key_here
```

## API

This app uses the **[TMDB (The Movie Database) API](https://www.themoviedb.org/documentation/api)**
