# 3. SYS - Use modular monolith

Date: 2020-04-25

## Status

Accepted

## Context

- Constraints
	- Greenfield project
	- Team of 2 developers
	- 6 months to first release
- Quality attributes
	- Expected load: 50-150 request/second
	- SLA 99,00 

## Decision

We will not separate components in to separate deployment units, 
we will use modular monolith approach with single database. 

## Consequences

- Positive
	- fast reliable and secure communication between components 
	- transactions
	- simple infrastructure
	- simple development and deployment 
	- possibility to extract separate services in future if needed
- Negative
	- We will have to scale the whole thing when it will be needed 
	- Video validation may consume lots of resources and have impact on rest of the application
	- We have to enforce component boundaries on our own 
