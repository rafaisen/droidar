## 2025-05-14 - [EfficientList Optimization]
**Learning:** In the custom `EfficientList` collection, many operations (contains, remove) were iterating over the full capacity (`myArray.length`) instead of the actual number of elements (`myLength`). This caused O(capacity) performance even for small lists that had previously been large. Additionally, manual loops for array shifting/resizing are significantly slower than `System.arraycopy`.

**Action:** Always ensure custom collection iterations are bounded by the logical size, not physical capacity. Use `System.arraycopy` for all array manipulation tasks.
