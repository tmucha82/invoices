# 9. APP ARCHIVE - Architecture style

Date: 2020-04-26

## Status

Proposed

## Context
This is for sure supportive domain. 

This is rather shallow complexity here so no need to treat it very special.

The type complexity is related to persistence and archive daily documents
and provide some read model for forecasting domains. It means that 
this model would adapter - port implementation for relevant modules.    

The direction of changes here would be related to adding some feature
related to dev ops and high availability in time of populating data 
for forecasting.
## Decision
We propose **3 layer architecture** style for this module.

## Consequences
1. Very well know architecture style with easy to implement.
2. Dedicated for not complex domain like that.
3. Good separation of layers.
