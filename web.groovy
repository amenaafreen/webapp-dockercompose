String basePath = 'folder1'
folder(basePath) {
    description('Folder containing all jobs for folder-1')
}
   job("$basePath/scm-checkout") {
  
    scm {
        github('amenaafreen/webapp-dockercompose', 'master')
    }
   triggers {
     githubPush()
    }

   steps {
        shell('mvn clean package')
        }
   publishers {
       //archive the war file generated
       archiveArtifacts 'target/*.war'
}
     steps{

     shell ("""docker-compose down &&\
               docker-compose up -d &&\
               echo "APP URL" &&\
               echo "http://localhost:8903/LoginWebApp/" """)
       
     }

steps {
      nexusArtifactUploader {
        nexusVersion('nexus3')
        protocol('http')
        nexusUrl('localhost:8051/')
        groupId('com.nisum')
        version('1.0')
        repository('maven-repo')
        artifact {
            artifactId('nexus-artifact-uploader')
            type('war')
            file('target/*.war')
        }
      }
    }     
     
}
