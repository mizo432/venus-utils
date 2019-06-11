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
                        findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: '**/soptbugs/*.xml', unHealthy: ''
                        pmd canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/pmd/*.xml', unHealthy: ''
                        dry canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/cpd/*.xml', unHealthy: ''
                        archiveArtifacts "**/spotbugs/*.xml"
                        archiveArtifacts "**/pmd/*.xml"
                        archiveArtifacts "**/cpd/*.xml"
                    },
                    'task-scan': {
                        step([
                            $class: 'TasksPublisher',
                            pattern: '**/*.java',
                            // 集計対象を検索するときに大文字小文字を区別するか
                            ignoreCase: false,
                            // 優先度別に集計対象の文字列を指定できる
                            // 複数指定する場合はカンマ区切りの文字列を指定する
                            high: 'FIXME,XXX',
                            normal: 'TODO',
                        ])
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