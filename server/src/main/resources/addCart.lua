--
-- Created by IntelliJ IDEA.
-- User: Administrator
-- Date: 2018/10/21
-- Time: 15:47
-- To change this template use File | Settings | File Templates.
--

redis.call("set","carts:"..KEYS[1]..":"..KEYS[2]..":"..KEYS[3],KEYS[4]);

