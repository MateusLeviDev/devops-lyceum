#### O que é Kubernetes

Kubernetes is by default a cluster in nature or cluster in behavior. offers: autohealing, scaling, enterprise level solutions


- ps: even when you are running a container you need to have something called as container runtime. sem isso seu container nunca irá rodar. in docker temos o `dockershim`. como o kubernetes tem um contexto mais complexto, e fornece uma série de soluções empresariais e etc, oq é feito é you create a master and you create a worker. para exemplos vamos criar 1 de cada, mas em geral temos mais unidades de cada em em produção (mult masters e mult workers).

- em kubernetes nao enviaremos a request para o worker e sim para a master. always. sempre vai para um Control Pane.

- kubelet -> responsavel por running seu pod. maintaining the pod. e mesmo assim ainda teremos um `container runtime`. the dff is in kubernetes docker is not mandatory, we can use CRI-O, containerd, dockershim e qualquer container runtime que impl kubernetes interface.

- in docker temos docker0 e bridge network the default networking. no kubernetes temos o `kube proxy`. because kubernetes has something called autoscaling, when you scale your pod instead of one replica if you have two replicas to your pod then there has to be a component which says okay send 50% das request here send 50% request here -> papel do kube proxy: provide networking ip address and also the load balacning default, load balacing capabilities in kubernetes. use ip tables linux for networking configs

#### Quais sao os 3 componenetes mais importantes?

Worker Node é responsável por executar os Pods, que são as menores unidades de trabalho no Kubernetes. Cada pod pode conter um ou mais contêineres.
Componentes do Worker Node:

    Kubelet:
        É o agente que roda em cada Worker Node.
        Garante que os pods estejam em execução e em conformidade com as instruções enviadas pelo Master Node.
        Interage diretamente com o runtime de contêiner (ex.: containerd, CRI-O) para iniciar ou parar contêineres.

    Kube-proxy:
        Gerencia a rede do cluster, garantindo que cada pod tenha um endereço IP exclusivo.
        Também é responsável pelo balanceamento de carga dentro do cluster, redirecionando o tráfego para as réplicas corretas dos pods.
        Enquanto o Docker usa docker0 e a bridge network como padrão para configurar a rede, o Kubernetes usa o kube-proxy.

    Runtime de Contêiner:
        É o software responsável por executar os contêineres em cada nó.
        Exemplos: Docker, containerd, CRI-O.

Como os Workers Funcionam?

    Os Worker Nodes recebem tarefas do Scheduler no Master Node.
    Cada Worker Node executa os pods atribuídos a ele, gerencia seu ciclo de vida e fornece logs e métricas para o Master Node.
    Se um Worker Node falhar, o Master redistribui os pods para outros Workers disponíveis.


#### who decide the pod creation. should the pod be created on node 2...

- pode haver mult instrução. deve haver um core que tem isso. has to be a component and that component is API Server. podemos chamar tb control pane. basicamente expoẽ seu cluster para o mundo externo. é o coração do kubernetes

- scheduler: Responsável por agendar recursos no cluster. Decide em qual nó (worker) os pods devem ser executados, considerando fatores como disponibilidade de recursos e afinidade de nós.

- API Server: É o "coração" do Kubernetes. Expõe o cluster para o mundo externo e gerencia todas as operações. Decide como e onde os pods serão criados.


- Controller Manager: Gerencia os controladores do Kubernetes, como: ReplicaSet: mantém o estado desejado dos pods no cluster (ex.: sempre manter 3 réplicas rodando).

- etcd: Banco de dados de chave-valor usado para armazenar o estado do cluster. É essencial para o funcionamento do Kubernetes.

- Cloud Controller Manager: Gerencia a integração do Kubernetes com provedores de nuvem (ex.: balanceadores de carga, armazenamento).


#### Arquitetura do Kubernetes

O Kubernetes segue uma arquitetura baseada em clusters, que consiste em dois tipos principais de nós: Master e Worker.

    Master Node (Control Plane):
        É o centro de controle do cluster.
        Todas as solicitações para o Kubernetes passam por ele. O Master gerencia e coordena os workers, além de tomar decisões sobre onde criar ou gerenciar os recursos.

    Worker Node:
        Responsável por executar os pods (unidades básicas no Kubernetes que encapsulam contêineres).
        Cada worker tem um kubelet, que:
            Garante que os pods estejam rodando.
            Interage com o runtime de contêineres para manter o estado dos pods.



Processo de criação de pods

    Quando um pod precisa ser criado, o API Server é o componente que recebe a solicitação e decide como processá-la.
    O Scheduler define em qual nó o pod será alocado.
    O kubelet, no nó selecionado, coordena a execução do pod com base no runtime de contêiner configurado.

Essa arquitetura modular e escalável permite que o Kubernetes seja amplamente utilizado em ambientes de produção para gerenciar aplicações de forma eficiente.


#### Master Node (Control Plane) e Worker

O Master Node é o núcleo do cluster Kubernetes e é responsável por gerenciar todo o cluster, incluindo agendamento, controle e monitoramento dos recursos. Ele é composto por vários componentes:
Componentes do Master Node:

    API Server:
        É o ponto de entrada para todas as interações com o cluster.
        Expondo a API RESTful, ele recebe solicitações (como criar ou deletar um pod) e atualiza o estado desejado no etcd.
        Também atua como intermediário para comunicação entre os outros componentes do Kubernetes.

    etcd:
        Banco de dados distribuído onde o estado do cluster é armazenado.
        Ele mantém o histórico de todos os objetos e configurações do cluster, permitindo recuperação em caso de falhas.

    Scheduler:
        Responsável por decidir em qual Worker Node cada pod deve ser executado.
        Toma decisões com base em métricas como disponibilidade de recursos, afinidade de nós e restrições definidas pelo usuário.

    Controller Manager:
        Coordena os diversos controladores do Kubernetes, garantindo que o estado atual do cluster esteja alinhado com o estado desejado.
        Exemplo: Se um pod falha, o Controller Manager acionará o ReplicaSet Controller para criar um novo pod automaticamente.

    Cloud Controller Manager (opcional):
        Gerencia interações entre o Kubernetes e provedores de nuvem.
        Exemplo: Configurar balanceadores de carga, provisionar volumes de armazenamento na nuvem.

Características do Master Node:

    Em ambientes de produção, geralmente há múltiplos Master Nodes para garantir alta disponibilidade e redundância.
    Usuários e ferramentas de automação sempre interagem com o cluster por meio do Master Node, nunca diretamente com os Workers.

Worker Node

O Worker Node é responsável por executar os Pods, que são as menores unidades de trabalho no Kubernetes. Cada pod pode conter um ou mais contêineres.
Componentes do Worker Node:

    Kubelet:
        É o agente que roda em cada Worker Node.
        Garante que os pods estejam em execução e em conformidade com as instruções enviadas pelo Master Node.
        Interage diretamente com o runtime de contêiner (ex.: containerd, CRI-O) para iniciar ou parar contêineres.

    Kube-proxy:
        Gerencia a rede do cluster, garantindo que cada pod tenha um endereço IP exclusivo.
        Também é responsável pelo balanceamento de carga dentro do cluster, redirecionando o tráfego para as réplicas corretas dos pods.

    Runtime de Contêiner:
        É o software responsável por executar os contêineres em cada nó.
        Exemplos: Docker, containerd, CRI-O.

Como os Workers Funcionam?

    Os Worker Nodes recebem tarefas do Scheduler no Master Node.
    Cada Worker Node executa os pods atribuídos a ele, gerencia seu ciclo de vida e fornece logs e métricas para o Master Node.
    Se um Worker Node falhar, o Master redistribui os pods para outros Workers disponíveis.

Fluxo de Comunicação Master-Worker

    Usuário ou ferramenta envia uma solicitação para o cluster via API Server no Master Node.
    O Master registra a solicitação no etcd como o estado desejado do cluster.
    O Scheduler determina em qual Worker Node os pods devem ser executados, com base em critérios como:
        Recursos disponíveis (CPU, memória).
        Restrições ou preferências do usuário (afinidade ou antiafinidade de nós).
    O kubelet no Worker Node selecionado recebe a tarefa e interage com o runtime de contêiner para iniciar os pods.
    O kube-proxy configura a rede para que os pods possam ser acessados internamente (ou externamente, se configurado).


#### Por que a criação de imagens é desnecessária no Kubernetes?

O Kubernetes foi projetado para trabalhar com imagens previamente construídas e armazenadas em um registro. Separação de responsabilidades: A construção de imagens é uma tarefa de desenvolvimento (CI/CD), enquanto o Kubernetes é uma ferramenta de operações (execução e orquestração). Ferramentas como Jenkins, GitHub Actions, ou GitLab CI geralmente cuidam da construção e do push de imagens para um registro.

- O Kubernetes baixa (pull) a imagem do registro configurado no spec de um Pod ou Deployment e usa o container runtime (como containerd ou CRI-O) para executá-la.

```

apiVersion: v1
kind: Pod
metadata:
  name: my-app
spec:
  containers:
  - name: app-container
    image: myregistry.com/my-app:1.0.0


```

