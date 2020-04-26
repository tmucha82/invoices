# 2. SYS - Pick documentation language

Date: 2019-09-13

## Status

Accepted

## Context

- We have polish client and polish development team, so all business related communication is in polish. 
- All team members prefer to write code in english.
- We want to be prepared for foreign acquisition in some distant future.
- We want to be open for foreign team members
- There is not much business facing documentation (BFD)

## Decision

- All BFD and artifacts will be in polish (ES sessions, System Context diagram, e.t.c)
- All developer facing documentation and artifacts will be in english (Code, ADR, C2-C3 diagrams, e.t.c)

## Consequences

- Positive
	- We will maintain low friction communication with domain experts (feedback will be possible)
	- Our code base will be standard and open for foreigners
- Negative 
	- We will have to translate polish domain names to english and maintain some sort of dictionary
	- In case of acquisition we will have to translate BFD