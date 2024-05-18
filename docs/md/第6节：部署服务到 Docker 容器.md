# 第6节：部署服务到 Docker 容器

## 资料

**如果你需要链接云服务，那么需要在远程 Docker 开启下面的配置**
```java
vim /usr/lib/systemd/system/docker.service
在ExecStart=/usr/bin/dockerd-current 后面加上 -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock \
重新加载配置文件
systemctl daemon-reload
重启启动
systemctl restart docker
```

## 脚本

```java
docker run -e PARAMS=" --chatbot-api.groupId=你的星球ID --chatbot-api.openAiKey=自行申请 --chatbot-api.cookie=登录cookie信息" -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
    
    
docker run -e PARAMS=" --chatbot-api.groupId=88888255114512 --chatbot-api.openAiKey=sk-J8mE87mqwP4BreOOPwzpJr70620ejJ5RpTDTVFl2F13nSoE5 --chatbot-api.cookie=abtest_env=product; zsxqsessionid=7a88d50b5eb3e5d9d753c565cd92ce18; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415485512584528%22%2C%22first_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkMzBlYTc2NmI1ODItMDM3ZjU1MzE1ZmJhNTQyLTI2MDAxOTUxLTIwNzM2MDAtMThkMzBlYTc2NmNkOTMiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU0ODU1MTI1ODQ1MjgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415485512584528%22%7D%2C%22%24device_id%22%3A%2218d30ea766b582-037f55315fba542-26001951-2073600-18d30ea766cd93%22%7D; zsxq_access_token=8673600C-2539-A6ED-61E2-726C3F810E9F_4675DF4ECF5D1908" -p 8090:8090 --name chatbot-api -d chatbot-api:1.0
```
