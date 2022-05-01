job('NodeJS Docker example') {
    scm {
        git('git://github.com/boorayan/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('booorayan')
            node / gitConfigEmail('boorayo@yahoo.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('booora/docker_node')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
