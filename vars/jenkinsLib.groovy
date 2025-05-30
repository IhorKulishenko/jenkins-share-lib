def info(message) {
    echo "INFO: ${message}"
}

def pullImage(String imageName) {
    def fullImageName = "ghcr.io/ihorkulishenko/" + imageName
    
    sh "docker pull ${fullImageName}"
}

def undeployOldApp(String containerName) {
    // sh '''
    //     container_id=$(docker ps --filter "name=${containerName}" --format='{{json .ID}}' | tr -d '"')

    //     if [ -n "${container_id}" ]; then
    //         docker container stop ${container_id}
    //         docker container rm ${container_id}
    //     fi
    // '''
    sh "docker container stop ${containerName} || true"    
    sh "docker container rm ${containerName} || true"
    sh "docker image prune -f"
}

def deployToDev(String containerName, String imageName) {
     sh "docker run -d --name ${containerName} -p 3001:3000 ${imageName}"
}

def deployToMain(String containerName, String imageName) {
     sh "docker run -d --name ${containerName} -p 3000:3000 ${imageName}"
}
