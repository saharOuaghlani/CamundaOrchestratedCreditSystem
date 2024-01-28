## Structure de projet
Notre projet repose sur une structure ingénieuse composée de quatre
microservices distincts, orchestrés de manière fluide sous la supervision de
Camunda par le biais d'API. Les microservices “Amazon Credit” et “Bank Credit”
coordonnent respectivement les tests et déductions de crédit, ainsi que les tests
et déductions de solde bancaire. Le "Get Conversion Rate Microservice" excelle
dans la récupération des taux de change actuels via une API externe, tandis
que le “Convert Amount Microservice”, également orchestré par Camunda,
utilise ces taux pour effectuer des conversions précises entre devises. Cette
architecture, clairement segmentée et habilement orchestrée par Camunda,
assure une maintenance optimale et une évolutivité accrue du système, avec
une coordination sans appel direct entre les microservices.
[rapport.pdf](https://github.com/saharOuaghlani/CamundaOrchestratedCreditSystem/files/14077962/rapport.pdf)
