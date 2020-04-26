# 7. APP VAT-TAX FORECAST - Architecture style

Date: 2020-04-26

## Status

Proposed

## Context
This is core domain. That is why we need to have such style
which allows us experimentation with this domain

This is rather deep type of complexity and also we need to focus
on the fact that it might be changes a lot since business needs
to add new features here.

The type of complexity is connected here with forecasting of tax.
There would be a lot of experimentation here. Also we would like
to have elasticity with change I/O at the end of hexagon. 

The direction here would be focused on changing algorithm using 
for prediction here. It might be also AI here, or complex math models.

## Decision
We propose **hexagonal architecture** style for this module.

## Consequences
1. Testability - we could easily add new tests, checking different
variants of algorithms for predictions. 
2. Manageability and Extensibility - it would be easy to change 
and extend algorithm of forecasting of taxes.
3. Easy to change I/O - there is easy to change I/O at the end of hexagon.   
