- future of devops

#### Problem 1 - Single Host Nature

por padrão, os containers Docker operam em um único host — ou seja, cada container é executado em uma única máquina física ou virtual. 

- pode gerar problemas em producão

Se você estivesse usando Docker em um único host, você teria que manual e explicitamente criar mais containers para distribuir a carga

- o kubernetes automaticamente distribui os containers (ou pods) por múltiplos hosts no cluster

Distribuir containers por múltiplos hosts traz várias vantagens, principalmente em termos de escalabilidade. Horizontal: Quando você distribui containers por múltiplos hosts, pode aumentar a capacidade de sua aplicação de forma horizontal, ou seja, criando mais instâncias de containers em diferentes máquinas. 

- um nó é uma máquina (física ou virtual) 
- O pod é a unidade de execução mais básica no Kubernetes. Ele pode conter um ou mais containers que são executados juntos no mesmo host (nó) e compartilham recursos como rede e armazenamento.
- pods: containers independentes


#### Problem 2 - Auto Healing

- recupere automaticamente os containers e pods que falham
- podemos definir quantas réplicas o pod tera
- Horizontal Pod Autoscaler (HPA): para escalar automaticamente a quantidade de réplicas de pods, com base em métricas de desempenho (como uso de CPU ou memória)
- O auto-healing reduz significativamente o tempo de inatividade de aplicações

#### Problem 3 - Auto Scaling

load is great increased scalue up...

- se nao tiver load balance nao podemos falar pro user qual data, url acessar... LB atua nisso. o cliente so ira acessa netflix.com
- there is always a load balance behind the hood

#### Problem 4 - Enterprise 

kubernetes é basicamente um cluster, ou seja, a group of nodes. 

- kubernetes in a production use case it is installed in a master node architecture -> just like jenkins. we create clusters
- embora tb role de instalar com um single node
- do google
- o docker por si so n tem soluções enterprise level
- um orq resolve isso. mas sim, o kubernetes n resolve tudo
- n é como as VMs. nos anos 10s, geral tava nas VMs. algumas pessoas falam que é envidente que certas configs eram mais simples para integrar. VM oferece mais segurança (Isolamento Completo, Cada máquina virtual possui seu próprio sistema operacional e kernel) do que containers. mas esta evoluindo
- em alguns aspectos, mais diretos e familiares para muitas equipes e organizações na época. Kubernetes e outras ferramentas de orquestração de containers ajudaram a simplificar essas configurações e a tornar os containers mais viáveis para ambientes de produção em larga escala,