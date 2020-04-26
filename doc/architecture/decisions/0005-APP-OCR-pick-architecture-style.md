# 5. APP OCR - Architecture style

Date: 2020-04-26

## Status

Proposed

## Context
This is for sure our generic domain. Basically we want to use some product
that are already on the market. However we assume that there are many OCRs
in market and we want to register more than one and have some defined process
for deciding about result of parsing/OCR.

This type of problem is not shallow. It might be if provided OCR would be 
perfect one. However, we would like to have multiple OCR and some engine 
with some heuristic to decide what final result would be at the end.

The complexity is placed here in decided based on multiple OCR-s, plus
some engine to decide based on given OCR-s and their results. We want
also have elasticity in check different OCR's and different rule engines
to make perfect result. 

The direction of potential changes would be related to slowly adapting 
small changes about performance and perfection of OCR process. Also we
want to have chance to register new OCR's and decision engines to make 
our OCR perfect. 

## Decision
Because we want to have basic process that i well defined, but with different
types of OCR's and rule engines we decide to propose **micro kernel architecture**
style. 
Also we want to experiment with this style.

## Consequences
1. Testability - we can easily test all OCR's we would like to have
in case of different types f invoices and compering result of such tests.
2. Extensibility & Configurability - we would have easily added new OCR,
change rule engines, adn their behaviour to make the result perfect one.
