## 2025-05-14 - Accessibility in Dynamic Component Frameworks
**Learning:** In frameworks where UI components are generated dynamically (like SimpleUI), it's crucial to pass descriptive labels (e.g., `getVarName()`) to accessibility attributes like `contentDescription` for icon-only buttons. Generic labels are less helpful than context-aware ones.
**Action:** Always check if dynamic components that use icons have access to a name or label that can be used for `contentDescription`.

## 2025-05-14 - Numeric Input Flexibility
**Learning:** When defining numeric input types in Android (`InputType`), omitting `TYPE_NUMBER_FLAG_SIGNED` can lead to poor UX where users are unable to enter negative values on many soft keyboards, even if the underlying data type (like `double`) supports it.
**Action:** Ensure `TYPE_NUMBER_FLAG_SIGNED` is included for numeric inputs unless negative values are explicitly disallowed.
