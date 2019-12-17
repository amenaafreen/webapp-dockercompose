String basePath = 'folder'
folder(basePath) {
    description('Folder containing all jobs for folder-1')
}
   job("$basePath/Job-scm-checkout") {
   stage('Git checkout')
   {
   steps{
    scm {
        github('amenaafreen/demo-java', 'master')
    }
   triggers {
     githubPush()
    }
}
}
   stage('Compile and Package')
   {
   steps {
        shell('mvn clean package')
        }
   publishers {
       //archive the war file generated
       archiveArtifacts 'target/*.war'
}
}
 stage('Deploy')
{

     steps{

     shell ("""docker-compose up &&\
           echo "APP URL" &&\
           curl http://localhost:8903/LogicWebApp/""")
       
     }
}     
     
}
