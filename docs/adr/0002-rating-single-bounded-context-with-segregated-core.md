# 0002. Rating is a single bounded context with a segregated core library

- **Status:** Accepted
- **Date:** 2026-11-25
- **Deciders:** Anthony DUSART

## Context

The Rating capability is implemented in two Maven modules:

- **`rating-core`**: the pricing engine (`RateBook`, `RateChart`, `Zone`, `Rate`, `PriceModifier`, `Shipment`, `PriceBreakdown`, …). It is framework-free, has no identities and no tenant concept, and is designed to be usable as a standalone library.
- **`rating`**: the service. It owns the `Tariff` aggregate (identity, tenant, version, domain events), the `TariffRepository` port, and will host the application and infrastructure layers.

`Tariff` holds a `RateBook` directly and delegates evaluation to it, without any translation. Both modules use the same vocabulary with the same meaning.

Both modules currently declare **the same Java packages** (`com.cleanship.rating.domain.model` and `com.cleanship.rating.domain.exception`), and `RatingApplication` sits at the root package `com.cleanship.rating`. This causes several problems:

- **Split packages:** `rating-core` can never become a JPMS module, which is a limitation for a standalone library.
- **Unenforceable rules:** package-based architecture tests (ArchUnit) cannot tell the two modules apart.
- **Implicit scanning:** Spring Boot component scanning and ArchUnit imports rooted at `com.cleanship.rating` also include the library's classes.
- **Poor readability:** contributors cannot tell which module a type comes from.

## Decision

1. **Rating is one bounded context split into two modules.** `rating-core` is the context's *Segregated Core* (Evans, *Distillation*): the heart of the model, isolated in its own module to protect it. It is **not** a separate bounded context.
2. **The modules use sibling namespaces under `com.cleanship.rating`:**

   | Module | Root package | Sub-packages |
   |---|---|---|
   | `rating-core` | `com.cleanship.rating.core` | `model.*`, `exception` |
   | `rating` | `com.cleanship.rating.service` | `domain`, `application`, `infrastructure` (and `RatingApplication`) |

   Neither namespace is a parent of the other. The shared `rating` segment expresses that both modules belong to the same bounded context.
3. **Dependencies flow one way:** `rating` depends on `rating-core`, never the reverse. This is enforced by the Maven build. `rating-core` must not depend on any framework.

## Alternatives considered

| Option | Pros | Cons |
|---|---|---|
| Keep the current shared packages | No migration | Split packages, unenforceable architecture rules, implicit scanning of the library |
| Rename only `rating-core` to `com.cleanship.rating.core` | Smaller change | The service stays at the root and still looks like the parent of the library; scanning from `com.cleanship.rating` still includes the core |
| Treat `rating-core` as a separate bounded context with its own namespace (e.g. `com.cleanship.pricing`) | Strong isolation | Contradicts reality: no translation exists between the modules, and the language is shared. It would require an anticorruption layer for no benefit |

## Consequences

### Positive

- Each type's origin is obvious from its package.
- Architecture rules can be expressed per module and per layer.
- `rating-core` can later ship a `module-info.java`.

### Negative

- Requires a one-time package rename and import update across both modules (`rating-core` and `rating`). While technically a breaking change relative to earlier `0.x` tags, the impact is low at this early pre-adoption stage.

### When to revisit

Consider splitting Rating into two bounded contexts if any of the following happens:

- the same term starts to mean different things in the two modules;
- the modules end up owned by different teams;
- the service starts translating core concepts instead of using them as they are.

## Enforcement

- Maven: `rating-core` declares no dependency on `rating` nor on any framework.
- ArchUnit (to be added): classes in `com.cleanship.rating.core..` must not depend on `com.cleanship.rating.service..`.

## References

- Eric Evans, *Domain-Driven Design: Tackling Complexity in the Heart of Software* (2003), Part IV, *Distillation* (Segregated Core).
