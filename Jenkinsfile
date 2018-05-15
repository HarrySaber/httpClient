properties([
	buildDiscarder(logRotator(numToKeepStr: '20')),
	pipelineTriggers([
		pollSCM('H/2 * * * *')
	])
])

node {

	stage "Checkout"
		checkout scm
		checkout([$class: 'GitSCM',
			branches: [[name: '*/master']],
			extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'repo']],
			userRemoteConfigs: [[
				credentialsId: 'b48ac1f1-f4e8-4cdb-a6aa-9e34eebf11be',
				url: 'git@git.starpost.cn:devops/repo.git']]])


	stage "Build"
		withEnv(["JAVA_HOME=${ tool 'JDK8' }", "PATH+MAVEN=${tool 'MAVEN3'}/bin:${env.JAVA_HOME}/bin"]) {

			// Apache Maven related side notes:
			// --batch-mode : recommended in CI to inform maven to not run in interactive mode (less logs)
			// -V : strongly recommended in CI, will display the JDK and Maven versions in use.
			//      Very useful to be quickly sure the selected versions were the ones you think.
			// -U : force maven to update snapshots each time (default : once an hour, makes no sense in CI).
			// -Dsurefire.useFile=false : useful in CI. Displays test errors in the logs directly (instead of
			//                            having to crawl the workspace files to see the cause).
			sh "mvn -s repo/maven/settings.xml --batch-mode -V -U -e clean deploy -Dsurefire.useFile=false"
		}

		junit([testResults: "target/surefire-reports/*.xml", allowEmptyResults: true])
	
}
