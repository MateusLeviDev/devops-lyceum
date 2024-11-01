- `o que é um server?` é um sistema computacional dedicado. basicamente um serviço. que pode responder a solicitações do cliente, prover recursos
<br>
- `What's is bare metal?` consiste em um servidor físico dedicado que é provisionado e usado por um único cliente. o cliente tem acesso direto ao hardware subjacente, sem a intermediação de uma camada de virtualização.
<br>
- `o que é a Virtualization?` essa camada de virtulização se refere ao software (hypervisor) que permite que um único servidor físico seja dividido em múltiplos ambientes virtuais independentes
Podemos abordar assim os namespaces nos processos de isolamento lógico and o firmware hypervisor que gerencia essas VMs criadas.

1. **Namespaces**:
    - Os Namespaces fornecem um mecanismo para isolar e ocultar recursos do sistema operacional, criando instâncias virtuais desses recursos para processos específicos.
    - Eles permitem que os processos em um namespace vejam apenas os recursos atribuídos a esse namespace, criando assim uma ilusão de que cada processo tem seu próprio espaço de sistema.
    - Alguns dos principais namespaces usados no contexto de contêineres Docker são:
        - **PID (Process ID)**: Isola os IDs de processo, de modo que os processos dentro de um contêiner não possam ver ou afetar os processos fora do contêiner.
        - **Network**: Isola a pilha de rede, de modo que cada contêiner tenha seu próprio conjunto de interfaces de rede, tabelas de roteamento e afins.
        - **IPC (Inter Process Communication)**: Isola os mecanismos de comunicação entre processos, como filas de mensagens e semáforos, garantindo que apenas processos do mesmo contêiner possam interagir.
        - **Mount**: Isola o sistema de arquivos, permitindo que cada contêiner tenha seu próprio sistema de arquivos raiz e montagens específicas.
        - **UTS (Unix Timesharing System)**: Isola o hostname e o domínio de nomes do sistema.
    - Esses namespaces ajudam a garantir que os contêineres sejam isolados uns dos outros e do host, proporcionando uma camada de segurança e confiabilidade.
2. **cgroups (Control Groups)**:
    - Os cgroups permitem o controle e a limitação dos recursos do sistema, como CPU, memória, E/S de disco e largura de banda de rede, entre outros.
    - Eles dividem os recursos do sistema em grupos, permitindo que os administradores imponham políticas de alocação e limitação de recursos em cada grupo.
    - No contexto do Docker, os cgroups são usados para garantir que cada contêiner tenha acesso apenas aos recursos alocados a ele e não possa monopolizar os recursos do sistema.
    - Por exemplo, você pode definir limites de memória para cada contêiner, garantindo que nenhum deles use mais memória do que o permitido.
    - Os cgroups são essenciais para garantir a previsibilidade e o desempenho em ambientes de contêineres, onde várias cargas de trabalho podem estar sendo executadas em um único host.
<br>
- ou seja, um servidor, em ambientes de hypervisor, como VMware, permite que o servidor divida seus recursos (CPU, memória, armazenamento) entre várias VMs.
<br>
- `O que é um container?` em um contexto genérico, são pacotes de software que contêm todos os elementos necessários (cod, runtime, tools) para serem executados em qualquer ambiente. Dessa forma, os contêineres virtualizam o sistema operacional e são executados em qualquer lugar, desde um data center privado até a nuvem pública ou até mesmo no laptop pessoal de um desenvolvedor.
<br>
- Containers, em um contexto geral, são uma forma de virtualização a nível de sistema operacional, onde diferentes ambientes de execução (como aplicativos e suas dependências) são isolados uns dos outros em um único sistema operacional. Eles oferecem uma alternativa mais leve e eficiente que as VMs, pois compartilham o kernel do sistema e não precisam de um sistema operacional completo para cada instância, mas sim de bibliotecas e dependências específicas para cada aplicativo.
- LXC (Linux Containers): É uma das primeiras implementações de containers no Linux, lançada em 2008. LXC usa namespaces e cgroups do kernel Linux para isolar processos e limitar recursos, permitindo que diferentes ambientes rodem no mesmo host de forma segura e isolada.
- `Docker` is dependent of docker engine. se a docker engine cair os containers param de rodar. a single point of failure. para solucionar este e outros problemas relacionados a containers, e the image is being built from docker file it creates a lot of layers, podemos usar o Buildah. will 

<br>
take a look...
[link](https://www.youtube.com/watch?v=AvMEVN98Xec)
<br>

- o container no docker usa o host da máquina q ele esta rodando, mas ainda assim possui basic folders que promovem o isolamento necessário para a segurança

## Why are containers light weight ?

Containers are lightweight because they use a technology called containerization, which allows them to share the host operating system's kernel and libraries, while still providing isolation for the application and its dependencies. This results in a smaller footprint compared to traditional virtual machines, as the containers do not need to include a full operating system. Additionally, Docker containers are designed to be minimal, only including what is necessary for the application to run, further reducing their size.

Let's try to understand this with an example:

Below is the screenshot of official ubuntu base image which you can use for your container. It's just ~ 22 MB, isn't it very small ? on a contrary if you look at official ubuntu VM image it will be close to ~ 2.3 GB. So the container base image is almost 100 times less than VM image.

To provide a better picture of files and folders that containers base images have and files and folders that containers use from host operating system (not 100 percent accurate -> varies from base image to base image). Refer below.



### Files and Folders in containers base images

```
    /bin: contains binary executable files, such as the ls, cp, and ps commands.

    /sbin: contains system binary executable files, such as the init and shutdown commands.

    /etc: contains configuration files for various system services.

    /lib: contains library files that are used by the binary executables.

    /usr: contains user-related files and utilities, such as applications, libraries, and documentation.

    /var: contains variable data, such as log files, spool files, and temporary files.

    /root: is the home directory of the root user.
```



### Files and Folders that containers use from host operating system

```
    The host's file system: Docker containers can access the host file system using bind mounts, which allow the container to read and write files in the host file system.

    Networking stack: The host's networking stack is used to provide network connectivity to the container. Docker containers can be connected to the host's network directly or through a virtual network.

    System calls: The host's kernel handles system calls from the container, which is how the container accesses the host's resources, such as CPU, memory, and I/O.

    Namespaces: Docker containers use Linux namespaces to create isolated environments for the container's processes. Namespaces provide isolation for resources such as the file system, process ID, and network.

    Control groups (cgroups): Docker containers use cgroups to limit and control the amount of resources, such as CPU, memory, and I/O, that a container can access.
    
```

It's important to note that while a container uses resources from the host operating system, it is still isolated from the host and other containers, so changes to the container do not affect the host or other containers.

**Note:** There are multiple ways to reduce your VM image size as well, but I am just talking about the default for easy comparision and understanding.

so, in a nutshell, container base images are typically smaller compared to VM images because they are designed to be minimalist and only contain the necessary components for running a specific application or service. VMs, on the other hand, emulate an entire operating system, including all its libraries, utilities, and system files, resulting in a much larger size. 

I hope it is now very clear why containers are light weight in nature.

- o que é Docker? Docker: um PassS que faz virtualização em nível de sistema operacional. Baseado no LXC, conjunto de libs, tools e APIs para uso de containers no Linux. Quando falamos de Docker estamos falando de isolamento de memória, CPU, rede e etc... Esse isolamento pode ser dividido em duas ideias. Primeiramente a parte do isolamento seria processo lógico (redes, processos, usuários, outpoint), outro ponto interessante é o isolamento referente aos recursos (gerenciamento de recursos, CPU, memória, IO de rede ou IO de bloco), uma parte mais física.
 
#### Docker daemon

- daemon o coração do docker.

The Docker daemon (dockerd) listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes. A daemon can also communicate with other daemons to manage Docker services.

#### Docker registries

A Docker registry stores Docker images. Docker Hub is a public registry that anyone can use, and Docker is configured to look for images on Docker Hub by default. You can even run your own private registry.

When you use the docker pull or docker run commands, the required images are pulled from your configured registry. When you use the docker push command, your image is pushed to your configured registry.
Docker objects

When you use Docker, you are creating and using images, containers, networks, volumes, plugins, and other objects. This section is a brief overview of some of those objects.


#### Dockerfile

Dockerfile is a file where you provide the steps to build your Docker Image.

[link](https://www.youtube.com/watch?v=wodLpta-hoQ&list=PLdpzxOOAlwvLjb0vTD9BXLOwwLD_GWCmC&index=2)



```
## Define a build-time argument

ARG BASE_IMAGE=us-central1-docker.pkg.dev/bullla-one-d-cicd-0960/bullla-docker-registry/openjdk:21-slim

## Create Docker image

FROM ${BASE_IMAGE}

WORKDIR /app

COPY target/*.jar /app/app.jar

EXPOSE 8080
ENV spring_profiles_active prod
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
    
```


- Mapeamento de Porta do Docker: O comando docker run -p 8000:8000 mapeia a porta 8000 do container para a porta 8000 da instância EC2. Isso significa que qualquer solicitação recebida na porta 8000 da EC2 será redirecionada para a porta 8000 no container onde seu app Django está rodando.
- `docker run -p 8000:8000 it <id>`
- podemos expor a porta por meio do nosso ec2 ubuntu
- so precisamos add nossas inbound traficc rules no security no console da aws na parte das instancias 
