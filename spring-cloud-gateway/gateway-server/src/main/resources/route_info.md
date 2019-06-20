[Spring Cloud Gateway 配置大全](https://blog.csdn.net/weiwoyonzhe/article/details/88686480)

###### gateway-client1 路由规则
```json
{
	"id": "gateway-client1",
	"predicates": [{
		"name": "Path",
		"args": {
			"pattern": "/gateway-client1/**"
		}
	}],
	"filters": [{
			"name": "TokenAuthenticationFilter",
			"args": {}
		},
		{
			"name": "RewritePath",
			"args": {
				"_genkey_0": "/gateway-client1/(?<segment>.*)","_genkey_1":"/$\\{segment}"
			}
		},
		{
			"name": "Hystrix",
			"args": {
				"name": "default",
				"fallbackUri": "forward:/defaultFallback"
			}
		},
		{
			"name": "RequestRateLimiter",
			"args": {
				"key-resolver": "#{@remoteAddressKeyResolver}",
				"redis-rate-limiter.replenishRate": "20",
				"redis-rate-limiter.burstCapacity": "20"
			}
		}
	],
	"uri": "lb://gateway-client1",
	"order": 0
}
```

###### gateway-client2 路由规则
```json
{
	"id": "gateway-client2",
	"predicates": [{
		"name": "Path",
		"args": {
			"pattern": "/gateway-client2/**"
		}
	}],
	"filters": [{
			"name": "TokenAuthenticationFilter",
			"args": {}
		},
		{
            "name": "RewritePath",
        	"args": {
        		"_genkey_0": "/gateway-client2/(?<segment>.*)","_genkey_1":"/$\\{segment}"
        	}
        },
		{
			"name": "Hystrix",
			"args": {
				"name": "default",
				"fallbackUri": "forward:/defaultFallback"
			}
		},
		{
			"name": "RequestRateLimiter",
			"args": {
				"key-resolver": "#{@remoteAddressKeyResolver}",
				"redis-rate-limiter.replenishRate": "20",
				"redis-rate-limiter.burstCapacity": "20"
			}
		}
	],
	"uri": "lb://gateway-client1",
	"order": 0
}
```
