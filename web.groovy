String basePath = 'folder1'
folder(basePath) {
    description('Folder containing all jobs for folder-1')
}
   mavenJob("$basePath/scm-checkout") {
  
    scm {
        github('amenaafreen/webapp-dockercompose', 'master')
    }
   triggers {
     githubPush()
    }

   goals('clean package')
   publishers {
       //archive the war file generated
       archiveArtifacts 'target/*.war'
}
     steps{

     shell ("""docker-compose down &&\
               docker-compose up -d &&\
               sleep 10s
               echo "APP URL" &&\
               curl -Is http://localhost:8903/LoginWebApp/""")
       
     }
steps {
    maven {
            goals('deploy:deploy-file')
            property('groupId', 'com.spring.maventest')
            property('artifactId', 'nexusartifact')
            property('version', '1.0.0')
            property('packaging', 'war')
            property('repositoryId', 'nexus')
            property('url', 'http://localhost:8051/#admin/repository/repositories:maven-repo')
            property('file', 'target/LogicWebApp.war')
           }


}
}
