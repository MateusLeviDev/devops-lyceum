<details> <summary><strong>Locally nginx `.conf`</strong></summary>



```
log_format upstreamlog '$remote_addr - $remote_user [$time_local] '
                       '"$request" $status $body_bytes_sent '
                       '"$http_referer" "$http_user_agent" '
                       'upstream: $upstream_addr';

upstream backendserver {
    server 127.0.0.1:1111;
    server 127.0.0.1:2222;
    server 127.0.0.1:3333;
    server 127.0.0.1:4444;
}

server {
    listen 3131;
    server_name localhost;

    root /var/www/example.com;
    index index.html;

    rewrite ^/number/(\w+) /count/$1;

    access_log /var/log/nginx/access_upstream.log upstreamlog;

    location / {
        proxy_pass http://backendserver/v1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }

    location ~* /count/[0-9] {
        root /var/www/example.com;
        try_files /index.html =404;
    }

    location /carbs {
        alias /var/www/example.com/fruits;
    }

    location /vegetables {
        root /var/www/example.com;
        try_files /vegetables/veggies.html index.html =404;
    }

    location /crops {
        return 307 /fruits;
    }
}
```

---


- upstreamlog: custom log format that includes proxy-related information and the final upstream server that handled the request.
- Upstream backend: Distributes incoming requests across four Go backend servers running locally on ports 1111, 2222, 3333, and 4444. Round-robin (default) load balancing.
- Listening on port 3131: NGINX is configured to listen on port 3131 and serve content from /var/www/example.com.
- Rewrite rule: Rewrites requests from /number/:id to /count/:id for internal routing purposes.
- Route /: Acts as a reverse proxy for all root requests, forwarding them to the backend with the /v1 prefix.
- Route /count/[0-9]: Serves index.html for matching numeric paths; returns 404 if the file isn’t found.
- Route /carbs: Serves static files from the /fruits directory using alias.
- Route /vegetables: Tries to serve /vegetables/veggies.html; falls back to index.html if it doesn't exist.Route /crops: Performs a 307 Temporary Redirect to /fruits.


![Screenshot From 2025-06-26 18-50-26](https://github.com/user-attachments/assets/5195be4c-b469-4176-b16c-561edb36ca97)

![Screenshot from 2025-06-26 19-29-00](https://github.com/user-attachments/assets/df43da36-ed2f-4f87-bde9-31043b65bee3)


- https://fedoraproject-org.translate.goog/wiki/Nginx?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc

</details>
