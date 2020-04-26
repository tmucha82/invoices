# 4. APP API - Architecture style

Date: 2020-04-26

## Status

Proposed

## Context
This is supportive domain so we do not need to pay much attention to this.
domain. This is public API and frontend for all source of invoices.  

This type of problem is shallow and there is no special complexity with 
this module. 

The type of complexity is related to integration with different types of  
sources of invoices (e.g: mail, REST, etc.)

The direction of potential changes are mostly connected with adding an extra 
input for feeding invoices. It might be some changes in service layer, but
they would be probably not too complex. Moreover these type of changes as
we assume would be very rarely.    

## Decision
This module would be perfect for classic **3 layer architecture** style.

First layer would contains controller, mail and other source of invoices.
Service layer would be responsible for processing raw files to data storage
and also processing feedback from verifier loop. 

## Consequences
1. Easy and quickly to implement.
2. Easy to add new type of sources (integration)
3. Very well know architecture style - would be possible to delegated this
to junior and mid developer. 
4. Not much complex to test, the main accent to integration tests.