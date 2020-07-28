# Company API  
- GET       /companies    #obtain company list  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/c287f981243f80616dca060fb8000e62.png)
- GET       /companies/1  #obtain a certain specific company  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/5052ec068a3e08534dc5f328da813f21.png)
- GET       /companies/1/employees  # obtain list of all employee under a certain specific company  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/b5ab89eeb9e93f580cb61bb438a965d2.png)
- GET       /companies?page=1&pageSize=5  #Page query, if page equals 1, pageSize equals 5, it will return the data in company list from index 0 to index 4.  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/eae68fbc1b1374ddb3ec78a53caea7e1.png)
- POST      /companies    #add a company     
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/779578668a3a499af380f10edc92dd86.png)

- PUT       /companies/1  #update a company basic infomation  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/493c794fb044545cd4349d0364ee94a1.png)

- DELETE    /companies/1  # delete all employees belong to this company specific company  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/20b0791832743891b7054718a96d80cd.png)

# employees API  
- GET       /employees    #obtain employee list  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/519ee92f65d52954289ff1411c570201.png)

- GET       /employees/1  # obtain a certain specific employee  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/f40c81ad731702164411c173f4d1fe3a.png)


- GET       /employees?page=1&pageSize=5  #Page query, page equals 1, pageSize equals 5  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/d92552aa2e1303c2da88bbf162b8d599.png)

- GET       /employees?gender=male   #screen all male employees  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/642f6f819fbe35585196185dfc5a1c8a.png)

- POST      /employees    # add an employee  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/1ab98bd285a6f78039e5b4d80bfbfb69.png)

- PUT       /employees/1  #update an employee  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/a4d3c9a5f359448303c3a76c346eaf41.png)

- DELETE    /employees/1  #delete an employee  
![companies](https://geticsen-cn.oss-cn-shanghai.aliyuncs.com/user/Geticsen/article/2020-07-28/ab44854bebf40bed245f27f3cc585ce5.png)
