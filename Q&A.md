#### What is Docker

Docker é um PasS -> platform as a service, muito conhecido no contexto de computação em nuvem. em resumo é uma engine de containerização, ou seja, usa virtualização a nível de OS.


#### How containers are different from VMs?

1 - Containers são lightwight em comparação as VMs. justamente por essa questão da virtualização a nível de OS com base no host que esta running.

2 - A tecnologia de containerization allows them to share the host operating system's kernel and libraries, while still providing isolation for the application and its dependencies. This results in a smaller footprint compared to traditional virtual machines, as the containers do not need to include a full operating system. Additionally, Docker containers are designed to be minimal, only including what is necessary for the application to run, further reducing their size.


#### What is Docker life cycle?

- build: É a etapa de criação de uma imagem a partir de um Dockerfile, que contém instruções para configurar o ambiente e empacotar o aplicativo. A imagem gerada é reutilizável e serve de base para criar containers.

- e podemos executar uma série de comandos com base no container criado a partir da imagem do dockerfile

#### What are the different Docker components?

- Docker CLI
- ao baixar tudo q é preciso temos o docker daemon que é responsável por executar as ações. daemon é a principal engine do docker
- temos o docker registry. que será o hub que armazena as imagens. 

#### Whats the difference between docker COPY e ADD?

 COPY: ele basicamente copies files or directories from the host file system to the container file system. Use COPY when you just need to copy files and directories without any additional functionality.

 <br>

 ADD: já é uma Enhanced functionality. 

 - URL support: You can copy files from a remote URL (e.g., downloading a file).
 - Automatic extraction: If the source file is a tarball (e.g., .tar, .tar.gz, .tgz), ADD will automatically extract it into the destination directory in the container.
 - Use ADD if you need the extra features, such as downloading files from a URL or extracting a compressed archive. por exemplo buscar pela url arquivos de logs que está em um Bucket na S3

 #### Whats the difference between CMD e entrypoint in Docker?

 - ENTRYPOINT: Defines the default executable to run when a container starts. The ENTRYPOINT sets the main command that is always executed, and any arguments provided during the docker run command are passed to this executable. Use ENTRYPOINT when you want to define the main command that should always run in the container (e.g., the application or service that the container is designed for).

 ```
 ENTRYPOINT ["python3", "app.py"]

 ```

 - In this case, every time the container starts, it will execute python3 app.py, and any additional arguments passed during the docker run will be passed to the Python script.


 - CMD: Defines default arguments that can be passed to the ENTRYPOINT or the command when the container starts. If ENTRYPOINT is defined, CMD provides arguments to it. If ENTRYPOINT is not defined, CMD is the command that gets executed. The CMD is only used when no command is specified in the docker run command. It can be overridden by passing a command in docker run. Use CMD when you want to provide default arguments to the command or executable that will run. It’s useful for defining default behavior that can be overridden by the user.

In summary:

    ENTRYPOINT: Defines the core command to always run.
    CMD: Provides default arguments that can be overridden.


```
# Dockerfile
FROM ubuntu:latest
CMD ["echo", "Hello, World!"]




Output: Hello, World!
```

```
# Dockerfile
FROM ubuntu:latest
ENTRYPOINT ["echo", "Hello from ENTRYPOINT"]

```

- CMD: Quando você executa um container com docker run myimage ls -l e o Dockerfile tem apenas um CMD, esse comando substitui completamente o CMD, incluindo o comando e seus argumentos. Ou seja, Hello, World! será totalmente ignorado.
- CMD é ideal quando você quer fornecer um comando padrão para o container, mas quer permitir que os usuários possam substituí-lo facilmente ao executar o container com outros comandos.
- ENTRYPOINT é útil quando você quer garantir que um comando específico sempre será executado, independentemente dos argumentos fornecidos. Isso é ideal para containers que devem rodar uma aplicação específica ou um processo que não deve ser alterado.ENTRYPOINT é útil quando você quer garantir que um comando específico sempre será executado, independentemente dos argumentos fornecidos. Isso é ideal para containers que devem rodar uma aplicação específica ou um processo que não deve ser alterado.

#### What are the networking types in docker and what's the default?

- host
- bridge -> default
- overlay
- MacVlan

bridge tem uma virtual ethernet, V8 or docker network (docker 0) que é criada using wich container can acess your host network. This is typically used for containers that need to communicate with each other on the same host machine, but are isolated from the outside world. usando o host network you will bind your host network with the container network, sera parte do seu host network. When a container is started with the default network, Docker automatically creates a virtual bridge network (usually named bridge).

- Access to Host: Containers can access the host machine via the host network interface, typically through port mappings (-p flag).

- Host Network: This is used when you want to give the container full access to the host network stack

- Overlay Network: This is used when you need containers to communicate across different Docker hosts (machines), typically in a Docker Swarm or `Kubernetes` setup.

- Macvlan Network: This is used when you want each container to appear as a separate physical device on the network (with its own MAC address).

#### Can you explain how to isolate networking betwwen containers?

Sim, podemos fazer isso crindo uma networking personalizada, como em alguns dos exemplos de código no repo. 


![Screenshot from 2024-11-12 19-45-10](https://github.com/user-attachments/assets/c0f40a9b-6605-431d-901f-9b2ecc5f91c0)


![Screenshot from 2024-11-12 19-47-17](https://github.com/user-attachments/assets/260df6b3-2a20-42f5-8adf-43eb20deba7b)


![Screenshot from 2024-11-12 19-48-24](https://github.com/user-attachments/assets/73d1a695-1f82-42da-a8ef-10284c465544)


- https://www.youtube.com/watch?v=Y5HQmgTNAtw&list=PLdpzxOOAlwvLjb0vTD9BXLOwwLD_GWCmC&index=10

