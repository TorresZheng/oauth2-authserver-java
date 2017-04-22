# oauth2-authserver-java
:star: Java OAuth2 Auth Server, SpringBoot, Gradle

## Introduction 
1. **获取token-info**  

POST /oauth/token  

Headers:  
Authorization: Basic base64(clientId:clientSecret)

Body:    
username: xxx  
password: xxx  
grant_type: password  

返回值:  
token-info:  

```
{
  "access_token": "07af0a39-2279-4ad2-83be-41725aef2033",
  "token_type": "bearer",
  "refresh_token": "1e51eefa-efbb-4d0b-9ec8-d946a334313b",
  "expires_in": 599,
  "scope": "read report"
}

```

2. **获取用户信息**  
  
GET /oauthuser  

Headers:  
Authorization: bearer {access_token}  

返回值:  
user-info:  

```
{
  "authorities": [
    {
      "authority": "Role_Admin"
    },
    {
      "authority": "Role_CRNAN"
    },
    {
      "authority": "Role_Category"
    },
    {
      "authority": "Role_Reference"
    }
  ]
  ...
 }

```

3. **刷新token**
  
POST /oauth/token  

Headers:  
Authorization: Basic base64(clientId:clientSecret)

Body:  
grant_type: refresh_token  
refresh_token: {refresh_token}  

返回值:  
token-info:  

```

{
  "access_token": "67560f76-5505-471a-9b85-8e9ff02fb181",
  "token_type": "bearer",
  "refresh_token": "1e51eefa-efbb-4d0b-9ec8-d946a334313b",
  "expires_in": 599,
  "scope": "read report"
}

```

4. **强制过期token**  
   
GET /revoketoken  

Headers  
Authorization: bearer {access_token}  

返回值:  
success  


5. **访问资源服务器**  

GET /data  

Headers  
Authorization: bearer {access_token}  

## Usage
1. Idea IDE  
