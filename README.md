# StarterProject
Kotlin Starter Project

## Tools & Architecture
  - Kotlin DSL
  - buildSrc directory to manage dependencies
  - Multi Modular:
        - All business logic (Base classes, ViewModel, Interactors, Repositories, Services, Local Storage) goes in core
        - View (Fragment, Activities) related logic goes in app
