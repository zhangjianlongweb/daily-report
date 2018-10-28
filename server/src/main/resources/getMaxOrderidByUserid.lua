--
-- Created by IntelliJ IDEA.
-- User: Administrator
-- Date: 2018/10/28
-- Time: 19:08
-- To change this template use File | Settings | File Templates.
--


local v=redis.call("get","maxid"..":"..KEYS[1]);

return v;
