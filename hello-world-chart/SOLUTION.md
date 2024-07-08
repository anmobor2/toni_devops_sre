## instructions
I have used minikube for the kubernetes cluster. The following steps are to be followed to deploy the application on the cluster.
minikube start

## First:
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install hello-world-chart ./hello-world-chart -f hello-world-chart/values.yaml --namespace hello-world --create-namespace
kubectl apply -f hello-worls-chart/namespace.yaml
cd hello-worls-chart

## Second:
helm dependency build
helm install hello-world-chart ./hello-world-chart -f values.yaml --namespace hello-world

minikube tunnel

minikube ip

## third:
curl --resolve "hello-worls-chart.<minikube-ip>.nip.io:80:127.0.0.1" -i http://hello-world.<minikube-ip>.nip.io

my case the minikube ip is
curl --resolve "hello-worls-chart.192.168.49.2.nip.io:80:127.0.0.1" -i http://hello-world.192.168.49.2.nip.io

output:
HTTP/1.1 200 OK
Date: Mon, 03 Jun 2024 20:37:17 GMT
Content-Type: text/plain
Transfer-Encoding: chunked
Connection: keep-alive

Hello, World!%

## Optional points has been implemented sucessfully
Optional:

Generate documentation for Helm chart (norwoodj/helm-docs)
Test end-to-end Helm chart with Github Action: helm/chart-testing-action
Validate values.yaml with values.schema.json