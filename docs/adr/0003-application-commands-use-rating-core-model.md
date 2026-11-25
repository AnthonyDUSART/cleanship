# 0003. Application commands use the `rating-core` model

- **Status:** Accepted
- **Date:** 2026-11-25
- **Deciders:** @anthonizer

## Context

The `rating` service is about to get its application layer (`CreateTariffCommand`, `UpdateTariffCommand`, evaluation use cases, …). We must decide which types commands carry, and therefore which types primary adapters (REST, gRPC) must know.

Forces at play:

- **Vaughn Vernon** (*Implementing Domain-Driven Design*, chapter 14) recommends passing primitives to application services, so that primary adapters do not depend on domain types.
- **The external contract is already decoupled.** The API is designed contract-first with OpenAPI; generated DTOs live in the infrastructure layer and isolate external clients (the BFF for tariff management, merchant backends for checkout evaluation) from internal types.
- **The `rating-core` model is deep and polymorphic:** `RateBook` → `RateChart` → `Zone` → `Subdivision` / postal code specifications, sealed `Rate` and `PriceModifier` hierarchies. Mirroring it with primitive structures means duplicating that tree and mapping it twice.
- **`rating-core` is a framework-free library designed to be consumed from outside** (see [ADR-0002](0002-rating-single-bounded-context-with-segregated-core.md)). Its public API is meant to be used by callers.
- **Bulk imports (CSV, spreadsheets) are not a concern of this service.** The BFF turns them into intentions before calling Rating.
- **External contributions** to the API layer are expected.

## Decision

- Commands **may** carry types from `rating-core` (e.g. `RateBook`, `Shipment`).
- Commands **must not** carry types from the `rating` service's domain (`Tariff`, `TariffId`, `TenantId`, `Version`, domain events, repositories). Identifiers are passed as `UUID` and converted to domain identifiers inside the application layer.
- Primary adapters map their contract (OpenAPI DTOs, protobuf messages) **directly** to commands, and therefore to `rating-core` types. There is no intermediate mirror model in the application layer.

```java
public record CreateTariffCommand(UUID tariffId, UUID tenantId, RateBook rateBook) {}
```

This is a deliberate, localized deviation from Vernon's recommendation. The rest of the architecture (hexagonal layering, thin application services, aggregates, repositories) follows Evans and Vernon.

## Alternatives considered

| Option | Pros | Cons |
|---|---|---|
| **A. Primitives only** (Vernon) | Primary adapters know nothing of the model; a single stable application contract for several adapters | Duplicates the polymorphic `rating-core` tree; two mappings per adapter (contract → mirror → model), so more room for silent mapping bugs; three places to change for every new `Rate` or `PriceModifier` |
| **B. `rating-core` types + `UUID` identifiers** (chosen) | One mapping per adapter; commands are valid by construction; the service's aggregate, identities and events stay private | Adapters depend on `rating-core`; contributors to adapters must learn the library's API |
| **C. All domain types, including `TariffId` / `TenantId`** | Fewest conversions | Leaks the service's domain into adapters; couples adapters to the aggregate's evolution |

With two adapters (REST and gRPC), option B needs two mappers, whereas option A needs three (contract → mirror twice, mirror → model once).

## Consequences

### Positive

- A single translation step per adapter, which reduces the surface for mapping bugs in a critical system.
- Adding a new `Rate` or `PriceModifier` touches the contract and `rating-core` only.
- The `Tariff` aggregate and its identities remain an internal detail of the service.

### Negative

- Adapters, not the application layer, construct `rating-core` value objects. Domain exceptions raised during construction (e.g. `OverlappingRateChartException`) must be handled at the adapter boundary.
- Contributors working on adapters must understand the `rating-core` API.

### When to revisit

Supersede this ADR and move to option A if any of the following happens:

- a new kind of primary adapter must call the application layer without knowing `rating-core`;
- contributors repeatedly struggle with mapping to `rating-core` in adapters;
- `rating-core` and the service start to diverge (see the signals in ADR-0002).

## Enforcement

ArchUnit (to be added):

- classes in `com.cleanship.rating.service.infrastructure..` must not depend on `com.cleanship.rating.service.domain..`;
- command types in `com.cleanship.rating.service.application..` must not depend on `com.cleanship.rating.service.domain..`.

## Follow-ups (out of scope)

- **Validation error reporting.** Structural errors are reported by contract validation. For semantic errors raised by `rating-core`, choose between path-aware fail-fast errors in adapters (e.g. `rateCharts[2].rates[5]`) and error accumulation provided by `rating-core` itself. This needs its own ADR.
- **Machine-readable error codes.** `DomainException` currently carries only a message. A public API needs stable error codes.

## References

- Eric Evans, *Domain-Driven Design: Tackling Complexity in the Heart of Software* (2003).
- Vaughn Vernon, *Implementing Domain-Driven Design* (2013), chapter 14, *Application*.
- [ADR-0002](0002-rating-single-bounded-context-with-segregated-core.md): Rating is a single bounded context with a segregated core library.
