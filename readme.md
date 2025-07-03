- future of devops

#K8s

## Problem 1 - Single Host Nature

Single Host: É a limitação e risco de rodar toda sua aplicação em uma única máquina física ou VM. ou seja, Se o host cair, todo o sistema para. por padrão, os containers Docker operam em um único host — ou seja, cada container é executado em uma única máquina física ou virtual. 

Se você estivesse usando Docker em um único host, você teria que manual e explicitamente criar mais containers para distribuir a carga

- o kubernetes automaticamente distribui os containers (ou pods) por múltiplos hosts no cluster.
- Kubernetes foi projetado para rodar distribuído. Ele divide os recursos entre nodes, e tem mecanismos internos para evitar a dependência de um único host. Distribuir containers por múltiplos hosts traz várias vantagens, principalmente em termos de escalabilidade. Horizontal: Quando você distribui containers por múltiplos hosts, pode aumentar a capacidade de sua aplicação de forma horizontal, ou seja, criando mais instâncias de containers em diferentes máquinas. 

- um nó é uma máquina (física ou virtual)

```
Kubernetes Cluster
├── Control Plane (pode ser replicado)
│   ├─ API Server
│   ├─ etcd
│   ├─ Controller Manager
│   └─ Scheduler
│
├── Node 1 
│   └─ kubelet, containerd, pods
│
├── Node 2
│   └─ kubelet, containerd, pods
│
└── Node 3
    └─ kubelet, containerd, pods

```

## Problem 2 - Auto Healing

- recupere automaticamente os containers e pods que falham
- podemos definir quantas réplicas o pod tera
- Horizontal Pod Autoscaler (HPA): para escalar automaticamente a quantidade de réplicas de pods, com base em métricas de desempenho (como uso de CPU ou memória)
- O auto-healing reduz significativamente o tempo de inatividade de aplicações

## Problem 3 - Auto Scaling

load is great increased scalue up...

- replication controller. 


---


- Kubernetes trabalha com imagens de contêiner já criadas e armazenadas em registros como o Docker Hub, Google Container Registry (GCR), ou Amazon Elastic Container Registry (ECR).
- O pod (containers independentes) é a unidade de execução mais básica no Kubernetes. Ele pode conter um ou mais containers que são executados juntos no mesmo host (nó) e compartilham recursos como rede e armazenamento.
