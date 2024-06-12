# kubernetes-example
## Running Local
### Setup Local Kubernetes
1. Install [microk8s](https://microk8s.io/docs/install-alternatives)
   ```shell
   sudo snap install microk8s --classic
    ```
2. Open firewall routes (Ubuntu)
   ```shell
   sudo ufw allow in on cni0 && sudo ufw allow out on cni0
   sudo ufw default allow routed
    ```
3. Enable basic microk8s addons. [Complete List](https://microk8s.io/docs/addons)
   ```shell
   sudo microk8s enable dns 
   sudo microk8s enable dashboard
   sudo microk8s enable storage
   sudo microk8s enable registry
   sudo microk8s enable hostpath-storage
    ```
4. Check if all addons are running 
   ```shell
   sudo microk8s kubectl get all --all-namespaces
    ```
5. Open kubernetes dashboard at the ip shown by this command:
   ```shell
   microk8s kubectl get svc kubernetes-dashboard -o jsonpath='{.spec.clusterIP}' -n kube-system
   ```
6. A token will be asked, run the following code to obtain it
   ```shell
   token=$(microk8s kubectl -n kube-system get secret | grep default-token | cut -d " " -f1)
   microk8s kubectl -n kube-system describe secret $token
    ```
   
### Now you are ready to deploy the application:
1. First, build our application and upload it to our local image registry
   ```shell
      docker build . -t localhost:32000/catalog-api:latest
      docker push localhost:32000/catalog-api:latest   
   ```
2. Then open the `k8s` folder present at root folder of this repo
   ```shell
      cd k8s
   ```
3. Apply base manifests by running the code below. It will deploy a Postgres database to our cluster.
   ```shell
    microk8s kubectl apply -f base/ 
   ```
4. Then apply the catalog manifests. It will deploy the latest image of our service to the cluster.
   ```shell
    microk8s kubectl apply -f catalog/
   ```