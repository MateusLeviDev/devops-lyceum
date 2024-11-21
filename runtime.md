Um Container Runtime é o software responsável por gerenciar a execução de contêineres em um sistema. 

- antes da versão 1.11, o Docker era considerado um único monolito. Tudo que podia ser feito por um runtime de containers estava sendo feito pelo Docker em um único local, o download das imagens, rede, gerenciamento de ciclo de vida e tudo mais em um único processo rodando como root.
- a empresa disse que iria quebrar o daemon do Docker e começar a utilizar o runC juntamente com o containerd.
- o Docker Daemon continua existindo, mas seu papel foi redesenhado com o tempo para seguir um modelo mais modular. muitas de suas responsabilidades foram transferidas para outros componentes, como o containerd e o runC, em conformidade com os padrões da OCI (Open Container Initiative).

- ![image](https://github.com/user-attachments/assets/f5dc3115-1b7c-4d2f-9157-d628b40bea44)

    Docker Engine (ou Docker Daemon, dockerd): É o responsável por receber os comandos do usuário pelo CLI e passar essas requisições para o containerd.
    containerd: Interface compatível com o OCI capaz de executar, baixar e extrair qualquer imagem que seja também compatível com a OCI e executar processos do runC.
    runC: Lida com todo o gerenciamento e criação dos containers de acordo com a especificação do OCI.

- Podemos ver que o engine (dockerd) principal do Docker foi separado do runtime, e agora ele se concentrava somente em receber os inputs do usuário e comunicar os mesmos ao containerd, que por sua vez iria iniciar os runtimes do runC ou qualquer outro runtime que fosse compatível com o OCI.
- "Daemon" é uma designação comum em sistemas Unix/Linux para serviços que rodam em segundo plano.
- dockerd e docker daemon: Em resumo, dockerd é o binário (ou programa) que implementa e executa o Docker Daemon.

O containerd é a ferramenta principal que abstrai grande parte da funcionalidade que o Docker acumulava como um todo, se fossemos simplificar, podemos dizer que o containerd é o conjunto de funções mínimas que um container runtime precisa para executar qualquer container.
O containerd ajuda a abstrair as chamadas de kernel (syscalls) para que containers possam executar da mesma forma em qualquer sistema operacional, independente do que está acontecendo embaixo deles. Isso é chamado de supervisão e é um padrão muito comum quando estamos executando VMs.
Sempre que rodamos um SO dentro de outro, o SO convidado não sabe que está rodando dentro de uma VM, então ele continua chamando as syscalls necessárias para se comunicar com o hardware, e é ai que entra o trabalho do hypervisor, que é abstrair essas chamadas de sistema para que o SO convidado não precise implementar cada kernel individual se ele estiver rodando dentro, por exemplo, de uma máquina Linux.

- A Similaridade com o containerd: Assim como o hypervisor faz essa "tradução" e abstração para VMs, o containerd faz o mesmo para contêineres. Ele intercepta chamadas que os contêineres fazem ao kernel e gerencia os recursos necessários para que eles funcionem de forma padronizada.
Isso significa que o contêiner pode rodar independentemente das diferenças do sistema operacional subjacente.

- https://computingforgeeks.com/docker-vs-cri-o-vs-containerd/?ref=blog.lsantos.dev
- https://kubernetes.io/docs/concepts/overview/

- Kubernetes and CRI-O process
- ![Screenshot from 2024-11-21 13-16-12](https://github.com/user-attachments/assets/4938ee6a-752d-4b62-808d-6a017790f337)



- docker, containerd...
- ![Screenshot from 2024-11-21 13-21-57](https://github.com/user-attachments/assets/bf416f32-b664-4a5d-8017-06995fad5af4)

