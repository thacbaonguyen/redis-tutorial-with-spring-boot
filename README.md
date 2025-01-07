## `Step 1:` Download Redis from the official website.  
[Download here](https://github.com/microsoftarchive/redis/releases)
## `Step 2:`  
Open CMD or PowerShell and execute the following commands.  

`netstat -ano | findstr :6379`  

If port 6379 is available, run the following command   

`redis-server --port 6380`

## `Step 3:`
Load maven, configurations and run project

### `Guide`
#### `redis-server --port 6380`
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/3.png)

#### `Generate db`
```sql
CREATE TABLE exercise (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255),
    title VARCHAR(255),
    paper VARCHAR(255),
    input VARCHAR(255),
    output VARCHAR(255)
);
```
#### `Check logging`
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/1.png)
When performing the add or delete method:   
+ The cache for the list of exercises will be cleared.
+ The cache for detailed exercises will not be cleared

#### `Before`
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/before-1.png)
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/before-2.png)
#### `After`
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/after-1.png)
![](https://github.com/thacbaonguyen/redis-tutorial-with-spring-boot/blob/master/img/after-2.png)
