## Bind mounts e volumes

1. Bind Mounts

    Localização: Com bind mounts, você especifica um caminho completo para um diretório ou arquivo do sistema host (por exemplo, /home/usuario/dados) que será montado no container.
    Funcionamento: O Docker monta o diretório ou arquivo do host no container no caminho especificado. Por exemplo, você pode mapear o diretório local ~/meus_dados para o caminho /dados dentro do container com docker run -v ~/meus_dados:/dados.
    Vantagem: Fácil para mapear um diretório específico, podendo ser útil para desenvolvimento e testes locais, pois você pode ver as alterações no host imediatamente refletidas no container.
    Desvantagem: Pode levar a problemas de segurança e portabilidade, pois o caminho absoluto é fixo no sistema host e pode não existir em outro sistema.

2. Volumes

    Localização: Volumes são gerenciados pelo Docker e ficam localizados dentro de um diretório padrão do Docker (por exemplo, /var/lib/docker/volumes no Linux).
    Funcionamento: Em vez de referenciar um caminho do sistema de arquivos do host, o Docker gerencia esses dados e permite que você use nomes para os volumes. Para montar um volume nomeado, você pode usar docker run -v nome_do_volume:/dados.
    Vantagem: Volumes são a maneira recomendada pelo Docker para persistir dados, pois são mais seguros, permitem fácil backup e restauração, e evitam o uso de caminhos absolutos. Também podem ser usados por múltiplos containers e são portáteis.
    Desvantagem: O controle sobre onde os dados são armazenados é limitado, e o acesso direto aos dados do volume pelo host requer conhecimento do diretório padrão de volumes do Docker.

Qual escolher?

    Bind Mounts são úteis para desenvolvimento local onde você precisa acessar e editar arquivos no sistema host.
    Volumes são ideais para produção e para manter dados persistentes de maneira mais gerenciável e segura, além de facilitar backup e restauração.



- Bind mounts e volumes resolvem problemas relacionados à persistência de dados e à separação de estado em containers Docker, oferecendo várias soluções práticas:

1. Persistência de Dados

    Problema: Quando um container Docker é removido, todos os dados internos dele são perdidos, já que o sistema de arquivos dentro do container é efêmero.
    Solução com Bind Mounts e Volumes: Ambos os métodos permitem armazenar dados fora do sistema de arquivos interno do container, possibilitando que dados importantes permaneçam mesmo se o container for destruído ou reiniciado.

2. Separação de Estado e Stateless Containers

    Problema: Containers são frequentemente usados para fornecer aplicações "stateless" (sem estado), mas em muitos casos, como em bancos de dados, o estado (dados) precisa ser armazenado e mantido separado do container.
    Solução: Volumes e bind mounts armazenam o estado fora do container, permitindo a criação e exclusão de containers sem perder os dados. Isso é especialmente útil em clusters, onde os containers podem ser recriados e balanceados em diferentes máquinas.

3. Isolamento e Segurança dos Dados

    Problema: Em ambientes de produção, o armazenamento de dados sensíveis diretamente no container pode ser inseguro, pois pode expor dados caso o container seja comprometido.
    Solução com Volumes: Volumes gerenciados pelo Docker oferecem uma camada adicional de abstração e segurança, isolando o armazenamento do container e facilitando permissões restritas.

4. Flexibilidade e Portabilidade

    Problema: Diferentes ambientes (desenvolvimento, teste, produção) podem requerer dados específicos que não devem estar empacotados na imagem do container, já que isso diminuiria a portabilidade.
    Solução: Com bind mounts e volumes, é possível montar dados específicos para cada ambiente, sem modificar a imagem do container. Isso torna o desenvolvimento e a portabilidade entre ambientes muito mais flexíveis.

5. Backups e Restauração

    Problema: Manter backups confiáveis de dados críticos em containers é desafiador quando o armazenamento está dentro do próprio container.
    Solução com Volumes: Volumes podem ser facilmente conectados a ferramentas de backup, pois os dados são armazenados externamente e de forma centralizada. Isso facilita o backup, migração e restauração de dados em caso de falhas.

6. Compartilhamento de Dados entre Containers

    Problema: Em sistemas que usam múltiplos containers (microserviços, por exemplo), pode ser necessário compartilhar dados entre containers de maneira eficiente.
    Solução: Volumes podem ser compartilhados entre containers para criar um ponto de acesso comum aos dados. Por exemplo, um container de aplicação e um de backup podem acessar o mesmo volume, simplificando a integração e troca de dados.

    - `docker volume create levi`

    - criado um volume, por exemplo, que pode ser associado a um ou multiplos containers

    - `docker volume ls`

    - `docker volume inspect <name>`


    ---


    # Docker Volumes

## Problem Statement

It is a very common requirement to persist the data in a Docker container beyond the lifetime of the container. However, the file system
of a Docker container is deleted/removed when the container dies. 

## Solution

There are 2 different ways how docker solves this problem.

1. Volumes
2. Bind Directory on a host as a Mount

### Volumes 

Volumes aims to solve the same problem by providing a way to store data on the host file system, separate from the container's file system, 
so that the data can persist even if the container is deleted and recreated.

![image](https://user-images.githubusercontent.com/43399466/218018334-286d8949-d155-4d55-80bc-24827b02f9b1.png)


Volumes can be created and managed using the docker volume command. You can create a new volume using the following command:

```
docker volume create <volume_name>
```

Once a volume is created, you can mount it to a container using the -v or --mount option when running a docker run command. 

For example:

```
docker run -it -v <volume_name>:/data <image_name> /bin/bash
```

This command will mount the volume <volume_name> to the /data directory in the container. Any data written to the /data directory
inside the container will be persisted in the volume on the host file system.

### Bind Directory on a host as a Mount

Bind mounts also aims to solve the same problem but in a complete different way.

Using this way, user can mount a directory from the host file system into a container. Bind mounts have the same behavior as volumes, but
are specified using a host path instead of a volume name. 

For example, 

```
docker run -it -v <host_path>:<container_path> <image_name> /bin/bash
```

## Key Differences between Volumes and Bind Directory on a host as a Mount

Volumes are managed, created, mounted and deleted using the Docker API. However, Volumes are more flexible than bind mounts, as 
they can be managed and backed up separately from the host file system, and can be moved between containers and hosts.

In a nutshell, Bind Directory on a host as a Mount are appropriate for simple use cases where you need to mount a directory from the host file system into
a container, while volumes are better suited for more complex use cases where you need more control over the data being persisted
in the container.
