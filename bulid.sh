#更换镜像名称
# 引导MAVEN_HOME
MAVEN_HOME=/usr/local/maven
PATH=$PATH:$MAVEN_HOME/bin
# 先删除之前的容器
echo "remobe old container"
docker ps -a | grep dockerspringboot | awk '{print $1}'| xargs docker rm -f
# 删除之前的镜像
echo "romove old image"
docker rmi dockerspringboot
# 构建镜像
mvn docker:build
# 打印当前镜像
echo "current docker images"
docker images | grep dockerspringboot
# 启动容器
echo "start container"
docker run -p 8001:8001 -d dockerspringboot
# 打印当前容器
echo "current container"
docker ps -a | grep dockerspringboot
echo "star service success!"