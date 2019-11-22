<img src="https://s3.amazonaws.com/gupy5/production/companies/442/career/448/images/logo.png" align="right" height="150"/>

# Teste de Back-end Bcredi

Na Bcredi utilizamos sistemas de mensageria para integrar nossos microsserviços. Nesse desafio, você vai resolver um problema real com a linguagem que você escolher. 

Você receberá uma lista de eventos com dados de propostas de empréstimo, garantias de imóvel e proponentes. Com base em regras de validação, você precisar retornar quais propostas são válidas após processar todos os eventos.

Uma proposta é o modelo que contém as informações de um empréstimo. Cada proposta deve conter múltiplos imóveis de garantia. A proposta também deve conter múltiplos proponentes, que são as pessoas envolvidas no contrato de empréstimo.

Para começar o desafio:

1. Faça um fork deste repositório
2. Clone este projeto
3. Escolha sua linguagem de preferência e edite o arquivo respectivo. Por exemplo, se você quer resolver em Ruby, o arquivo é `./ruby/solution.rb`; e se for Java `./java/solution.java`
4. Para testar sua solução, utilize os inputs e outputs definidos em `./test/input/*.txt` e `./test/output/*.txt`

## Regras

*   O valor do empréstimo deve estar entre R$ 30.000,00 e R$ 3.000.000,00
*   O empréstimo deve ser pago em no mínimo 2 anos e no máximo 15 anos
*   Deve haver no mínimo 2 proponentes por proposta
*   Deve haver exatamente 1 proponente principal por proposta
*   Todos os proponentes devem ser maiores de 18 anos
*   Dever haver no mínimo 1 garantia de imóvel por proposta
*   O valor das garantias deve ser maior ou igual ao dobro do valor do empréstimo
*   As garantias de imóvel dos estados PR, SC e RS não são aceitas
*   A renda do proponente principal deve ser pelo menos:
    *   4 vezes o valor da parcela do empréstimo, se a idade dele for entre 18 e 24 anos
    *   3 vezes o valor da parcela do empréstimo, se a idade dele for entre 24 e 50 anos
    *   2 vezes o valor da parcela do empréstimo, se a idade dele for acima de 50 anos
*   Em caso de eventos repetidos, considere o primeiro evento
    *   Por exemplo, ao receber o evento ID 1 e novamente o mesmo evento, descarte o segundo evento
*   Em caso de eventos atrasados, considere sempre o evento mais novo
    *   Por exemplo, ao receber dois eventos de atualização com IDs diferentes, porém o último evento tem um timestamp mais antigo do que o primeiro, descarte o evento mais antigo

## Formato de input 

A primeira linha contém o número de eventos a serem processados. Da segunda em diante, os dados do evento separados por vírgula.

Cada evento tem seu formato. Veja abaixo:

* **proposal.created**: enviado quando uma proposta é criada  
`event_id,event_schema,event_action,event_timestamp,proposal_id,proposal_loan_value,proposal_number_of_monthly_installments`

* **proposal.updated**: enviado quando uma proposta é atualizada  
`event_id,event_schema,event_action,event_timestamp,proposal_id,proposal_loan_value,proposal_number_of_monthly_installments`

* **proposal.deleted**: enviado quando uma proposta é excluída  
`event_id,event_schema,event_action,event_timestamp,proposal_id`

* **warranty.added**: enviado quando um imóvel de garantia é adicionado à uma proposta  
`event_id,event_schema,event_action,event_timestamp,proposal_id,warranty_id,warranty_value,warranty_province`

* **warranty.updated**: enviado quando um imóvel de garantia é atualizado  
`event_id,event_schema,event_action,event_timestamp,proposal_id,warranty_id,warranty_value,warranty_province`

* **warranty.removed**: enviado quando um imóvel de garantia é removido de uma proposta  
`event_id,event_schema,event_action,event_timestamp,proposal_id,warranty_id`

* **proponent.added**: enviado quando um proponente é adicionado à uma proposta  
`event_id,event_schema,event_action,event_timestamp,proposal_id,proponent_id,proponent_name,proponent_age,proponent_monthly_income,proponent_is_main`

* **proponent.updated**: enviado quando um proponente é atualizado  
`event_id,event_schema,event_action,event_timestamp,proposal_id,proponent_id,proponent_name,proponent_age,proponent_monthly_income,proponent_is_main`

* **proponent.removed**: enviado quando um proponente é removido de uma proposta  
` event_id,event_schema,event_action,event_timestamp,proposal_id,proponent_id`

## Casos de exemplo

### Exemplo 1

Input do exemplo

```
10
c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proposal,created,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,684397.0,72
27179730-5a3a-464d-8f1e-a742d00b3dd3,warranty,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,6b5fc3f9-bb6b-4145-9891-c7e71aa87ca2,1967835.53,ES
716de46f-9cc0-40be-b665-b0d47841db4c,warranty,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,1750dfe8-fac7-4913-b946-ab538dce0977,1608429.56,GO
05588a09-3268-464f-8bc8-03974303332a,proponent,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,5f9b96d2-b8db-48a8-a28b-f7ac9b3c8108,Kip Beer,50,73300.95,true
0fe9465f-af17-452c-9abe-fa64d475d8ad,proponent,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,fc1a95db-5468-4a37-9a49-9b15b9e250e6,Dong McDermott,50,67287.16,false
814695b6-f44e-491b-9921-af806f5bb25c,proposal,created,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,2908382.0,108
cc08d0d2-e519-495f-b7d6-db6391c21958,warranty,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,37113e50-26ae-48d2-aaf4-4cda8fa76c79,6040545.22,BA
f72d0829-beac-45bb-b235-7fa16b117c43,warranty,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,8ade6e09-cb60-4a97-abbb-b73bf4bd8f76,6688872.79,DF
5d9e1ec6-9304-40a1-947f-ab5ea993d100,proponent,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,2213ea91-4a3c-46a3-b3a7-ff55c2888561,Kathline Ferry,50,168896.38,true
23060b08-32bf-4e53-9866-69f6bcc7fdbd,proponent,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,7526214a-cd5b-4e49-a723-e031bc82dcef,Merle Leuschke,50,143081.9,false
```

Output do exemplo

```
c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,814695b6-f44e-491b-9921-af806f5bb25c
```

Explicação: como os dados das duas propostas eram válidos, o output contém as duas propostas.

### Exemplo 2

Input do exemplo:

```
8
c2d06c4f-e1dc-4b2a-af61-ba15bc6d8610,proposal,created,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,684397.0,72
27179730-5a3a-464d-8f1e-a742d00b3dd3,warranty,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,6b5fc3f9-bb6b-4145-9891-c7e71aa87ca2,1967835.53,ES
716de46f-9cc0-40be-b665-b0d47841db4c,warranty,added,2019-11-11T13:26:04Z,bd6abe95-7c44-41a4-92d0-edf4978c9f4e,1750dfe8-fac7-4913-b946-ab538dce0977,1608429.56,GO
814695b6-f44e-491b-9921-af806f5bb25c,proposal,created,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,2908382.0,108
cc08d0d2-e519-495f-b7d6-db6391c21958,warranty,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,37113e50-26ae-48d2-aaf4-4cda8fa76c79,6040545.22,BA
f72d0829-beac-45bb-b235-7fa16b117c43,warranty,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,8ade6e09-cb60-4a97-abbb-b73bf4bd8f76,6688872.79,DF
5d9e1ec6-9304-40a1-947f-ab5ea993d100,proponent,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,2213ea91-4a3c-46a3-b3a7-ff55c2888561,Kathline Ferry,50,168896.38,true
23060b08-32bf-4e53-9866-69f6bcc7fdbd,proponent,added,2019-11-11T13:27:22Z,af6e600b-2622-40d1-89ad-d3e5b6cc2fdf,7526214a-cd5b-4e49-a723-e031bc82dcef,Merle Leuschke,50,143081.9,false
```

Output do exemplo:

```
814695b6-f44e-491b-9921-af806f5bb25c
```

Explicação: uma das propostas dos eventos não tinha nenhum proponente, por isso somente uma das propostas estavam válidas.
