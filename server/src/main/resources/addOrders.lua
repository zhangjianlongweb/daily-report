--
-- Created by IntelliJ IDEA.
-- User: Administrator
-- Date: 2018/10/21
-- Time: 15:47
-- To change this template use File | Settings | File Templates.
--

--redis.call("set","orders:"..KEYS[1]..":"..KEYS[2],KEYS[3]);

redis.call("set","maxid:"..KEYS[1],KEYS[4]);