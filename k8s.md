```
kubectl get pods -n kube-system

kubectl get pod -n kube-system -o wide

watch -n1 kubectl get pods -o wide

lubectl get namespaces

---------------------------------------

//explain pra entender onde esta o erro no manifesto
kubectl explain deployment

kubectl explain deployment.metadata

---------------------------------------
kubectl api-resources

```

---

- API-Resources: são os tipos de entidades (como Pods, Services, Deployments) que o Kubernetes entende e gerencia via sua API. Eles são o que você manipula com kubectl, e representam tudo o que "existe" no cluster

```
kubectl create -f deployment.yaml

apiVersion: apps/v1
kind: Deployment
metadata:
  name: mydeploy
  labels:
    app: mydeploy
spec:
  replicas: 3
  selector:
    matchLabels:
      app: mydeploy
  template:
    metadata:
      labels:
        app: mydeploy
    spec:
      containers:
        - name: myapp
          image: nginx


//Usando Kind pra simular k8s environment
kind create cluster --name levicluster --config cluster.yaml

kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
- role: control-plane
- role: worker
- role: worker


```
