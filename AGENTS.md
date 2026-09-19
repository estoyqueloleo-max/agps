# Refactoring Guidelines

## Variable Renaming
Whenever you are modifying or reading code and you notice "decompiled" variables or variables with generic English names (e.g., single-letter variables, `param1`, `var2`, `str`, `context`, etc.) in the scope you are working on, you **must** proactively rename them to descriptive names in English that reflect their actual purpose in the domain logic.

This helps gradually clean up the codebase from artifacts left over by the decompilation process.

**Rules for Renaming:**
1. Only rename variables in the scope you are actively working on to avoid massive unrelated diffs.
2. Ensure the new name accurately reflects the variable's role.
3. Use `camelCase` for variable names.
4. Keep the code compiling; if the variable is used in other methods or classes, make sure to use your tools to replace all occurrences if needed, or stick to local variables if global changes are too risky without further analysis.

## Automated Testing (Unit & Visual)
Whenever you are working on, modifying, or fixing features and bug areas in the codebase, you **must** proactively add automated tests to incrementally increase test coverage and prevent regressions:

1. **Unit Tests**:
   - Add tests covering domain logic, parsers, calculations, model state, and algorithms.
   - Use real-world sample data when available (e.g. sample GPX files in test resources like `rivas_track.gpx`).
   - Keep tests deterministic, fast, and runnable with `./gradlew testDebugUnitTest`.

2. **Visual & UI Tests**:
   - For UI components, custom views (e.g., `GraphView`, balloon overlays, buttons, layouts), validate layout inflation, view state transitions, and canvas drawing integrity.
   - Use Robolectric / Android test runners to ensure UI components don't throw exceptions, produce `NaN`/infinity bounds, or lock threads during drawing.

