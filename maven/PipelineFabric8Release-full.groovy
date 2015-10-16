hubot room: 'release', message: "release started"
try {

  releaseProject{
    project = 'kubernetes-model'
    projectArtifact = 'kubernetes-model'
  }

  releaseProject{
    project = 'kubernetes-client'
    projectArtifact = 'kubernetes-client'
  }

  releaseProject{
    project = 'fabric8'
    projectArtifact = 'fabric8-maven-plugin'
  }

  releaseProject{
    project = 'ipaas-quickstarts'
    projectArtifact = 'archetypes/archetypes-catalog'
  }

  parallel(devops: {
    releaseProject{
      project = 'fabric8-devops'
      projectArtifact = 'devops/distro/distro'
    }
  }, ipaas: {
    releaseProject{
      project = 'fabric8-ipaas'
      projectArtifact = 'ipaas/distro/distro'
    }
  })

  hubot room: 'release', message: "release success"

} catch (err){
    hubot room: 'release', message: "Release failed ${err}"
    currentBuild.result = 'FAILURE'
}