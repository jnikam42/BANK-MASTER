import groovy.json.JsonSlurper
import hudson.util.Secret

pipeline {
	agent any

	options {
		skipStagesAfterUnstable()
	}
	
	environment {
         JAVA_HOME = tool 'JDK17'
       }

	stages {
		stage('Build') {
			steps {
				sh 'mvn -B -DskipTests clean package'
			}

			post {
				always {
					archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
				}
			}
		}

		stage('Deploy to Artifactory') {
			steps {
				script {
					def server = Artifactory.server 'artifactory-server-id'
					def buildInfo = Artifactory.newBuildInfo()

					def files = findFiles(glob: 'target/*.jar')
					def targetDir = 'com/sarvatra/jenkins/bank-product-feature-data'
					def repo = 'libs-release-local'
					def baseUrl = server.getUrl()
					files.each { file ->
						def fileName = file.getName()
						def targetPath = "${repo}/${targetDir}/${fileName}"
						echo "Target Path ${targetPath}"

						def fileExists = sh(
							script: "curl -s --head '${baseUrl}/${targetPath}' | grep -q '200 OK'",
							returnStatus: true
						)
						
						if (fileExists == 0) {
							echo "${fileName} already exists in Artifactory. Skipping upload."
							error("${fileName} already exists in Artifactory. Skipping upload.")
						} else {
							
							def uploadSpec = """{
								"files": [
											{
												"pattern": "${file.path}",
												"target": "${targetPath}"
									  		}
										]
									}"""
				
							
							server.upload(uploadSpec,buildInfo)
							currentBuild.description = "${baseUrl}/${targetPath}"
							return;
						}
					}
				}
			}
		}
		
		stage('Deploy to Server') {
    steps {
        sshagent(credentials: ['qa']) {
            script {
                def artifact = findFiles(glob: 'target/*.jar')[0]
                echo "Deploying ${artifact.name} to ${TARGET_HOST}:${MICRO_V2_PATH}/applications"
                
                // Extract program name (remove version and .jar)
                def programName = artifact.name.replaceAll(/-\d+(\.\d+)*$/, '') // removes -version

                // Stop the running process before deploying the new artifact
                echo "Stopping the current running program if any..."
                sh """
                    ssh ${TARGET_USER}@${TARGET_HOST} '
                        ${MICRO_V2_PATH}/start_stop/stop.sh --s ${programName}
                    '
                """
                
                // Deploy the new artifact
                echo "Deploying ${artifact.name}..."
                sh """
                    scp target/${artifact.name} ${TARGET_USER}@${TARGET_HOST}:${MICRO_V2_PATH}/applications
                    ssh ${TARGET_USER}@${TARGET_HOST} '
                        ${MICRO_V2_PATH}/start_stop/start.sh --s ${artifact.name}
                    '
                """
            }
        }
    }
}

	}

}

