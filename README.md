#### `Step 1:` Download Redis from the official website.  
[Download here](https://github.com/microsoftarchive/redis/releases)
#### `Step 2:`  
Open CMD or PowerShell and execute the following commands.  

`netstat -ano | findstr :6379`  

If port 6379 is available, run the following command   

`redis-server --port 6380`

#### `Step 3:`
Load maven, configurations and run project
