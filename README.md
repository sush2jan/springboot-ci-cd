# springboot-autodeployment-ci-cd
INSTALL openjdk-8-jdk
**********************
$sudo apt update
$sudo apt search openjdk
$sudo apt install openjdk-8-jdk

INSTALL Jenkins
***************

1. Add the repository key to the system
$wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -

2. Append the Debian package repository address to the server’s sources.list:
$sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > \
    /etc/apt/sources.list.d/jenkins.list'

3. Let’s run a system update
$sudo apt-get update

4. Now let’s install Jenkins (Works with openjdk 8)
$sudo apt-get install jenkins  

CHECK Ubuntu Version
********************
$lsb_release -a


START the Jenkins server
**************************
sudo systemctl start jenkins

LOGIN to Jenkins
*********************
http://ec2-13-126-235-185.ap-south-1.compute.amazonaws.com:8080/

To get initial admin password to unlock:
$ sudo cat /var/lib/jenkins/secrets/initialAdminPassword

User Name: admin
Admin Password: Admin@123
Full name: Sushovan Nandan
E-mail address: sushovan2jan@gmail.com

ENABLE firewall on the EC2:
***********************************
$ sudo ufw status
$ sudo ufw enable
$ sudo ufw allow 8080


Pre-requisites
***********************************
1. Install Git
$ sudo apt-get install git-all

2. Install Docker
Uninstall existing docker (If Any): $ sudo apt-get remove docker docker-engine docker.io
Install the latest docker version: $ sudo apt install docker.io

3. Install python
Version: $ python ––version
Update repository: $ sudo apt update
Install supporting software: $ sudo apt install software-properties-common
Add deadsnakes PPA: $ sudo add-apt-repository ppa:deadsnakes/ppa
Install python 3: sudo apt install python3.8

4. Install AWS CLI
$ sudo apt-get update
$ sudo apt-get install awscli


IAM Policy
*****************************
pm-poc-iam-policy-access-ecr-ecs-ec2
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ecs:CreateCluster",
                "ecs:DeregisterContainerInstance",
                "ecs:DiscoverPollEndpoint",
                "ecs:Poll",
                "ecs:RegisterContainerInstance",
                "ecs:StartTelemetrySession",
                "ecs:Submit*",
                "ecr:GetAuthorizationToken",
                "ecr:BatchCheckLayerAvailability",
                "ecr:GetDownloadUrlForLayer",
                "ecr:BatchGetImage",
                "logs:CreateLogStream",
                "logs:PutLogEvents"
            ],
            "Resource": "*"
        }
    ]
}

Store the credentials in /<home>/.aws/credentials file:
[default]
aws_access_key_id = AKIA3R26Q27MEDBQSWWL
aws_secret_access_key = 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

Login to ECR from CLI:
$ aws ecr get-login --no-include-email --region ap-south-1

Create repository:
aws ecr create-repository --repository-name pm-ecr-repo --region ap-south-1

We will use below names for the service creation during our deployment:
ECS Cluster name : pm-poc-ecs-cluster
ECS service name : pm-poc-ecs-service
Task Definition : pm-ecs-ecs-task-defn
ECR repository : pm-ecr-repo
