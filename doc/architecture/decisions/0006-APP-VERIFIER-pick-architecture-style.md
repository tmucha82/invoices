# 6. APP-verifier-pick-architecture-style

Date: 2020-04-26

## Status

Proposed

## Context
This is supportive or even core domain (not decide yet).
We do not know what kind of verification would be here but it might be 
a lot of different and complex verifications here.

This is rather deep type of complexity (for sure not shallow).
We need to have different steps of verifications and every single step 
might be crucial for invoice processing.

The type of complexity is here located in different rules, that might be
configured. This module must provide high elasticity with configuration. 

The direction of potential changes would be related to providing new rules,
verifiers for checking invoices in different aspects. We do not know all of them
now, but they would appear rather quickly.

## Decision
This is perfect candidate for **pipe & filter architecture** style.

## Consequences
1. Elasticity: Extensibility & Configurability - it would be very easy to configure
new filter and added to stream of processing. Also would be very elastic to change
configuration of existing workflow.
2. Concurrency - would be possible to make verification process concurrent with the 
same workflow of filters.
3. Testability - very easy to testing all filter independently with different variants.
Also would be easy to test different configurations sets.
