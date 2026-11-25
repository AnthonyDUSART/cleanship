# 0001. Record architecture decisions

- **Status:** Accepted
- **Date:** 2026-11-25
- **Deciders:** Anthony DUSART

## Context

CleanShip is an open-source shipping rate engine built with Domain-Driven Design and a hexagonal architecture. Several of its design choices are deliberate trade-offs between well-known approaches (Eric Evans, Vaughn Vernon, contract-first APIs).

Without a written record, contributors have to guess the reasoning behind these choices from the code alone. They may "fix" a deliberate trade-off, or, conversely, keep a decision alive long after the context that justified it has disappeared.

## Decision

- We record significant architectural decisions as **Architecture Decision Records**, using the lightweight format described by Michael Nygard.
- ADRs live in [`docs/adr`](.), are written in English, and are numbered sequentially (`NNNN-short-title.md`).
- ADRs are proposed through pull requests. Merging the pull request accepts the ADR.
- Accepted ADRs are immutable. A decision is changed by writing a new ADR that supersedes the previous one.

## Consequences

### Positive

- Contributors can understand *why* the architecture looks the way it does, including its deliberate deviations from the literature.
- Each decision states the conditions under which it should be revisited, so it can evolve without being silently eroded.

### Negative

- Writing an ADR adds a small amount of work to significant changes.

## References

- Michael Nygard, [*Documenting Architecture Decisions*](https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions) (2011).
