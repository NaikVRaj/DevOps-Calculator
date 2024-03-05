
# Scientific Calculator Mini Project

## Overview

This project is a mini project for the Software Production and Engineering (SPE) subject. It involves building a scientific calculator application written in Java and includes various DevOps practices such as Continuous Integration (CI), Continuous Deployment (CD), and infrastructure automation.


## Application Description

The scientific calculator application provides functionalities for basic arithmetic operations (addition, subtraction, multiplication, division), trigonometric functions (sine, cosine, tangent), power, factorial, square root, and provides the value of π (pi).

## Tools Used

- Language: Java
- Version Control: Git/GitHub
- Continuous Integration: Jenkins
- Containerization: Docker
- Infrastructure Automation: Ansible

## Project Structure

### 1. **What and Why of DevOps?**

   DevOps is a set of practices that combine software development (Dev) and IT operations (Ops) to shorten the software development lifecycle and deliver high-quality software. It emphasizes collaboration, automation, and integration between developers and IT operations teams.

### 2. **Tools Used**

   - **Git/GitHub**: Version control system for managing source code.
   - **Jenkins**: Automation server for CI/CD pipelines.
   - **Docker**: Platform for containerizing applications.
   - **Ansible**: Automation tool for infrastructure management.

### 3. **Step-by-Step Explanation**

   1. **Source Control Management (Git/GitHub)**

      - **Setup**: Create a GitHub repository for the project.
      - **Configuration**: Configure Git credentials and repository settings.
      - **Commands Used**:
        ```bash
        git init
        git add .
        git commit -m "Initial commit"
        git remote add origin <repository_url>
        git push -u origin master
        ```
      - **Screenshots**: [GitHub Repo Creation](screenshots/git-hub.png) (screenshots/github_push.png)

   2. **Continuous Integration (Jenkins)**

      - **Setup**: Install and configure Jenkins server.
      - **Configuration**: Configure Jenkins job for building and testing the application.
      - **Pipeline Script**:
        ```groovy 
        pipeline {
            agent any
            tools {
                maven 'maven'
            }
            stages {
                // Stage to checkout source code and build with Maven
                stage('Build Maven') {
                    steps {
                        // Checkout source code from Git repository
                        checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/NaikVRaj/miniProject/']])
                        // Build project with Maven
                        sh 'mvn clean install'
                    }
                }

                // Stage to run JUnit tests
                stage('JUnit Tests') {
                    steps {
                        // Run JUnit tests with Maven
                        sh 'mvn test'
                    }
                }

                // Stage to build Docker image
                stage('Build docker image') {
                    steps {
                        script {
                            // Build Docker image
                            sh 'docker build -t naikvraj/miniproject .'
                        }
                    }
                }

                // Stage to push Docker image to Docker Hub
                stage('Push to docker hub') {
                    steps {
                        script {
                            // Login to Docker Hub with credentials
                            withCredentials([string(credentialsId: 'demo2', variable: 'speminiproject')]) {
                                sh 'docker login -u naikvraj -p ${speminiproject}'
                            }
                            // Push Docker image to Docker Hub
                            sh 'docker push naikvraj/miniproject'
                        }
                    }
                }

                // Stage to stop and remove existing Docker container
                stage('Stop and Remove Existing Container') {
                    steps {
                        script {
                            // Stop and remove existing Docker container named miniProject if it exists
                            sh 'docker rm -f miniProject || true'
                        }
                    }
                }

                // Stage to run Ansible playbook for deployment
                stage('Run Ansible Playbook') {
                    steps {
                        script {
                            // Run Ansible playbook for deployment
                            ansiblePlaybook(
                                playbook: 'deploy.yml',
                                inventory: 'inventory'
                            )
                        }
                    }
                }
            }
        }
        ```
      - **Screenshots**: [Jenkins Pipeline Creation](Screenshots/Repos/pipeline.png)

   3. **Containerization (Docker)**

      - **Setup**: Install Docker on the server.
      - **Configuration**: Write Dockerfile to define the container environment.
      - **Dockerfile**:
        ```Dockerfile
        FROM openjdk:17
        COPY . /app
        WORKDIR /app
        RUN mvn clean install
        CMD ["java", "-jar", "target/miniproject.jar"]
        ```
      - **Screenshots**: [Dockerfile](Screenshots/Repos/docker_hub.png)

   4. **Infrastructure Automation (Ansible)**

      - **Setup**: Install Ansible on the control node.
      - **Configuration**: Write Ansible playbook for deploying the application.
      - **Ansible Playbook**:
        ```yaml
        ---
        - name: Pull Docker Image from Docker Hub
          hosts: localhost
          remote_user: vraj-naik
          connection: local
          tasks:
            - name: Pull Docker Image
              docker_image:
                name: "naikvraj/miniproject"
                source: pull
              register: docker_pull_result

            - name: Display Docker Pull Result
              debug:
                var: docker_pull_result

            - name: Start Docker service
              service:
                name: docker
                state: started

            - name: Running container
              shell: docker run -it -d --name miniProject naikvraj/miniproject
        ```
      - **Screenshots**: [Ansible Playbook](Screenshots/Repos/ansible_playbook.png)

4. **Links**

   - GitHub Repository: [Link](https://github.com/NaikVRaj/miniProject)
   - Docker Hub Repository: [Link](https://hub.docker.com/repository/docker/naikvraj/miniproject)

5. **Scripts**

   - Dockerfile: [Link](dockerfile)
   - Ansible Playbook: [Link](deploy.yml)
   - Inventory File: [Link](inventory)

6. **Screenshots**

   - Scientific Calculator Application-Operations: [Link](Screenshots/Calculator)

8. **Report**
   - Project Report: [link](MT2023050.pdf)
### Created By:

- Name: Vraj Jatin Naik
- Roll No: MT2023050
- Email-ID: vrajnaik5421@gmail.com