# Users App — MVVM + Retrofit + Coroutines (Jetpack Compose)

A simple Android app that fetches users from [JSONPlaceholder API](https://jsonplaceholder.typicode.com/users) and displays them in a list using **Jetpack Compose**. It demonstrates **MVVM architecture**, **Retrofit**, and **Kotlin Coroutines**.

---

## Features

* Fetches user data from `/users` endpoint.
* Displays user `name` and `email` in a `LazyColumn`.
* **Loading indicator** while fetching data.
* **Error handling** with retry button.
* Clean **MVVM architecture**.
* Written in **Kotlin** and **Jetpack Compose**.

---

## Architecture Overview

```
com.irwan.application
│
├── data
│   └── UserRepository.kt          // Handles data fetching logic
│
├── domain
│   └── User.kt                    // Domain model
│
├── network
│   ├── Retrofit.kt                // Retrofit instance configuration
│   └── UsersApi.kt                // Retrofit API service
│
├── presentation
│   ├── UsersScreen.kt             // UI built with Jetpack Compose
│   └── UsersViewModel.kt          // ViewModel with StateFlow
│
├── utils
│   └── UsersUiState.kt            // UI state (Loading/Success/Error)
│
└── MainActivity.kt                // Entry point
```

---

## Tech Stack

* **Kotlin**
* **Jetpack Compose** (UI)
* **Retrofit** for networking
* **OkHttp** for HTTP client
* **Kotlin Coroutines** for async tasks
* **Lifecycle ViewModel** & `StateFlow`
* **Material3** for styling

---

## Data Model

```kotlin
data class User(
    val id: Int,
    val name: String,
    val email: String
)
```

---

## UI State

```kotlin
sealed interface UsersUiState {
    object Loading : UsersUiState
    data class Success(val users: List<User>) : UsersUiState
    data class Error(val message: String) : UsersUiState
}
```

---

## How It Works

1. `MainActivity` hosts `UsersScreen` and provides `UsersViewModel`.
2. `UsersViewModel` fetches users from `UserRepository`.
3. `UserRepository` calls `UsersApi.getUsers()` via Retrofit.
4. UI observes `UsersUiState` (Loading → Success/Error).

**Data flow:**

```
UsersScreen → UsersViewModel.loadUsers() → UserRepository.fetchUsers() → UsersApi.getUsers()
             ↑                                                        ↓
          render(state) ← StateFlow: Loading / Success / Error ← Gson(List<User>)
```

---

## Screenshots

*(Add screenshots of loading, error, and success states here)*

---

## Setup

1. Clone this repository.
2. Open in **Android Studio**.
3. Sync Gradle.
4. Run on an emulator or device with internet access.

---

## Recommended Versions

| Library             | Version    |
| ------------------- | ---------- |
| Kotlin              | 2.0.x      |
| Retrofit            | 2.11.0     |
| Gson Converter      | 2.11.0     |
| OkHttp              | 4.12.0     |
| Coroutines          | 1.9.0      |
| Lifecycle ViewModel | 2.8.4      |
| Compose BOM         | 2024.09.01 |

---

## Future Improvements

* Implement **Hilt** for DI.
* Add offline caching (Room/Store).
* Pull-to-refresh and shimmer loading.
* Better error handling (timeout, no internet, HTTP errors).
* UI polish & theming.
