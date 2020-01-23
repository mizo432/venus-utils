pipeline {
    agent any
    // 定数や変数を定義する
    environment {
        reportDir = 'build/reports'
        javaDir = 'src/main/java'
        resourcesDir = 'src/main/resources'
        testReportDir = 'build/test-results/test'
        jacocoReportDir = 'build/jacoco'
        javadocDir = 'build/docs/javadoc'
        libsDir = 'build/libs'
        appName = 'studio-web'
        appVersion = '1.0.0-SNAPSHOT'
    }

    // stagesブロック中に一つ以上のstageを定義する
    stages {
        stage('Preparation') {
            // 実際の処理はstepsブロック中に定義する
            steps {
                deleteDir()
                // このJobをトリガーしてきたGithubのプロジェクトをチェックアウト
                checkout scm
                // ジョブ失敗の原因調査用にJenkinsfileとbuild.gradleは最初に保存する
                archiveArtifacts "Jenkinsfile"
                archiveArtifacts "build.gradle"
                archiveArtifacts "settings.gradle"
                // scriptブロックを使うと従来のScripted Pipelinesの記法も使える
                script {
                    // Permission deniedで怒られないために実行権限を付与する
                    if(isUnix()) {
                        sh 'chmod +x gradlew'
                    }
                }
                gradlew 'clean'
            }
        }


        stage('Compile') {
            steps {
                gradlew 'classes testClasses'
            }
        }

        stage('analysis') {
            steps {
                // 並列処理の場合はparallelメソッドを使う
                parallel(
                    'static analysis' : {
                        gradlew 'check -x test'
                        // dirメソッドでカレントディレクトリを指定できる
                        recordIssues enabledForFailure: true, tools: [spotBugs(pattern: '**/build/reports/spotbugs/main.xml')]
                        recordIssues enabledForFailure: true, tools: [pmdParser(pattern: '**/build/reports/pmd/main.xml')]
                        recordIssues enabledForFailure: true, tools: [cpd(pattern: '**/build/reports/cpd/cpd.xml', reportEncoding: 'UTF-8', skipSymbolicLinks: true)]
                        archiveArtifacts "**/spotbugs/*.xml"
                        archiveArtifacts "**/pmd/*.xml"
                        archiveArtifacts "**/cpd/*.xml"
                    },
                    'task-scan': {
                        recordIssues(tools: [taskScanner(highTags: 'FIXME', ignoreCase: true, includePattern: '**/src/main/java/**/*.java', lowTags: 'XXX', normalTags: 'TODO')])
                    },
                    'step-count': {
                            // レポート作成
                            stepcounter outputFile: "stepcount.xls", outputFormat: 'excel', settings: [
                                    [key: 'Java', filePattern: "**/${javaDir}/**/*.java"],
                                    [key: 'SQL', filePattern: "**/${resourcesDir}/**/*.sql"],
                                    [key: 'HTML', filePattern: "**/${resourcesDir}/**/*.html"],
                                    [key: 'JS', filePattern: "**/${resourcesDir}/**/*.js"],
                                    [key: 'CSS', filePattern: "**/${resourcesDir}/**/*.css"]
                            ]
                            // 一応エクセルファイルも成果物として保存する
                            archiveArtifacts allowEmptyArchive: true, artifacts: "stepcount.xls"
                    }
                )
            }
        }
        stage('unit-test') {
            steps {
                gradlew 'test jacocoTestReport -x classes -x testClasses'
                junit allowEmptyResults: true, testResults: "**/${testReportDir}/*.xml"
                archiveArtifacts allowEmptyArchive: true, artifacts: "**/${testReportDir}/*.xml"
                // カバレッジレポートを生成（テストクラスを除外）
                echo 'JacocoReportアーカイブ 開始'
                jacoco exclusionPattern: '**/*Test*.class,**/*Mock*.class'
                echo 'JacocoReportアーカイブ 終了'
            }
        }
        stage('lib-release') {
            when {
                branch 'master'
            }
            steps {
                gradlew 'release -x test -Prelease.useAutomaticVersion=true'
            }
        }


    }

}

// Gradlewコマンドを実行する
def gradlew(command) {
    if(isUnix()) {
            sh "./gradlew ${command} --stacktrace --daemon"
        } else {
            bat "./gradlew.bat ${command} --stacktrace  --daemon"
    }
}