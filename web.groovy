String basePath = 'folder'
folder(basePath) {
    description('Folder containing all jobs for folder-1')
}
   job("$basePath/scm-checkout") {
  
    scm {
        github('amenaafreen/demo-java', 'master')
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

     steps{

     shell ("""docker-compose up &&\
           echo "APP URL" &&\
           curl http://localhost:8903/LogicWebApp/""")
       
     }
}     
     
}
