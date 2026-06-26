## 2026-06-25 - [WebView Security Hardening]
**Vulnerability:** Remote Code Execution (RCE) via JavaScript Bridge and unauthorized file access.
**Learning:** Legacy Android code often misses the @JavascriptInterface annotation, which is required since API 17 to prevent JS from accessing all public methods of an injected Java object. Additionally, WebViews by default may allow file access, which can be exploited for data exfiltration.
**Prevention:** Always use @JavascriptInterface for any method called from JS. Explicitly disable file, content, and cross-origin file access in WebView settings unless strictly required.
